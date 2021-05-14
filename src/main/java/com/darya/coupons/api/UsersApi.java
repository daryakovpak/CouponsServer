package com.darya.coupons.api;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.darya.coupons.controllers.UsersController;
import com.darya.coupons.data.SuccesfulLoginData;
import com.darya.coupons.data.UserLoginData;
import com.darya.coupons.data.UserLoginDetails;
import com.darya.coupons.dto.CompanyDto;
import com.darya.coupons.dto.UserDto;
import com.darya.coupons.exceptions.ProjectException;

@RestController
@RequestMapping("/users")

public class UsersApi {

	@Autowired
	UsersController usersController;


	@PostMapping ("/login")
	public SuccesfulLoginData login (@RequestBody UserLoginDetails userLoginDetails) throws ProjectException {
		return this.usersController.login(userLoginDetails);
	}

	@PostMapping
	public void createUser (@RequestBody UserDto user) throws ProjectException {
		usersController.createUser(user);
	}

	//	deleteUserForAdmin
	@DeleteMapping ("/{userId}")
	public void deleteUserForAdmin(@PathVariable ("userId") long id) throws ProjectException {
		usersController.deleteUser(id);
	}

	//	only for admin
	@GetMapping
	public List <UserDto> getAllUsers() throws ProjectException {
		return this.usersController.getAllUsers();
	}

	//	user deletes himself
	@DeleteMapping 
	public void deleteUser(HttpServletRequest request) throws ProjectException {
		UserLoginData data = (UserLoginData) request.getAttribute("userLoginData");
		usersController.deleteUser(data.getUserId());
	}

	@GetMapping ("/{userId}")
	public UserDto getUser(@PathVariable("userId") long id) throws ProjectException {
		return usersController.getUser(id);
	}

	@PutMapping("/{userId}")
	public void updateUser(@PathVariable("userId") long userId, @RequestBody UserDto user) throws ProjectException {
		user.setId(userId);
		this.usersController.updateUser(user);
	}

	//	for company and/or admin
	@GetMapping("/byCompanyId")
	public  List <CompanyDto> getUsersByCompanyId (@RequestParam ("companyId") long companyId, HttpServletRequest request) throws ProjectException {
		UserLoginData data = (UserLoginData) request.getAttribute("userLoginData");
		return this.usersController.getUsersByCompanyId(companyId, data);
	}

}
