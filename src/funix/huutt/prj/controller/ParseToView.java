package funix.huutt.prj.controller;

import java.text.SimpleDateFormat;
import java.util.Date;

import funix.huutt.prj.entity.Donation;
import funix.huutt.prj.entity.User;
import funix.huutt.prj.entity.UserDonation;
import funix.huutt.prj.view.AccountView;
import funix.huutt.prj.view.DonationDetailView;
import funix.huutt.prj.view.DonationView;
import funix.huutt.prj.view.UserView;

public class ParseToView {
	
	// create and move username & password to user for updating user in database
	static public void updateUser(User u, UserView v) { 
		
		if(u.getFullName() == null || !u.getFullName().equals(v.getFullName())) //
			u.setFullName(v.getFullName());
		
		if(u.getAddress() == null || !u.getAddress().equals(v.getAddress())) //
			u.setAddress(v.getAddress());
		
		if(u.getEmail() == null || !u.getEmail().equals(v.getEmail())) //
			u.setEmail(v.getEmail());
		
		if(u.getNote() == null || !u.getNote().equals(v.getNote())) //
			u.setNote(v.getNote());
		
		if(u.getPhoneNumber() == null || !u.getPhoneNumber().equals(v.getPhoneNumber())) //
			u.setPhoneNumber(v.getPhoneNumber());
		
	}
	
	// create and move username & password to user for saving database
	static public User parseViewToUser(AccountView v) { 
		User u = new User();
		u.setUsername(v.getUsername());
		u.setPassword(v.getPassword());
		u.setCreatedDate(new Date());
		u.setStatus(0);
		
		return u;
		
	}
	
	static public UserView parseUserToView(User u) {
		UserView v = new UserView();
		v.setId(u.getId());
		v.setFullName(u.getFullName());
		v.setAddress(u.getAddress());
		v.setCreatedDate(u.getCreatedDate());
		v.setEmail(u.getEmail());
		v.setNote(u.getNote());
		v.setPhoneNumber(u.getPhoneNumber());
		v.setStatus(u.getStatus());
		v.setRoleId(u.getRole().getId());
		v.setRoleName(u.getRole().getRoleName());
		return v;
	}
	

	static public Donation parseViewToDonation(DonationView view) { 
		Donation d = new Donation();
		
		d.setId(view.getId());
		d.setCode(view.getCode());
		d.setName(view.getName());
		d.setOrganizationName(view.getOrganizationName());
		d.setDescription(view.getDescription());
		d.setPhoneNumber(view.getPhoneNumber());
		d.setMoney(view.getMoney());
		d.setStatus(view.getStatus());
		d.setCreated(new Date());
		try {
			d.setStartDate(new SimpleDateFormat("yyyy-MM-dd").parse(view.getStartDate()));
			d.setEndDate(new SimpleDateFormat("yyyy-MM-dd").parse(view.getEndDate()));
			if(view.getCreated() != "")
				d.setCreated(new SimpleDateFormat("yyyy-MM-dd").parse(view.getCreated()));
			else 
				d.setCreated(new Date());
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return d;
	}
	
	static public void parseViewToDonation(Donation d, DonationView view) { 
		d.setId(view.getId());
		d.setCode(view.getCode());
		d.setName(view.getName());
		d.setOrganizationName(view.getOrganizationName());
		d.setDescription(view.getDescription());
		d.setPhoneNumber(view.getPhoneNumber());
		d.setMoney(view.getMoney());
		d.setStatus(view.getStatus());
		

		try {
			d.setStartDate(new SimpleDateFormat("yyyy-MM-dd").parse(view.getStartDate()));
			d.setEndDate(new SimpleDateFormat("yyyy-MM-dd").parse(view.getEndDate()));
			if(view.getCreated() != "")
				d.setCreated(new SimpleDateFormat("yyyy-MM-dd").parse(view.getCreated()));
			else 
				d.setCreated(new Date());
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	static public DonationView parseDonationToView(Donation dona) { 
		DonationView v = new DonationView();
		
		v.setId(dona.getId());
		v.setCode(dona.getCode());
		v.setName(dona.getName());
		v.setOrganizationName(dona.getOrganizationName());
		v.setDescription(dona.getDescription());
		v.setPhoneNumber(dona.getPhoneNumber());
		v.setMoney(dona.getMoney());
		v.setStatus(dona.getStatus());
		v.setProcess("processAddDonation");
		
		v.setStartDate(new SimpleDateFormat("yyyy-MM-dd").format(dona.getStartDate()));
		v.setEndDate(new SimpleDateFormat("yyyy-MM-dd").format(dona.getEndDate()));
		v.setCreated(new SimpleDateFormat("yyyy-MM-dd").format(dona.getCreated()));
		
		return v;
	}
	
	static public void parseDonationToView(Donation dona, DonationView v) { 
		
		v.setId(dona.getId());
		v.setCode(dona.getCode());
		v.setName(dona.getName());
		v.setOrganizationName(dona.getOrganizationName());
		v.setDescription(dona.getDescription());
		v.setPhoneNumber(dona.getPhoneNumber());
		v.setMoney(dona.getMoney());
		v.setStatus(dona.getStatus());
		v.setProcess("processAddDonation");
		
		v.setStartDate(new SimpleDateFormat("yyyy-MM-dd").format(dona.getStartDate()));
		v.setEndDate(new SimpleDateFormat("yyyy-MM-dd").format(dona.getEndDate()));
		v.setCreated(new SimpleDateFormat("yyyy-MM-dd").format(dona.getCreated()));
		
	}
	

	static public DonationDetailView parseUserDonationToView(UserDonation ud) {
		DonationDetailView view = new DonationDetailView();
		
		view.setId(ud.getId());
		view.setMessage(ud.getMessage());
		view.setDonationId(ud.getDonation().getId());
		view.setDonationName(ud.getDonation().getName());
		view.setUserId(ud.getUser().getId());
//		view.setUserName((ud.getUser().getFullName() == null || ud.getUser().getFullName().length() == 0) ? ud.getUser().getUsername() :ud.getUser().getFullName());
		view.setUserName(ud.getUser().getFullName());
		view.setCreated(new SimpleDateFormat("yyyy-MM-dd").format(ud.getCreated()));
		view.setMoney(ud.getMoney());
		view.setStatus(ud.getStatus());
		view.setHiddenName(ud.getHiddenUser()==1);
		
		return view;
	}
	
	static public void parseUserDonationToView (UserDonation ud, DonationDetailView view) {
		view.setId(ud.getId());
		view.setMessage(ud.getMessage());
		view.setDonationId(ud.getDonation().getId());
		view.setUserId(ud.getUser().getId());
		view.setUserName(ud.getUser().getFullName());
		view.setCreated(new SimpleDateFormat("yyyy-MM-dd").format(ud.getCreated()));
		view.setMoney(ud.getMoney());
		view.setStatus(ud.getStatus());
	}
	
	static public UserDonation parseViewToUserDonation(DonationDetailView view) { 
		UserDonation ud = new UserDonation();
		
		ud.setId(view.getId());
		ud.setMessage(view.getMessage());
//		ud.setDonation(adminService.getDonation(view.getDonationId()));
//		ud.setUser(adminService.getUser(view.getUserId()));
		ud.setMoney(view.getMoney());
		ud.setStatus(view.getStatus());
		ud.setHiddenUser(view.isHiddenName()?1:0);
		
		try {

			if(view.getCreated() != null)
				ud.setCreated(new SimpleDateFormat("yyyy-MM-dd").parse(view.getCreated() ));
			else ud.setCreated(new Date());
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return ud;
	}
	
	static public void parseViewToUserDonation(DonationDetailView view, UserDonation ud) {
		
		ud.setId(view.getId());
		ud.setMessage(view.getMessage());
//		ud.setDonation(adminService.getDonation(view.getDonationId()));
//		ud.setUser(adminService.getUser(view.getUserId()));
		ud.setMoney(view.getMoney());
		ud.setStatus(view.getStatus());
		
		try {

			if(view.getCreated() != "")
				ud.setCreated(new SimpleDateFormat("yyyy-MM-dd").parse(view.getCreated() ));
			else ud.setCreated(new Date());
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	
	



	
	
	
	


}
