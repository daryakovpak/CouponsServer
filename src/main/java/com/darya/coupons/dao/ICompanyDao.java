package com.darya.coupons.dao;


import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.darya.coupons.dto.CompanyDto;
import com.darya.coupons.entities.CompanyEntity;

@Repository
public interface ICompanyDao extends CrudRepository<CompanyEntity, Long> {

	public boolean existsByName(String name);

	@Query("select new com.darya.coupons.dto.CompanyDto (c) from CompanyEntity c")
	public List<CompanyDto> getAllCompanies ();

	@Query("select new com.darya.coupons.dto.CompanyDto (c) from CompanyEntity c where c.id = ?1")
	public CompanyDto getCompanyWithId (long id);


}
