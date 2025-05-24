package com.sms.entity;

import java.math.BigDecimal;
import java.util.Date;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.DateSerializer;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

@Entity(name = "customer_details")
@Table(name = "customer_details")
public class CustomerDetails {

	@Id
	@Column(name = "customer_code", nullable = false, length = 10)
	private String customerCode;

	@Column(name = "name")
	private String name;

	@Column(name = "email_id")
	private String emailId;

	@Column(name = "addr1")
	private String address1;

	@Column(name = "addr2")
	private String address2;

	@Column(name = "addr3")
	private String address3;

	@Column(name = "city")
	private String city;

	@Column(name = "state")
	private String state;

	@Column(name = "pin_code")
	private String pinCode;

	@Column(name = "first_contact_person_name")
	private String firstContactPersonName;

	@Column(name = "first_contact_person_mobile_no")
	private String firstContactPersonMobileNo;

	@Column(name = "second_contact_person_name")
	private String secondContactPersonName;

	@Column(name = "second_contact_person_mobile_no")
	private String secondContactPersonMobileNo;

	@Column(name = "username")
	private String username;

	@Column(name = "auth_key")
	private String authKey;

	@Column(name = "bank_name", nullable = false, length = 100)
	private String bankName;

	@Column(name = "account_no", nullable = false, length = 35)
	private String accountNo;

	@Column(name = "ifsc_code", nullable = false, length = 11)
	private String ifscCode;

	@Column(name = "rate", precision = 16, scale = 2)
	private BigDecimal rate;

	@Column(name = "rate_date")
	@Temporal(TemporalType.TIMESTAMP)
	private Date rateDate;

	@Column(name = "sms_balance")
	private Long smsBalance;

	@Column(name = "status", length = 10)
	private String status;

	@Column(name = "last_txn_update_by", length = 100)
	private String lastTxnUpdateBy;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "last_txn_update_date")
	private Date lastTxnUpdateDate;

	@CreatedBy
	@Column(name = "created_by")
	private String createdBy;

	@CreatedDate
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "created_dt")
	@JsonSerialize(using = DateSerializer.class)
	private Date createdDate;

	@LastModifiedBy
	@Column(name = "modify_by", length = 100)
	private String modifyBy;

	@LastModifiedDate
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "modify_dt")
	@JsonSerialize(using = DateSerializer.class)
	private Date modifyDt;

	@Column(name = "auth_by")
	private String authBy;

	@Column(name = "auth_dt")
	@Temporal(TemporalType.TIMESTAMP)
	@JsonSerialize(using = DateSerializer.class)
	private Date authDate;

	@Column(name = "auth_status")
	private String authStatus;

	@Column(name = "entity_id", length = 100)
	private String entityId;

	@Column(name = "entity_status", length = 20)
	private String entityStatus;

	@Column(name = "entity_date")
	@Temporal(TemporalType.TIMESTAMP)
	@JsonSerialize(using = DateSerializer.class)
	private Date entityDate;

	@Column(name = "entity_name", length = 100)
	private String entityName;

	public String getCustomerCode() {
		return customerCode;
	}

	public void setCustomerCode(String customerCode) {
		this.customerCode = customerCode;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public String getAddress1() {
		return address1;
	}

	public void setAddress1(String address1) {
		this.address1 = address1;
	}

	public String getAddress2() {
		return address2;
	}

	public void setAddress2(String address2) {
		this.address2 = address2;
	}

	public String getAddress3() {
		return address3;
	}

	public void setAddress3(String address3) {
		this.address3 = address3;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getPinCode() {
		return pinCode;
	}

	public void setPinCode(String pinCode) {
		this.pinCode = pinCode;
	}

	public String getFirstContactPersonName() {
		return firstContactPersonName;
	}

	public void setFirstContactPersonName(String firstContactPersonName) {
		this.firstContactPersonName = firstContactPersonName;
	}

	public String getFirstContactPersonMobileNo() {
		return firstContactPersonMobileNo;
	}

	public void setFirstContactPersonMobileNo(String firstContactPersonMobileNo) {
		this.firstContactPersonMobileNo = firstContactPersonMobileNo;
	}

	public String getSecondContactPersonName() {
		return secondContactPersonName;
	}

	public void setSecondContactPersonName(String secondContactPersonName) {
		this.secondContactPersonName = secondContactPersonName;
	}

	public String getSecondContactPersonMobileNo() {
		return secondContactPersonMobileNo;
	}

	public void setSecondContactPersonMobileNo(String secondContactPersonMobileNo) {
		this.secondContactPersonMobileNo = secondContactPersonMobileNo;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getAuthKey() {
		return authKey;
	}

	public void setAuthKey(String authKey) {
		this.authKey = authKey;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public String getModifyBy() {
		return modifyBy;
	}

	public void setModifyBy(String modifyBy) {
		this.modifyBy = modifyBy;
	}

	public Date getModifyDt() {
		return modifyDt;
	}

	public void setModifyDt(Date modifyDt) {
		this.modifyDt = modifyDt;
	}

	public String getAuthBy() {
		return authBy;
	}

	public void setAuthBy(String authBy) {
		this.authBy = authBy;
	}

	public Date getAuthDate() {
		return authDate;
	}

	public void setAuthDate(Date authDate) {
		this.authDate = authDate;
	}

	public String getAuthStatus() {
		return authStatus;
	}

	public void setAuthStatus(String authStatus) {
		this.authStatus = authStatus;
	}

	public BigDecimal getRate() {
		return rate;
	}

	public void setRate(BigDecimal rate) {
		this.rate = rate;
	}

	public Date getRateDate() {
		return rateDate;
	}

	public void setRateDate(Date rateDate) {
		this.rateDate = rateDate;
	}

	public Long getSmsBalance() {
		return smsBalance;
	}

	public void setSmsBalance(Long smsBalance) {
		this.smsBalance = smsBalance;
	}

	public String getLastTxnUpdateBy() {
		return lastTxnUpdateBy;
	}

	public void setLastTxnUpdateBy(String lastTxnUpdateBy) {
		this.lastTxnUpdateBy = lastTxnUpdateBy;
	}

	public Date getLastTxnUpdateDate() {
		return lastTxnUpdateDate;
	}

	public void setLastTxnUpdateDate(Date lastTxnUpdateDate) {
		this.lastTxnUpdateDate = lastTxnUpdateDate;
	}

	public String getBankName() {
		return bankName;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
	}

	public String getAccountNo() {
		return accountNo;
	}

	public void setAccountNo(String accountNo) {
		this.accountNo = accountNo;
	}

	public String getIfscCode() {
		return ifscCode;
	}

	public void setIfscCode(String ifscCode) {
		this.ifscCode = ifscCode;
	}

	public String getEntityId() {
		return entityId;
	}

	public void setEntityId(String entityId) {
		this.entityId = entityId;
	}

	public String getEntityStatus() {
		return entityStatus;
	}

	public void setEntityStatus(String entityStatus) {
		this.entityStatus = entityStatus;
	}

	public Date getEntityDate() {
		return entityDate;
	}

	public void setEntityDate(Date entityDate) {
		this.entityDate = entityDate;
	}

	public String getEntityName() {
		return entityName;
	}

	public void setEntityName(String entityName) {
		this.entityName = entityName;
	}

}