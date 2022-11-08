package com.moneyware.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.moneyware.model.DocumentDetails;
import com.moneyware.model.UserDocument;
import com.moneyware.service.DocumentUploadService;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api/v1.0/")
public class DocumentUploadController {
	
	@Autowired
	DocumentUploadService docUploadService;

	@RequestMapping(value="/uploadDocument", method=RequestMethod.POST, consumes= {MediaType.MULTIPART_FORM_DATA_VALUE,MediaType.APPLICATION_JSON_VALUE})
	public DocumentDetails uploadDocument(@RequestPart("uploadedFile") MultipartFile file,
			@RequestPart("userDocument")String userDocument) {
			System.out.println("Upload request received from User:");
			return docUploadService.uploadDocument(file,userDocument);

	}
	
	@RequestMapping(value="/listDocuments/{id}", method=RequestMethod.GET)
	public List<DocumentDetails> getDocumentsByUsername(@PathVariable String userName) {
		System.out.println("Received Request: getDocumentsByUsername");
	    return docUploadService.getAllDocumentsListByUsername(userName);
	}
	
}
