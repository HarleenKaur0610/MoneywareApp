package com.moneyware.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.moneyware.model.ArchivalDetails;

public interface IArchivalRepository extends JpaRepository<ArchivalDetails, Long>{

}
