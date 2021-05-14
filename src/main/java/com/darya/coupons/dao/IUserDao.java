package com.darya.coupons.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.darya.coupons.data.UserLoginData;
import com.darya.coupons.dto.CompanyDto;
import com.darya.coupons.dto.UserDto;
import com.darya.coupons.entities.UserEntity;

@Repository
public interface IUserDao extends CrudRepository<UserEntity, Long> {


	public boolean existsByUsername(String username);

	@Query ("select new com.darya.coupons.dto.UserDto(u) from UserEntity u where u.id = ?1")
	public UserDto getUser (long id);

	@Query ("select new com.darya.coupons.dto.UserDto(u) from UserEntity u")
	public List<UserDto> getAllUsers();

	@Query ("select new com.darya.coupons.data.UserLoginData (u) from UserEntity u where u.username = ?1 and u.password = ?2")
	public UserLoginData login(String username, String password);

	@Query("select new com.darya.coupons.dto.UserDto(u) from UserEntity u where u.company.id = ?1")
	public List<CompanyDto> getUsersByCompanyId(Long companyId);

}
