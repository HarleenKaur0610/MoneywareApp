package com.moneyware.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.moneyware.model.UserDetails;

@Repository
public interface IUserLoginRepository extends JpaRepository<UserDetails, Long> {

	UserDetails findByCustName(String custName);
}
