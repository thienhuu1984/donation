package funix.huutt.prj.controller;

import funix.huutt.prj.entity.Donation;
import funix.huutt.prj.entity.Role;
import funix.huutt.prj.entity.User;
import funix.huutt.prj.entity.UserDonation;
import funix.huutt.prj.entity.Validation;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Pattern;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import funix.huutt.prj.service.AdminService;
import funix.huutt.prj.view.AccountView;
import funix.huutt.prj.view.DonationDetailView;
import funix.huutt.prj.view.DonationView;
import funix.huutt.prj.view.UserView;

@Controller
@RequestMapping("/admin")
public class AdminController extends ParseToView {
	
	@Autowired
	private AdminService adminService;
	
	private Validation validation = new Validation();
	
	private int entriesPerPage = 10;
	private int page = 1;
	private String searchKey = "";
	private int step = 0;
	private boolean passed = true;
	int start, end, pages = 1;
	
	private List<AccountView> accViews;
	private List<UserView> userViews;
	private List<User> users;
	private List<Role> roles;
	private List<Donation> donations;
	private List<UserDonation> userDonations;
	private List<DonationDetailView> donationDetails;
	private List<DonationView> donationViews;	
	
	private AccountView accView;
	private UserView userView;
	private User user;
	private Donation donation;
	private Role role;
	private UserDonation userDonation;
	
	private boolean validUsername;
	private boolean validPassword;
	private boolean validConfirmPassword;
	private boolean validFullName;
	private boolean validEmail;
	private boolean validPhoneNumber;
	
	private boolean validDonationName;
	private boolean validDonationStartDate;
	private boolean validDonationEndDate;
	
	
	
	// Go to index page of admin (main page) 
	
	// If that time is the 1st time coming, user have to create the administrator account for 
	// managing application
	@GetMapping("/")
	public String adminIndex(Model model, HttpSession session) {
		
		validation.resetValidation();

		// occurs at 1st time when starting Donation App
		
		if(adminService.getUsers() == null || adminService.getUsers().size() == 0) {
			
			session.setAttribute("pagename", "login");
			this.step = 1;
			
			return "redirect:/admin/welcome";
		}
		
		session.setAttribute("pagename", "");
		
		// when logging in, the fullName of user will be stored at session attribute "adminname"
		if(session.getAttribute("adminname") == null) 
			return requestLogin(model, session);
		
		return "redirect:/admin/donation";
	}
	
	// This method will be called when entering app 1st time.
	@RequestMapping("/welcome")
	public String welcome(Model model, HttpSession session) { 
		
		session.setAttribute("welcome", "welcome");
		
		if(step == 1 ) {
			if(this.passed) {
				
				validation.resetValidation();
				
				// Entering welcome model
				model.addAttribute("account", new AccountView());
			} else {
				
				// Failed when inputting username / password / confirmpassword
				// then come back to p1 page
				model.addAttribute("errors", validation);
				model.addAttribute("account", this.accView);
			}
					
			return "admin/userForm/new-account";
			
		} else if (step == 2) {
			if(this.passed) {
				
				// Passed when inputting username / password / confirmPassword
				// then saved administrator name to database. (previous method)
				model.addAttribute("account", this.userView);
				
			} else {
				
				// Failed when inputting email / phoneNumber / or fullName is blank
				// It was checked at previous method
				
				model.addAttribute("errors", validation);
				model.addAttribute("account", this.userView);
				
			}
			
			return "admin/userForm/update-user";
			
		} else if (step == 3) {
			
			this.step = 1; 
			this.passed = true;
			session.removeAttribute("welcome");
			
			return "admin/userForm/ready";
			
		}
		return "redirect:/admin/";
			
	}
	
	// This method also called by welcome page
	@PostMapping("/step-2")
	public String welcome2(@ModelAttribute("account") AccountView account, Model model, HttpSession session) {
		
		Role adminRole = adminService.getRole("admin");
		
		validation.resetValidation();
		
		this.validPassword = isValidPassword(account.getPassword());
		this.validConfirmPassword = isValidConfirmPassword(account.getPassword(), account.getConfirmPassword());
		
		if(! this.validPassword || !this.validConfirmPassword) {
			
			this.accView = account;
			this.step = 1;
			this.passed = false;
			
		} else {
			this.user = parseViewToUser(account);
			this.user.setRole(adminRole);
			
			adminService.saveUser(user);
			
			this.user = adminService.getUser(account.getUsername());
			userView = new UserView();
			userView.setId(user.getId());
			userView.setRoleId(adminRole.getId());
			userView.setRoleName(adminRole.getRoleName());
			
			this.step = 2;
			this.passed = true;
			
		}
		
		return "redirect:/admin/welcome";
	}
	
	// This method also called by welcome page
	@GetMapping("/ready") 
	public String startApp(@ModelAttribute("account") UserView account, Model model, HttpSession session ) {
		
		validation.resetValidation();
		
		validFullName = account.getFullName() != null && account.getFullName().length() > 0;
		validEmail = isValidEmail(account.getEmail());
		validPhoneNumber = isValidPhoneNumber(account.getPhoneNumber());
		
		validation.setErrorFullName(validFullName?"":"Họ và tên không được để trống.");
		
		if(!validFullName || !validEmail || !validPhoneNumber) {
			
			this.userView = account;
			this.step = 2;
			this.passed = false;
		} else {
		
			user = adminService.getUser(account.getId());
			
			updateUser(user, account);
			
			adminService.saveUser(user);
			
			session.setAttribute("adminname", account.getFullName());
			
			this.step = 3;
			this.passed = true;
			
			return "redirect:/admin/";
		}
		
		return "redirect:/admin/welcome";
		
	}
	
	
	// login methods /////////////////////
	// Always required logging in when using app
	// Only admin can log in at Admin site
	
	@RequestMapping("/login") 
	public String loginAdmin(Model model, HttpSession session) { 
		
		// "previouspage" attribute in session will store 
		// for coming back when logged in
		session.setAttribute("previouspage", session.getAttribute("pagename"));
		
		model.addAttribute("account", new AccountView());
		session.setAttribute("pagename", "login");
				
		return "admin/admin-login";
	}
	
	// Submit of login 
	@PostMapping("/loginPOST")
	public String loginPOST(@ModelAttribute("account") AccountView login, Model model, HttpSession session) { 
		
		validation.resetValidation();
		
		user = adminService.loginAdmin(login.getUsername(), login.getPassword());
		
		if(user == null) {
			
			validation.setErrorLogin("Tên đăng nhập hoặc mật khẩu không chính xác. Vui lòng đăng nhập lại.");
			model.addAttribute("account", login);
			
			model.addAttribute("errors", validation);
			
			return "admin/admin-login";
			
		} else {
			
			session.setAttribute("adminname", user.getFullName());
			session.setAttribute("userId", user.getId());
			
			// The page viewed before logged in would be comed back
			return "redirect:/admin/" + session.getAttribute("previouspage");
		}
		
	}
	
	@RequestMapping("/logout")
	public String logout(HttpSession session) { 
		session.removeAttribute("adminname");
		session.removeAttribute("userId");
		
		return "redirect:/admin/";
	}
	
	@RequestMapping("/createNewAccount")
	public String newAccount(Model model, HttpSession session) { 
		
		if(session.getAttribute("adminname") == null) 
			return requestLogin(model, session);

		if(step == 1 ) {
			if(this.passed) {
				
				validation.resetValidation();
				
				// Entering welcome model
				model.addAttribute("account", new AccountView());
				model.addAttribute("roles", adminService.getRoles());
				
			} else {
				
				// Failed when inputting username / password / confirmpassword
				// then come back to p1 page
				model.addAttribute("errors", validation);
				model.addAttribute("account", this.accView);
			}
					
			return "admin/createAccount/new-account";
			
		} else if (step == 2) {
			
			if(this.passed) {
				
				// Passed when inputting username / password / confirmPassword
				// then saved administrator name to database. (previous method)
				model.addAttribute("account", this.userView);
				
			} else {
				
				// Failed when inputting email / phoneNumber / or fullName is blank
				// It was checked at previous method
				
				model.addAttribute("errors", validation);
				model.addAttribute("account", this.userView);
				
			}
			
			return "admin/createAccount/update-user";
			
		} 
		
		this.step = 1;
		this.passed = true;
		
		return "redirect:/admin/users";
	}

	// submit for createNewAccount
	@PostMapping("/newAccount") 
	public String createNewAccount(@ModelAttribute("account") AccountView view, Model model, HttpSession session) {
		
		validation.resetValidation();
		
		if(session.getAttribute("adminname") == null) 
			return requestLogin(model, session);
		
		validUsername = isValidUsername(view.getUsername());
		validPassword = isValidPassword(view.getPassword());
		validConfirmPassword = isValidConfirmPassword(
							view.getPassword(), view.getConfirmPassword());
		
		if( validUsername  && validPassword && validConfirmPassword ) {
			
			user = parseViewToUser(view);
			adminService.saveUser(user);
			
			user = adminService.getUser(view.getUsername());
			this.userView = parseUserToView(user);
			
			this.step = 2;
			this.passed = true;
			
		} else {
			
			this.accView = view;
			this.step = 1;
			this.passed = false;
			
		} 
		
		return "redirect:/admin/createNewAccount";
		
	}
	
	@GetMapping("/newAccount2")
	public String createNewAccount(@ModelAttribute("account") UserView account, Model model, HttpSession session) {
		
		validation.resetValidation();
		
		// Check validate input fields
		validFullName = account.getFullName() != null && account.getFullName().length() > 0;
		validEmail = isValidEmail(account.getEmail());
		validPhoneNumber = isValidPhoneNumber(account.getPhoneNumber());
		
		validation.setErrorFullName(validFullName?"":"Họ và tên không được để trống.");
		
		// If one of them is wrong, the previous page will be come back
		if(!validFullName || !validEmail || !validPhoneNumber) {
			this.userView = account;
			this.step = 2;
			this.passed = false;
			
		} else {
			
			// If all inputs are right, user will be updated.
			
			user = adminService.getUser(account.getId());
			updateUser(user, account);
			
			adminService.saveUser(user);			
			session.setAttribute("adminname", account.getFullName());
			
			this.step = 3;
			this.passed = true;
		}
		
		return "redirect:/admin/createNewAccount";
	}
	
	//////////////////////// RESET PASSWORD ////////////////////////
	// User: reset password action
	@RequestMapping("/resetPassword")
	public String resetPassword(@RequestParam("id") int id, Model model, HttpSession session) {
		
		if(session.getAttribute("adminname") == null) 
			return requestLogin(model, session);
		
					
		user = adminService.getUser(id);
		this.accView = new AccountView();
		
		this.accView.setUsername(user.getFullName());
		this.accView.setId(id);
		
		model.addAttribute("account", this.accView);
				
		
		return "admin/admin-reset-password";
		
	}
	
	// submit for resetPassword
	// Administrator can reset password for all users without old password.
	@PostMapping("/performResetPassword")
	public String resetPassword(@ModelAttribute("account") AccountView view, Model model, HttpSession session) { 
		
		validation.resetValidation();
		validPassword = isValidPassword(view.getPassword());
		validConfirmPassword = isValidConfirmPassword(view.getPassword(), view.getConfirmPassword());
		
		if( validPassword && validConfirmPassword ) {
			
			user = adminService.getUser(view.getUsername());
			
			if(user == null) 
				model.addAttribute("display", "null");
			else {
				user.setPassword(view.getPassword());
				adminService.saveUser(user);
			}
			
			String previousPage = (String) session.getAttribute("previouspage");
			
			previousPage += previousPage.equals("detailUser")?"?id=" + view.getId():"";
			
			return "redirect:/admin/"  + previousPage;
		} else {
			
//			validation.setErrorOldPassword("Mật khẩu cũ không chính xác. Vui lòng kiểm tra lại.");
			model.addAttribute("errors", validation);
			
			return "redirect:/admin/resetPassword";
		}
	}
	
	@GetMapping("/users")
	public String usersList(Model model, HttpSession session) {
		
		userViews = new ArrayList<>();;
		
		validation.resetValidation();

		if(session.getAttribute("adminname") == null) 
			return requestLogin(model, session);
		
		users = adminService.getUsers();
		
		if(this.searchKey != null || this.searchKey.length()>0) {
			users = adminService.getUsers(this.searchKey);
		}
		
		if(users != null) {
		
			start = (page-1)*entriesPerPage;
			if(start >= users.size()) {
				start = 0;
				page = 1;
			}
			
			end = start + entriesPerPage - 1;
			end = end > users.size() ? users.size()-1: end;
			pages = users.size() / entriesPerPage + ( users.size() % entriesPerPage > 0 ? 1: 0);  
			
			for(int i = start; i <=  end; i++) {
				userViews.add(parseUserToView(users.get(i)));
			}
		}
		
		model.addAttribute("usersList", userViews);
		
		session.setAttribute("pagename", "users");
		session.setAttribute("totalPages", pages);
		session.setAttribute("curPage", page);
		
		return "admin/admin-user-list";
	}
	
	// Display list by rows(entries) per page
	@RequestMapping("/entries")
	public String setEntries(@RequestParam("entries") int entries, Model model, HttpSession session) {
		
		String pagename = (String) session.getAttribute("pagename");
		this.entriesPerPage = entries;
		session.setAttribute("entries", entries);
		
		return "redirect:/admin/" + pagename ;
		
	}
	
	// Display page of each users / donations / donation details
	@RequestMapping("/page")
	public String viewPage(@RequestParam("page") int page, Model model, HttpSession session) {
		
		String pagename = (String) session.getAttribute("pagename");

		this.page = page;
		
		return "redirect:/admin/" + pagename ;
		
	}
	
	@GetMapping("/updateUser")
	public String fromEditUser(@RequestParam("id") int id, Model model, HttpSession session) {
		
		validation.resetValidation();

		if(session.getAttribute("adminname") == null) 
			return requestLogin(model, session);
		
		User user = adminService.getUser(id);
		
		UserView v = parseUserToView(user);
		v.setProcess("processUpdateUser");
		
		List<Role> roles = adminService.getRoles();
		
		model.addAttribute("account", v);
		model.addAttribute("roles", roles);
		
		
		return "admin/admin-from-detail";
	}
	
	@GetMapping("/processUpdateUser")
	public String editUser(@Valid @ModelAttribute("account") UserView view, Model model, HttpSession session) {
		
		validation.resetValidation();

		if(session.getAttribute("adminname") == null) 
			return requestLogin(model, session);
		
		
		User user = adminService.getUser(view.getId());
		
		updateUser(user, view);
		if( user.getRole().getId() != view.getRoleId()) 
			user.setRole(adminService.getRole(view.getRoleId()));
		
		adminService.saveUser(user);
				
		return "redirect:/admin/users";
	}
	
	@RequestMapping("/changeRole")
	public String changeRole(@RequestParam("id") int id, Model model, HttpSession session) { 
		
		validation.resetValidation();

		if(session.getAttribute("adminname") == null) 
			return requestLogin(model, session);
		
		model.addAttribute("changeRole", adminService.getRoles());
		
		model.addAttribute("user", parseUserToView(adminService.getUser(id)));
		
		return "admin/admin-detail-user";
	}
	
	@GetMapping("/detailUser")
	public String userDetail(@RequestParam("id") int id, Model model, HttpSession session) {
		
		validation.resetValidation();

		if(session.getAttribute("adminname") == null) 
			return requestLogin(model, session);
		
		User u = adminService.getUser(id);
		
		UserView v = parseUserToView(u);
		
		
		model.addAttribute("user", v);
		
		return "admin/admin-detail-user";
	}
	
	@GetMapping("/updateRole")
	public String updateRole(@ModelAttribute("user") UserView view, Model model, HttpSession session) {
		
		validation.resetValidation();

		if(session.getAttribute("adminname") == null) 
			return requestLogin(model, session);
		
		User user = adminService.getUser(view.getId());
		user.setRole(adminService.getRole(view.getRoleId()));
		adminService.saveUser(user);
		
		return "redirect:/admin/users";
	}
	
	@GetMapping("/blockUser")
	public String blockUser(@RequestParam("id") int id, Model model, HttpSession session) { 
		
		validation.resetValidation();

		if(session.getAttribute("adminname") == null) 
			return requestLogin(model, session);
		
		User user = adminService.getUser(id);
		user.block();
		adminService.saveUser(user);
		
		
		List<User> users = adminService.getUsers();
		List<UserView> views = new ArrayList<>();
		
		for(User u : users) 
			views.add(parseUserToView(u));
		
		model.addAttribute("usersList", views);
		
		return "redirect:/admin/users";
	}
	
	@GetMapping("/unblockUser")
	public String unblockUser(@RequestParam("id") int id, Model model, HttpSession session) { 
		
		validation.resetValidation();

		if(session.getAttribute("adminname") == null) 
			return requestLogin(model, session);

		User user = adminService.getUser(id);
		user.unblock();
		adminService.saveUser(user);
		
		List<User> users = adminService.getUsers();
		List<UserView> views = new ArrayList<>();
		
		for(User u : users) 
			views.add(parseUserToView(u));
		
		model.addAttribute("usersList", views);
		
		return "redirect:/admin/users";
	}
	
	@GetMapping("/deleteUser")
	public String deleteUser(@RequestParam("id") int id, Model model, HttpSession session) { 
		
		validation.resetValidation();

		if(session.getAttribute("adminname") == null) 
			return requestLogin(model, session);

		adminService.deleteUser(id);
		
		List<User> users = adminService.getUsers();
		List<UserView> views = new ArrayList<>();
		
		for(User u : users) 
			views.add(parseUserToView(u));
		
		model.addAttribute("usersList", views);
		
		return "redirect:/admin/users";
	}
	
	
	@GetMapping("/donation")
	public String donationList(Model model, HttpSession session) { 
		
		int start, end, pages;
		List<Donation> donations;
		List<DonationView> views;
		
		validation.resetValidation();

		if(session.getAttribute("adminname") == null) 
			return requestLogin(model, session);
		
		donations = adminService.getDonations();
		
		if(this.searchKey != null || this.searchKey.length() > 0 ) {
			donations = adminService.getDonations(this.searchKey);
		}
		
		views = new ArrayList<>();		
		start = (page-1)*entriesPerPage;
		if(start >= donations.size()) {
			start = 0;
			page = 1;
		}
		
		end = start + entriesPerPage - 1;
		end = end > donations.size() ? donations.size()-1: end;
		pages = donations.size() / entriesPerPage + ( donations.size() % entriesPerPage > 0 ? 1: 0);  
		
		for(int i = start; i <=  end; i++) {
			views.add(parseDonationToView(donations.get(i)));
		}
		
		model.addAttribute("donations", views);
		session.setAttribute("totalPages", pages);
		session.setAttribute("curPage", page);
		session.setAttribute("pagename", "donation");
		
		return "admin/admin-donation-list";
	}
	
	@GetMapping("/addDonation")
	public String addNewDonation(Model model, HttpSession session) { 
		
		validation.resetValidation();

		if(session.getAttribute("adminname") == null) 
			return requestLogin(model, session);
		
		model.addAttribute("dona", new DonationView());
		
		return "admin/admin-add-donation";
	}
	
	@GetMapping("/processAddDonation")
	public String addDonation(@Valid @ModelAttribute("dona") DonationView view, Model model) {
		
		Donation dona = parseViewToDonation(view);
		
		dona.updateStatus();
		dona.setMoney(0);
		dona.setCreated(new Date());
		
		adminService.saveDonation(dona);
		
		List<Donation> donations = adminService.getDonations();
		List<DonationView> views = new ArrayList<>();
		
		
		for(Donation d: donations) {
			views.add(parseDonationToView(d));
		}
		
		model.addAttribute("donations", views);
		
		return "redirect:/admin/donation";
	}
	
	@GetMapping("/editDonation")
	public String editDonation(@RequestParam("id") int id, Model model, HttpSession session) { 
		
		validation.resetValidation();

		if(session.getAttribute("adminname") == null) 
			return requestLogin(model, session);
		
		DonationView view = new DonationView("processEditDonation", "CẬP NHẬT HOẠT ĐỘNG QUYÊN GÓP", "Cập nhật");
		
		parseDonationToView(adminService.getDonation(id), view);
		
		model.addAttribute("dona", view);

		
		return "admin/admin-add-donation";
	}
	
	@GetMapping("/processEditDonation")
	public String processEditDonation(@Valid @ModelAttribute("dona") DonationView view, Model model) {
		
		Donation dona = adminService.getDonation(view.getId());
		
		parseViewToDonation(dona, view);
		
		dona.updateStatus();
		
		adminService.saveDonation(dona);
		
		List<Donation> donations = adminService.getDonations();
		List<DonationView> views = new ArrayList<>();
		
		for(Donation d: donations) {
			views.add(parseDonationToView(d));
		}
		
		model.addAttribute("donations", views);
		
		return "redirect:/admin/donation";
	}
	
	@RequestMapping("/donationDetail")
	public String donationDetails(@RequestParam("id") int id, Model model, HttpSession session) {
		
		validation.resetValidation();

		if(session.getAttribute("adminname") == null) 
			return requestLogin(model, session);
		
		model.addAttribute("dona", parseDonationToView(adminService.getDonation(id)));
		
		List<UserDonation> userDonations = adminService.getUserDonations();
		List<DonationDetailView> donausers = new ArrayList<>();
		
		for(UserDonation ud: userDonations ) { 
			donausers.add(parseUserDonationToView(ud));
		}
		
		
		model.addAttribute("donausers", donausers);
		
		return "admin/admin-donation-details";
	}
	
	
	@GetMapping("/finish")
	public String finishDonation(@RequestParam("id") int id, Model model, HttpSession session) {
		
		validation.resetValidation();

		if(session.getAttribute("adminname") == null) 
			return requestLogin(model, session);
		
		Donation dona = adminService.getDonation(id);

		dona.finish();
		dona.setStatus(-1);
		
		adminService.saveDonation(dona);
		
		List<Donation> donations = adminService.getDonations();
		
		model.addAttribute("donations", donations);
		
		return "redirect:/admin/donation";
	}
	
	@RequestMapping("/start")
	public String startDonation(@RequestParam("id") int id, Model model, HttpSession session) {
		
		validation.resetValidation();

		if(session.getAttribute("adminname") == null) 
			return requestLogin(model, session);
		
		Donation dona = adminService.getDonation(id);

		dona.start();
		
		adminService.saveDonation(dona);
		
		List<Donation> donations = adminService.getDonations();
		
		model.addAttribute("donations", donations);
		
		return "redirect:/admin/donation";
	}
	
	@RequestMapping("/deleteDonation")
	public String deleteDonation(@RequestParam("id") int id, Model model, HttpSession session) { 
		
		validation.resetValidation();

		if(session.getAttribute("adminname") == null) 
			return requestLogin(model, session);
		
		adminService.deleteDonation(id);
		
		List<Donation> donations = adminService.getDonations();
		
		model.addAttribute("donations", donations);
		
		return "redirect:/admin/donation";
		
	}
	
	@RequestMapping("/confirmDonate")
	public String confirmDonation(@RequestParam("userDonationId") int id, Model model, HttpSession session) { 
		
		validation.resetValidation();

		if(session.getAttribute("adminname") == null) 
			return requestLogin(model, session);
		
		UserDonation ud = adminService.getUserDonation(id);
		
		ud.setStatus(1);
		
		adminService.saveUserDonation(ud);
		
		
		return "redirect:/admin/donationDetail?id=" + ud.getDonation().getId();
	}
	
	@RequestMapping("/cancelDonate")
	public String cancelDonation(@RequestParam("userDonationId") int id, Model model, HttpSession session) { 
		
		validation.resetValidation();

		if(session.getAttribute("adminname") == null) 
			return requestLogin(model, session);
		
		UserDonation ud = adminService.getUserDonation(id);
		
		ud.setStatus(0);
		
		adminService.saveUserDonation(ud);
		
		
		return "redirect:/admin/donationDetail?id=" + ud.getDonation().getId();
	}
	
	
	@GetMapping("/roles") 
	public String rolesManage( Model model, HttpSession session) { 
		
		validation.resetValidation();

		if(session.getAttribute("adminname") == null) 
			return requestLogin(model, session);
		
		List<Role> roles = adminService.getRoles();
		
		model.addAttribute("newRole", new Role());
		model.addAttribute("rolesList", roles);
		
		session.setAttribute("pagename", "roles");
		
		return "admin/admin-role-manage";
	}
	
	@GetMapping("/addNewRole") 
	public String addnewRole(@ModelAttribute("newRole") Role newRole, Model model, HttpSession session){ 
		
		validation.resetValidation();

		if(session.getAttribute("adminname") == null) 
			return requestLogin(model, session);
		
		if( newRole.getRoleName().trim() != "" ) {
			adminService.saveRole(newRole);
		}
		
		return "redirect:/admin/roles";
	}
	
	@RequestMapping("/search")
	public String search(@RequestParam("key") String key, Model model, HttpSession session) { 
		
		validation.resetValidation();

		if(session.getAttribute("adminname") == null) 
			return requestLogin(model, session);
		
		String pagename = (String) session.getAttribute("pagename");
		this.searchKey = key;
		
		return "redirect:/admin/" + pagename;
		
		
	}
	
	// validation areas
	
	// In Admin site, all pages have to be login. So this method is used to all pages here. 
	protected String requestLogin(Model model, HttpSession session) {
		
		validation.resetValidation();
		validation.setRequestLogin("Bạn cần phải đăng nhập trước");
		
		model.addAttribute("account", new AccountView());
		model.addAttribute("request", validation);
		session.setAttribute("pagename", "login");
		
		return "admin/admin-login";
	}

	protected boolean isValidUsername(String username) { 
		
		if(username.length() == 0)
			validation.setErrorUsername("Tên đăng nhập không được bỏ trống");
			
		
		if(!adminService.isValidUsername(username)){
			
			validation.setErrorUsername("\nTên đăng nhập bạn chọn đã tồn tại. Vui lòng chọn tên khác.");
		}
		
		return validation.getErrorUsername().length() == 0;
	}
	
	protected boolean isValidPassword(String password) {
		
		
		
		if(password.length() < 8) {
			validation.setErrorPassword("Mật khẩu phải có ít nhất 8 ký tự.");
			return false;
		}
		
		if(password.contains(password)) {
			
			Pattern pattern = Pattern.compile("^(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9]).+$");
			if(!pattern.matcher(password).matches()) {
				validation.setErrorPassword("\nMật khẩu phải bao gồm chữ viết HOA, chữ viết thường và số");
				return false;
			}
		}
		
		return true;
	}
	
	protected boolean isValidConfirmPassword(String password, String confirmPassword) {

		if(!password.equals(confirmPassword)) {
			
			validation.setErrorConfirmPassword("Nhắc lại mật khẩu phải giống mật khẩu.");
			return false;
		}
		return true;
		
	}

	protected boolean isValidEmail(String email) {

		if(!email.contains("@")) {
			validation.setErrorEmail( "Email bạn nhập không hợp lệ.");
			return false;
		}
		return true;
	}
	
	protected boolean isValidPhoneNumber(String phoneNumber) {
		
		try {
			
			Long.parseLong(phoneNumber);
			return true;
			
		} catch (Exception e) {
			phoneNumber += "Số điện thoại bạn nhập không hợp lệ.";
			return false;
		}
		
	}
	
}

