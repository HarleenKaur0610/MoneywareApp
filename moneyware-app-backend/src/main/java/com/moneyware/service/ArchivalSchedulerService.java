package com.moneyware.service;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.text.SimpleDateFormat;
import java.util.Base64;
import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.moneyware.model.ArchivalDetails;
import com.moneyware.model.ArchivalStatus;
import com.moneyware.model.DocumentDetails;
import com.moneyware.model.DocumentStatus;
import com.moneyware.repository.IArchivalRepository;
import com.moneyware.repository.IDocumentUploadRepository;

@Service
public class ArchivalSchedulerService {

	private static final String COMMENT = "COMMENT: ONDEMAND GENERIC INDEX FILE GENERATED";
	private static final String GRP_NAME = "GROUP_FIELD_NAME";
	private static final String GRP_VALUE = "GROUP_FIELD_VALUE";
	private static final String GRP_FNAME = "GROUP_FILENAME";
	private static final String DELIM_COLON = ":";

	@Autowired
	IDocumentUploadRepository documentUploadRepository;

	@Autowired
	IArchivalRepository archivalRepository;

	@Value("${archival.filepath}")
	String filePath;
	
	
	@Scheduled(cron = "30 * * * * *")
	@Transactional
	private void archiveUploadedDocuments() {
		System.out.println("Archiving");

		List<DocumentDetails> documentList = documentUploadRepository.findAllByDocumentStatus(DocumentStatus.COMPLETED);
		documentList.forEach((document) -> {
		archiveFile(document,filePath);
		});
	}

	@Transactional
	private void archiveFile(DocumentDetails document, String filePath) {
		String archPath= filePath + document.getDocumentName();
		String timeStamp = new SimpleDateFormat("ddMMyyyy").format(new Date());
		byte[] data = document.getDocumentObj();
		Path path = Paths.get(archPath);
		try {
			byte[] dataDecoded = Base64.getDecoder().decode(data);
			Files.write(path, dataDecoded, StandardOpenOption.CREATE);
			String indexFilePath=filePath + "index_" + document.getDocumentId() + "_"+ timeStamp + ".txt";
			generateIndex(indexFilePath,timeStamp,document.getUserDetails().getCustId(),document.getDocumentType(),document.getDocumentName());
			saveArchivalDetails(document,document.getDocumentType(),filePath,indexFilePath);
			updateDocumentDetailsStatus(document);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

	private void generateIndex(String indexFilePath, String timeStamp, long custId, String documentType,
			String documentName) {
		try(PrintWriter pw = new PrintWriter(new FileWriter(indexFilePath))){
			pw.println(COMMENT);
			pw.println(GRP_NAME + DELIM_COLON + "TIMESTAMP");
			pw.println(GRP_VALUE + DELIM_COLON + timeStamp);
			pw.println(GRP_NAME + DELIM_COLON + "CUSTOMER_ID");
			pw.println(GRP_VALUE + DELIM_COLON + custId);
			pw.println(GRP_NAME + DELIM_COLON + "DOCUMENT_TYPE");
			pw.println(GRP_VALUE + DELIM_COLON + documentType);
			pw.println(GRP_FNAME + DELIM_COLON + documentName);	
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void saveArchivalDetails(DocumentDetails documentDetails, String docTyp, String filepath, String indexFilePath) {
		ArchivalDetails archivalDetails = new ArchivalDetails();
		archivalDetails.setArchiveFilePath(filepath);
		archivalDetails.setIndexFilePath(indexFilePath);
		archivalDetails.setDocumentDetails(documentDetails);
		archivalDetails.setArchivalStatus(ArchivalStatus.ARCHIVED);
		archivalRepository.save(archivalDetails);
	}
	
	private void updateDocumentDetailsStatus(DocumentDetails document) {
		document.setDocumntStatus(DocumentStatus.ARCHIVED);
		documentUploadRepository.save(document);
	}
}
