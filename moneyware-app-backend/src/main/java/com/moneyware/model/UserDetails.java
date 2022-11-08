package com.moneyware.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="user_details")
public class UserDetails {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="cust_id")
	private long custId;
	
	@Column(name="cust_name", unique=true, nullable=false)
	private String custName;
	
	@Column(name="cust_pass", unique=true, nullable=false) //To:do hashing
	private String custPass;

	public long getCustId() {
		return custId;
	}

	public void setCustId(long custId) {
		this.custId = custId;
	}

	public String getCustName() {
		return custName;
	}

	public void setCustName(String custName) {
		this.custName = custName;
	}

	public String getCustPass() {
		return custPass;
	}

	public void setCustPass(String custPass) {
		this.custPass = custPass;
	}

	@Override
	public String toString() {
		return "UserDetails [custId=" + custId + ", custName=" + custName + ", custPass=" + custPass + "]";
	}
	
	
	
}
