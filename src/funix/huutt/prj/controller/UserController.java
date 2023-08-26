package funix.huutt.prj.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Pattern;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import funix.huutt.prj.entity.Donation;
import funix.huutt.prj.entity.Role;
import funix.huutt.prj.entity.User;
import funix.huutt.prj.entity.UserDonation;
import funix.huutt.prj.entity.Validation;
import funix.huutt.prj.service.DonationService;
import funix.huutt.prj.view.AccountView;
import funix.huutt.prj.view.DonationDetailView;
import funix.huutt.prj.view.DonationView;
import funix.huutt.prj.view.UserView;

@Controller
public class UserController extends ParseToView  {
	
	@Autowired
	private DonationService donationService;
	
	private Validation validation = new Validation();
	
	private int entriesPerPage = 10;
	private int page = 1;
	private String searchKey = "";
	private int step = 1;
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
	private Role userRole; 
	
	private boolean validUsername;
	private boolean validPassword;
	private boolean validConfirmPassword;
	private boolean validFullName;
	private boolean validEmail;
	private boolean validPhoneNumber;
	
	private boolean validDonationName;
	private boolean validDonationStartDate;
	private boolean validDonationEndDate;
	
	
	
	public UserController() {
		userRole = donationService.getRole("user");
	}
	
	// In the first time, the site will move to admin / create admin account page.
	@RequestMapping("/")
	public String indexPage(Model model, HttpSession session) {
		
		// Getting start...
		users = donationService.getUsers();
		if(users == null || users.size() == 0) {
			return "redirect:/admin/";
		}
		
		donations = donationService.getDonations();
		donationViews = new ArrayList<>();
		
		if(donations != null )
			for(Donation dona: donations) {
				donationViews.add(parseDonationToView(dona));
			}
		
		
		model.addAttribute("donations", donationViews);
		session.setAttribute("currentPage", "homepage");	
		
		return "user/n-donates-list"; 
	}
	
	// Login required when clicking on any "Donate" button
	@RequestMapping("/login")
	public String login(Model model, HttpSession session) {
		
		if(this.passed) {
			model.addAttribute("login", new AccountView());
			session.setAttribute("currentPage", "login");	
		} else {
			model.addAttribute("errors", validation);
			model.addAttribute("login", accView);
		}
		return "user/form-login";
	}
	
	@PostMapping("/loginPOST") 
	public String login(@ModelAttribute("login") AccountView login,  Model model, HttpSession session ) {
		
		user = donationService.login(login.getUsername(), login.getPassword());
		validation.resetValidation();
		
		if(user == null) {
			
			validation.setErrorLogin("Tên đăng nhập hoặc mật khẩu không chính xác. Vui lòng đăng nhập lại.");
			
			this.accView = login;
			this.passed = false;
			
			return "redirect:/login" ;
			
		} else {
			session.setAttribute("username", user.getFullName() );
			session.setAttribute("userId", user.getId());
			
			return "redirect:/" + ((String) session.getAttribute("previousPage"));
				
		}
		
	}
	
	
	@GetMapping("/register")
	public String register(Model model, HttpSession session) { 
		
		if(this.step == 1) {
			if(this.passed) {
				
				model.addAttribute("account", new AccountView() );
			} else {
				model.addAttribute("account", accView);
				model.addAttribute("errors", validation);
			}
			
			return "user/newUser/new-account";
			
		} else if (step == 2) {
			if(this.passed ) { 
				
				userView = parseUserToView(user);
				
				model.addAttribute("account", userView);
				
			} else {
				model.addAttribute("account", userView);
				model.addAttribute("errors", validation);
				
			}
			
			return "user/newUser/update-user";
		}
		
		// App will login automatically after registering.
		session.setAttribute("userName", user.getFullName());
		session.setAttribute("userID", user.getId());
		
		step = 1;
		passed = true;
		
		return "redirect:/";
		
		
	}
	
	@PostMapping("/registerNewAccount")
	public String register(@ModelAttribute("account") AccountView view, Model model, HttpSession session) { 
		
		
		validation.resetValidation();
		
		validUsername = this.isValidUsername(view.getUsername()) ;
		validPassword = this.isValidPassword(view.getPassword()) ;
		validConfirmPassword = 
				this.isValidConfirmPassword(view.getPassword(), view.getConfirmPassword());
		
		if(!validUsername && !validPassword && !validConfirmPassword) {
			
			accView = view;
			
			step = 1;
			passed = false;
			
			
		} else {
		
			user = parseViewToUser(view);
			user.setRole(userRole);
			
			donationService.saveUser(user);		
			user = donationService.getUser(view.getUsername());
			
			step = 2;
			passed = true;
		}
		
		return "redirect:/register";
		
	}
	
	@GetMapping("/updateRegister") 
	public String register(@ModelAttribute("account") UserView account, Model model, HttpSession session) {
		
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
			
			user = donationService.getUser(account.getId());
			updateUser(user, account);
			
			donationService.saveUser(user);		
			
			this.step = 3;
			this.passed = true;
		}
		return "redirect:/register";
	}
	
	
	@RequestMapping("/logout") 
	public String logout(Model model, HttpSession session) {
		
		session.removeAttribute("username");
		session.removeAttribute("userId");
		
		
		return "redirect:/";
	}
	
	@RequestMapping("/donate")
	public String userDonate (@RequestParam("id") int donationId, Model model, HttpSession session) {
		
		validation.resetValidation();
		
		if(session.getAttribute("username") == null) {
			
			validation.setRequestLogin("Bạn cần đăng nhập trước khi thực hiện quyên góp.");
			model.addAttribute("request", validation);
			
			model.addAttribute("login", new AccountView());
			
			return "user/form-login";
		}
		
		
		DonationDetailView donate = new DonationDetailView();
		
		
		donate.setUserId((int) session.getAttribute("userId"));
		donate.setUserName( (String) session.getAttribute("username"));
		donate.setDonationId(donationId);
		donate.setDonationName(donationService.getDonation(donationId).getName());
		
		model.addAttribute("donate",donate);

		String currentPage = (String) session.getAttribute("currentPage");
		
		if(currentPage.equals("donationDetail")) 
			return "user/n-donation-detail";
		else 
			return "user/n-donates-list";
		
	}
	
	
	
	@RequestMapping("/donateAction")
	public String donateAction ( @ModelAttribute("donate") DonationDetailView view , Model model, HttpSession session) {
		
		UserDonation ud = parseViewToUserDonation(view);
		ud.setDonation(donationService.getDonation(view.getDonationId()));
		ud.setUser(donationService.getUser(view.getUserId()));
		ud.setCreated(new Date());
		
		Donation dona = ud.getDonation();
		dona.setMoney(dona.getMoney() + ud.getMoney());
		
		donationService.saveUserDonation(ud);
		donationService.saveDonation(dona);
		
		String currentPage = (String) session.getAttribute("currentPage");
		if(currentPage.equals("donationDetail"))
			currentPage += "?id=" + view.getDonationId();

		return "redirect:/" + currentPage;
	}
	
	@RequestMapping("/close")
	public String closeDonation(@RequestParam("id") int id, Model model, HttpSession session) { 
		List<Donation> donations = donationService.getDonations();
		List<DonationView> views = new ArrayList<>();
		
		for(Donation dona: donations) {
			views.add(parseDonationToView(dona));
		}
		
		
		model.addAttribute("donations", views);
			
		String currentPage = (String) session.getAttribute("currentPage");
		
		
		
		if(currentPage.equals("donationDetail")) {
			return "redirect:/" + currentPage + "?id=" + id;
		}
		else 
			return "redirect:/" + currentPage;
		
	}
	

	@RequestMapping("/donationDetail")
	public String donationDetail(@RequestParam("id") int id, Model model, HttpSession session) {
		
		Donation dona = donationService.getDonation(id);
		
		List<UserDonation> userDonations = donationService.getUserDonations(id);
		List<DonationDetailView> donausers = new ArrayList<>();
		
		
		for(UserDonation ud: userDonations ) { 
			donausers.add(parseUserDonationToView(ud));
		}
		
		
		model.addAttribute("dona", parseDonationToView(dona));
		model.addAttribute("donausers", donausers);

		session.setAttribute("currentPage", "donationDetail");
		session.setAttribute("id", id);
		
		return "user/n-donation-detail";
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
				
			
			if(!donationService.isValidUsername(username)){
				
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
