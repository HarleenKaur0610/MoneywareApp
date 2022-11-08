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
import javax.persistence.OneToOne;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="archival_details")
public class ArchivalDetails {
	
		@Id
		@GeneratedValue(strategy=GenerationType.AUTO)
		@Column(name="arch_id")
		private long archivalId;
		
		@OneToOne(cascade= CascadeType.MERGE)
		@JoinColumn(name = "doc_id")
		private DocumentDetails documentDetails;
		
		@Column(name="arch_file_path")
		private String archiveFilePath;
		
		@Column(name="index_file_path")
		private String indexFilePath;
		
		@Enumerated(EnumType.ORDINAL)
		@Column(name="arch_status")
		private ArchivalStatus archivalStatus;
		
		@Temporal(TemporalType.TIMESTAMP)
	    @Column(name = "created_tmstmp", nullable = false)
	    private Date createdTimestamp;

	    @PrePersist
	    protected void onCreate() {
	    	createdTimestamp = new Date();
	    }

		public DocumentDetails getDocumentDetails() {
			return documentDetails;
		}

		public void setDocumentDetails(DocumentDetails documentDetails) {
			this.documentDetails = documentDetails;
		}

		public String getArchiveFilePath() {
			return archiveFilePath;
		}

		public void setArchiveFilePath(String archiveFilePath) {
			this.archiveFilePath = archiveFilePath;
		}

		public String getIndexFilePath() {
			return indexFilePath;
		}

		public void setIndexFilePath(String indexFilePath) {
			this.indexFilePath = indexFilePath;
		}

		public ArchivalStatus getArchivalStatus() {
			return archivalStatus;
		}

		public void setArchivalStatus(ArchivalStatus archivalStatus) {
			this.archivalStatus = archivalStatus;
		}

		public Date getCreatedTimestamp() {
			return createdTimestamp;
		}

		public void setCreatedTimestamp(Date createdTimestamp) {
			this.createdTimestamp = createdTimestamp;
		}

		public long getArchivalId() {
			return archivalId;
		}


}
