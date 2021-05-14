package com.darya.coupons.api;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.darya.coupons.controllers.CompaniesController;
import com.darya.coupons.dto.CompanyDto;
import com.darya.coupons.exceptions.ProjectException;

@RestController
@RequestMapping("/companies")

public class CompaniesApi {

	@Autowired
	CompaniesController companiesController;

	//	only admins have access to these functions:

	@PostMapping
	public void createCompany(@RequestBody CompanyDto company) throws ProjectException {
		companiesController.createCompany(company);
	}

	@DeleteMapping ("/{companyId}")
	public void deleteCompany(@PathVariable("companyId") long id) throws ProjectException {
		companiesController.deleteCompany(id);
	}

	@GetMapping
	public List <CompanyDto> getAllCompanies() throws ProjectException {
		return companiesController.getAllCompanies();
	}

	@PutMapping("/{companyId}")
	public void updateCompany(@PathVariable("companyId") long companyId ,@RequestBody CompanyDto company) throws ProjectException {
		company.setId(companyId);
		companiesController.updateCompany(company);
	}

	//		getCompany to all users
	@GetMapping ("/{companyId}")
	public CompanyDto getCompany(@PathVariable("companyId") long id) throws ProjectException {
		return companiesController.getCompany(id);
	}


}
