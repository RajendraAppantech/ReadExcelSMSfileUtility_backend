package com.sms.entity;

import java.util.Date;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

@Entity
@Table(name = "template_master")
public class TemplateMaster {

	@Id
	@Column(name = "template_id", nullable = false, length = 100)
	private String templateId;

	@Column(name = "template_name", nullable = false, length = 100)
	private String templateName;

	@Column(name = "template_type", nullable = false, length = 50)
	private String templateType;

	@Column(name = "sendor_id", nullable = false, length = 30)
	private String sendorId;

	@Column(name = "customer_code", nullable = false, length = 10)
	private String customerCode;

	@Column(name = "customer_name", nullable = false, length = 100)
	private String customerName;

	@Column(name = "entity_id", nullable = false, length = 50)
	private String entityId;

	@Column(name = "status", length = 10)
	private String status;

	@Column(name = "sms_content", nullable = false, length = 1024)
	private String smsContent;

	@Column(name = "sms_desc", length = 1024)
	private String smsDesc;

	@CreatedDate
	@Column(name = "created_date")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy HH:mm:ss", timezone = "Asia/Kolkata")
	@Temporal(TemporalType.TIMESTAMP)
	private Date createdDate;

	@CreatedBy
	@Column(name = "created_by", length = 100)
	private String createdBy;

	@Column(name = "template_key", length = 1024)
	private String templateKey;

	// Getters and Setters

	public String getTemplateId() {
		return templateId;
	}

	public String getTemplateKey() {
		return templateKey;
	}

	public void setTemplateKey(String templateKey) {
		this.templateKey = templateKey;
	}

	public void setTemplateId(String templateId) {
		this.templateId = templateId;
	}

	public String getTemplateName() {
		return templateName;
	}

	public void setTemplateName(String templateName) {
		this.templateName = templateName;
	}

	public String getTemplateType() {
		return templateType;
	}

	public void setTemplateType(String templateType) {
		this.templateType = templateType;
	}

	public String getSendorId() {
		return sendorId;
	}

	public void setSendorId(String sendorId) {
		this.sendorId = sendorId;
	}

	public String getCustomerCode() {
		return customerCode;
	}

	public void setCustomerCode(String customerCode) {
		this.customerCode = customerCode;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getEntityId() {
		return entityId;
	}

	public void setEntityId(String entityId) {
		this.entityId = entityId;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getSmsContent() {
		return smsContent;
	}

	public void setSmsContent(String smsContent) {
		this.smsContent = smsContent;
	}

	public String getSmsDesc() {
		return smsDesc;
	}

	public void setSmsDesc(String smsDesc) {
		this.smsDesc = smsDesc;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}
}
