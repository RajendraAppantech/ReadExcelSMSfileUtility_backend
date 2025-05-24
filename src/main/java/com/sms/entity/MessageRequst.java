package com.sms.entity;

public class MessageRequst {
	public String senderId;
	public String entityId;
	public String templateId;
	public String mobileNo;
	public String body;
	public String authKey;
	
	public MessageRequst() {
		super();
		// TODO Auto-generated constructor stub
	}

	public MessageRequst(String senderId, String entityId, String templateId, String mobileNo, String body) {
        this.senderId = senderId;
        this.entityId = entityId;
        this.templateId = templateId;
        this.mobileNo = mobileNo;
        this.body = body;
    }

    @Override
    public String toString() {
        return "SenderId: " + senderId + ", EntityId: " + entityId + ", TemplateId: " + templateId +
                ", MobileNo: " + mobileNo + ", Body: " + body;
    }
}