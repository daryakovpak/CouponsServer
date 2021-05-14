package com.darya.coupons.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.darya.coupons.dao.ICompanyDao;
import com.darya.coupons.dto.CompanyDto;
import com.darya.coupons.entities.CompanyEntity;
import com.darya.coupons.enums.ErrorType;
import com.darya.coupons.exceptions.ProjectException;

@Controller
public class CompaniesController {

	@Autowired
	private ICompanyDao companiesDao;

	public void createCompany(CompanyDto company) throws ProjectException {
		validateCompanyInfoToCreate(company);
		try {
			CompanyEntity entity = new CompanyEntity(company);
			this.companiesDao.save(entity);
		} catch (Exception e) {
			throw new ProjectException(ErrorType.GENERAL_ERROR, "CreateCompany failed on ", e);
		}
	}

	public void updateCompany(CompanyDto company) throws ProjectException {
		validateCompanyInfoToUpdate(company);
		CompanyEntity entity = new CompanyEntity(company);
		try {
			this.companiesDao.save(entity);
		} catch (Exception e) {
			throw new ProjectException(ErrorType.GENERAL_ERROR, "updateCompany failed on ", e);
		}
	}

	public void deleteCompany(long id) throws ProjectException {
		try {
			companiesDao.deleteById(id);
		} catch (Exception e) {
			throw new ProjectException(ErrorType.GENERAL_ERROR, "deleteCompany failed on ", e);
		}
	}

	public CompanyDto getCompany(long id) throws ProjectException {
		try {
			return this.companiesDao.getCompanyWithId(id);
		} catch (Exception e) {
			throw new ProjectException(ErrorType.GENERAL_ERROR, "deleteCompany failed on ", e);
		}
	}

	public List <CompanyDto> getAllCompanies() throws ProjectException {
		try {
			return this.companiesDao.getAllCompanies();
		} catch (Exception e) {
			throw new ProjectException(ErrorType.GENERAL_ERROR, "deleteCompany failed on ", e);
		}
	}

	public CompanyEntity getCompanyEntity (long companyId) throws ProjectException {
		try {
			return companiesDao.findById(companyId).get();
		} catch (Exception e) {
			throw new ProjectException(ErrorType.GENERAL_ERROR, "getCompanyEntity failed - " , e);
		}
	}

	private void validateCompanyInfoToCreate(CompanyDto company) throws ProjectException {
		if (this.companiesDao.existsByName(company.getName())) {
			throw new ProjectException(ErrorType.NAME_ALREADY_EXISTS, "This company`s name already exist");
		} 
		//		validate CompanyName
		if (company.getName() == null) {
			throw new ProjectException(ErrorType.MUST_ENTER_NAME, "Can not insert an null/empty name");
		}
		if (company.getName().isEmpty()) {
			throw new ProjectException(ErrorType.MUST_ENTER_NAME, "Can not insert an null/empty name");
		}		
		if (company.getName().length() <2) {
			throw new ProjectException(ErrorType.MUST_ENTER_NAME, "Name is too short");
		}
		//		validate address 
		if (company.getAddress() == null) {
			throw new ProjectException(ErrorType.MUST_ENTER_ADDRESS, "Can not insert an null/empty address");
		}
		if (company.getAddress().length()<3) {
			throw new ProjectException(ErrorType.MUST_ENTER_ADDRESS, "Can not insert an null/empty address");
		}
		//		validate PhoneNumber
		if (company.getPhoneNumber() == null) {
			throw new ProjectException(ErrorType.INVALID_PHONENUMBER, "Can not insert an null phonenumber");
		}
		if (company.getPhoneNumber().length()<9 ||company.getPhoneNumber().length()>10) {
			throw new ProjectException(ErrorType.INVALID_PHONENUMBER, "Phone number must contain 9-10 digits");
		}
		for (int i = 0; i < company.getPhoneNumber().length(); i++) {
			if (!Character.isDigit(company.getPhoneNumber().charAt(i))) {
				throw new ProjectException(ErrorType.INVALID_PHONENUMBER, "Phone number must contain digits only");
			}
		}
	}

	private void validateCompanyInfoToUpdate(CompanyDto company) throws ProjectException {
		if (company.getId() < 1 ) {
			throw new ProjectException(ErrorType.ID_DOES_NOT_EXIST, "Can not update without company`s ID");
		}
		//		validate CompanyName
		CompanyEntity companyNow = getCompanyEntity(company.getId());
		if (!company.getName().equals(companyNow.getName())) {
			try {
				if (this.companiesDao.existsByName(company.getName())) {
					throw new ProjectException(ErrorType.NAME_ALREADY_EXISTS, "This company`s name already exist");
				} 
			} catch (Exception e) {
				throw new ProjectException(ErrorType.GENERAL_ERROR, "Failed", e);
			}
		}
		if (company.getName().isEmpty()) {
			throw new ProjectException(ErrorType.MUST_ENTER_NAME, "Can not insert an null/empty name");
		}		

		if (company.getName().length() <2) {
			throw new ProjectException(ErrorType.MUST_ENTER_NAME, "Name is too short");
		}
		//		validate address
		if (company.getAddress() != null) {
			if (company.getAddress().length()<3) {
				throw new ProjectException(ErrorType.MUST_ENTER_ADDRESS, "Can not insert an null/empty address");
			}
		}
		//		validate PhoneNumber
		if (company.getPhoneNumber() != null) {
			if (company.getPhoneNumber().length()<9 ||company.getPhoneNumber().length()>10) {
				throw new ProjectException(ErrorType.INVALID_PHONENUMBER, "Phone number must contain 9-10 digits");
			}
			for (int i = 0; i < company.getPhoneNumber().length(); i++) {
				if (!Character.isDigit(company.getPhoneNumber().charAt(i))) {
					throw new ProjectException(ErrorType.INVALID_PHONENUMBER, "Phone number must contain digits only");
				}
			}
		}
	}
}



