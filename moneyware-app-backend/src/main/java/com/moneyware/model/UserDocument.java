package com.moneyware.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class UserDocument {

	@JsonProperty("userName")
	private String userName;

	@JsonProperty("documentTyp")
	private String documentTyp;

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getDocumentType() {
		return documentTyp;
	}

	public void setDocumentType(String documentTyp) {
		this.documentTyp = documentTyp;
	}

	@Override
	public String toString() {
		return "UserDocument [userName=" + userName + ", documentTyp=" + documentTyp + "]";
	}

}
