package com.moneyware.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.moneyware.model.DocumentDetails;
import com.moneyware.model.DocumentStatus;

@Repository
public interface IDocumentUploadRepository extends JpaRepository<DocumentDetails, Long> {

	List<DocumentDetails> findAllByUserDetails(long custId);
	List<DocumentDetails> findAllByDocumentStatus(DocumentStatus status);
}
