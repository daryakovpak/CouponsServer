package com.darya.coupons.controllers;

import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.darya.coupons.dao.IUserDao;
import com.darya.coupons.data.SuccesfulLoginData;
import com.darya.coupons.data.UserLoginData;
import com.darya.coupons.data.UserLoginDetails;
import com.darya.coupons.dto.CompanyDto;
import com.darya.coupons.dto.UserDto;
import com.darya.coupons.entities.CompanyEntity;
import com.darya.coupons.entities.UserEntity;
import com.darya.coupons.enums.ErrorType;
import com.darya.coupons.enums.UserType;
import com.darya.coupons.exceptions.ProjectException;
@Controller
public class UsersController {

	private static final String ENCRYPTION_TOKEN_SALT = "qwertyasdfg11111???/567/ghjk-9";

	@Autowired
	private IUserDao usersDao;

	@Autowired
	CacheController cacheController;

	@Autowired
	private CompaniesController companiesController;

	public UsersController() {
	}

	public void createUser(UserDto user) throws ProjectException {
		validateUserInfoToCreate(user);
		try {
			UserEntity entity = new UserEntity();
			if (user.getUserType().equals(UserType.COMPANY)) {
				long companyId = user.getCompanyId();
				CompanyEntity company = companiesController.getCompanyEntity(companyId);
				entity.setCompany(company);
			}
			entity.setUsername(user.getUserName());
			String hashPassword = (user.getPassword()+ENCRYPTION_TOKEN_SALT).hashCode()+"";
			entity.setPassword(hashPassword);
			entity.setFirstName(user.getFirstName());
			entity.setLastName(user.getLastName());
			entity.setUserType(user.getUserType());
			this.usersDao.save(entity);
		} catch (Exception e) {
			throw new ProjectException(ErrorType.GENERAL_ERROR, "CreateUser failed: ", e);
		}
	}

	public UserDto getUser (long id) throws ProjectException {
		try {
			return this.usersDao.getUser(id);
		} catch (Exception e) {
			throw new ProjectException(ErrorType.GENERAL_ERROR, "getUser failed: ", e);
		}
	}

	public List <UserDto> getAllUsers() throws ProjectException {
		try {
			return this.usersDao.getAllUsers();
		} catch (Exception e) {
			throw new ProjectException(ErrorType.GENERAL_ERROR, "getAllUsers failed: ", e);
		}
	}

	public void updateUser (UserDto user) throws ProjectException {
		validateUserInfoToUpdate(user);
		try {
			UserEntity entity = new UserEntity(user);
			this.usersDao.save(entity);
		} catch (Exception e) {
			throw new ProjectException(ErrorType.GENERAL_ERROR, "updateUser failed: ", e);
		}
	}

	public void deleteUser (long id) throws ProjectException {
		try {
			this.usersDao.deleteById(id);
		} catch (Exception e) {
			throw new ProjectException(ErrorType.GENERAL_ERROR, "updateUser failed: ", e);
		}
	}

	public List<CompanyDto> getUsersByCompanyId(long companyId, UserLoginData data) throws ProjectException {
		try {
			if (data.getUserType().equals(UserType.ADMIN)) {
				return usersDao.getUsersByCompanyId(companyId);
			}
			if (data.getUserType().equals(UserType.COMPANY)) {
				return usersDao.getUsersByCompanyId(data.getCompanyId());
			}
		} catch (Exception e) {
			throw new ProjectException(ErrorType.GENERAL_ERROR, "getUsersByCompanyId failed: ", e);
		}	
		throw new ProjectException(ErrorType.HACKING_ATTEMP, "Hacking attemp by " + data.getUserId());
	}

	public SuccesfulLoginData login (UserLoginDetails userLoginDetails) throws ProjectException {
		validateLogin(userLoginDetails.getUsername(), userLoginDetails.getPassword());
		UserLoginData userLoginData = new UserLoginData();
		try {
			userLoginData = this.usersDao.login(userLoginDetails.getUsername(), (userLoginDetails.getPassword()+ENCRYPTION_TOKEN_SALT).hashCode()+"");
		} catch (Exception e) {
			throw new ProjectException(ErrorType.LOGIN_FAILED, "login failed: ", e);
		}
		if (userLoginData == null) {
			throw new ProjectException(ErrorType.INVALID_LOGIN_DETAILS, "Username or/and password is/are incorrect");
		}
		int token = generateToken(userLoginDetails);
		cacheController.put(String.valueOf(token), userLoginData);
		return new SuccesfulLoginData(token, userLoginData.getUserType());
	}

	public UserEntity getUserEntity(long userId) throws ProjectException {
		try {
			return usersDao.findById(userId).get();
		} catch (Exception e) {
			throw new ProjectException(ErrorType.GENERAL_ERROR, "getUserEntity failed on "+ e.getMessage());
		}
	}

	private int generateToken(UserLoginDetails userLoginDetails) {
		String text = userLoginDetails.getUsername() + Calendar.getInstance().getTime().toString() + ENCRYPTION_TOKEN_SALT;
		return text.hashCode();
	}

	private void validateUserInfoToCreate (UserDto user) throws ProjectException {
		try {
			if (usersDao.existsByUsername(user.getUserName())) {
				throw new ProjectException(ErrorType.NAME_ALREADY_EXISTS, "Name already exists");
			}
		} catch (Exception e) {
			throw new ProjectException(ErrorType.GENERAL_ERROR, "existsByUsername failed: ", e);
		}	
		validateUsername(user.getUserName());
		if (user.getPassword().length()<6 || user.getPassword().length()>10) {
			throw new ProjectException(ErrorType.INVALID_PASSWORD, "Password must contain 6-10 characters");
		}
		validateText(user.getFirstName());
		validateText(user.getLastName());

	}
	//	checking the entered username and password - if user entered initially incorrect data -
	//	we`ll catch it before send it to DB
	private void validateLogin (String username, String password) throws ProjectException {
		validateUsername(username);
		if (password.length()<6 || password.length()>10) {
			throw new ProjectException(ErrorType.INVALID_PASSWORD, "Password must contain 6-10 characters");
		}
	}

	private void validateUsername (String username) throws ProjectException {
		if (username == null) {
			throw new ProjectException(ErrorType.MUST_ENTER_NAME, "Can not insert an null/empty name");
		}
		if (username.isEmpty()) {
			throw new ProjectException(ErrorType.MUST_ENTER_NAME, "Can not insert an null/empty name");
		}		
		if (username.length() <2) {
			throw new ProjectException(ErrorType.MUST_ENTER_NAME, "User name is too short");
		}
	}

	private void validateText (String text) throws ProjectException {
		for (int i = 0; i < text.length(); i++) {
			if (!Character.isLetter(text.charAt(i))) {
				throw new ProjectException(ErrorType.MUST_ENTER_NAME, "Must contain letters only");
			}
		}
	}

	private void validateUserInfoToUpdate (UserDto user) throws ProjectException {
		try {
			if (usersDao.existsByUsername(user.getUserName())) {
				throw new ProjectException(ErrorType.NAME_ALREADY_EXISTS, "Name already exists");
			}
		} catch (Exception e) {
			throw new ProjectException(ErrorType.GENERAL_ERROR, "existsByUsername failed: ", e);
		}	
		validateUsername(user.getUserName());
		if (user.getPassword().length()<6 || user.getPassword().length()>10) {
			throw new ProjectException(ErrorType.INVALID_PASSWORD, "Password must contain 6-10 characters");
		}
		validateText(user.getFirstName());
		validateText(user.getLastName());
	}
}

