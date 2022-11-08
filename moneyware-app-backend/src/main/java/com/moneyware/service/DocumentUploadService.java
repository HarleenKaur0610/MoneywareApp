package com.moneyware.service;

import java.io.IOException;
import java.util.List;

import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.moneyware.model.DocumentDetails;
import com.moneyware.model.DocumentStatus;
import com.moneyware.model.UserDetails;
import com.moneyware.model.UserDocument;
import com.moneyware.repository.IDocumentUploadRepository;
import com.moneyware.repository.IUserLoginRepository;

@Service
public class DocumentUploadService {
	
	@Autowired
	IDocumentUploadRepository documentUploadRepository;

	@Autowired
	IUserLoginRepository userLoginRepository;

	public DocumentDetails uploadDocument(MultipartFile userFile, String userDocumentStr) {
		
		DocumentDetails documentDetails = new DocumentDetails();
		try {
		UserDocument userDocument = new Gson().fromJson(userDocumentStr, UserDocument.class) ; 
		System.out.println("uu>"+userDocument);
		UserDetails user=userLoginRepository.findByCustName(userDocument.getUserName());
		documentDetails.setUserDetails(user);
		documentDetails.setDocumentName(StringUtils.cleanPath(userFile.getOriginalFilename()));
		documentDetails.setDocumentType(userDocument.getDocumentType());
		documentDetails.setDocumentSize(userFile.getSize());
		documentDetails.setDocumentObj(userFile.getBytes());
		documentDetails.setDocumntStatus(DocumentStatus.COMPLETED);
		System.out.println("Document Details: "+documentDetails);
		} catch (IOException e) {
			e.printStackTrace();
		}
	    return documentUploadRepository.save(documentDetails);
	}
	
	public List<DocumentDetails> getAllDocumentsListByUsername(String username) {
		UserDetails user=userLoginRepository.findByCustName(username);
	    return documentUploadRepository.findAllByUserDetails(user.getCustId());
	}
}
