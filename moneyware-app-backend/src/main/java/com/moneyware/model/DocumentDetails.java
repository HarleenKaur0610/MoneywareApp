package com.moneyware.model;



import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="document_details")
public class DocumentDetails {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="doc_id")
	private long documentId;
	
	@ManyToOne(cascade= CascadeType.ALL)
	@JoinColumn(name = "cust_id")
	private UserDetails userDetails;
	
	@Column(name="doc_typ")
	private String documentType;
	
	@Column(name="doc_name")
	private String documentName;
	
	@Column(name="doc_obj")
	@Lob
	private byte[] documentObj;
	
	@Column(name="doc_size")
	private long documentSize;
	
	@Enumerated(EnumType.ORDINAL)
	@Column(name="doc_status")
	private DocumentStatus documentStatus;
	
	@Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_tmstmp", nullable = false)
    private Date createdTimestamp;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "updated_tmstmp", nullable = false)
    private Date updatedTimestamp;

    @PrePersist
    protected void onCreate() {
    	updatedTimestamp = createdTimestamp = new Date();
    }

    @PreUpdate
    protected void onUpdate() {
    	updatedTimestamp = new Date();
    }

	@Override
	public String toString() {
		return "DocumentDetails [documentId=" + documentId + ", userDetails=" + userDetails + ", documentType="
				+ documentType + ", documentName=" + documentName + ", documentObj=" + documentObj
				+ ", documentSize=" + documentSize + ", documentStatus=" + documentStatus + ", createdTimestamp="
				+ createdTimestamp + ", updatedTimestamp=" + updatedTimestamp + "]";
	}

	public long getDocumentId() {
		return documentId;
	}

	public void setDocumentId(long documentId) {
		this.documentId = documentId;
	}

	public UserDetails getUserDetails() {
		return userDetails;
	}

	public void setUserDetails(UserDetails userDetails) {
		this.userDetails = userDetails;
	}

	public String getDocumentType() {
		return documentType;
	}

	public void setDocumentType(String documentType) {
		this.documentType = documentType;
	}

	public String getDocumentName() {
		return documentName;
	}

	public void setDocumentName(String documentName) {
		this.documentName = documentName;
	}

	public byte[] getDocumentObj() {
		return documentObj;
	}

	public void setDocumentObj(byte[] documentObj) {
		this.documentObj = documentObj;
	}

	public long getDocumentSize() {
		return documentSize;
	}

	public void setDocumentSize(long documentSize) {
		this.documentSize = documentSize;
	}

	public DocumentStatus getDocumntStatus() {
		return documentStatus;
	}

	public void setDocumntStatus(DocumentStatus documentStatus) {
		this.documentStatus = documentStatus;
	}

	public Date getCreatedTimestamp() {
		return createdTimestamp;
	}

	public void setCreatedTimestamp(Date createdTimestamp) {
		this.createdTimestamp = createdTimestamp;
	}

	public Date getUpdatedTimestamp() {
		return updatedTimestamp;
	}

	public void setUpdatedTimestamp(Date updatedTimestamp) {
		this.updatedTimestamp = updatedTimestamp;
	}
	
	
}
