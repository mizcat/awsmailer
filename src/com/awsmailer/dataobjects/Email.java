package com.awsmailer.dataobjects;

import java.util.List;

import javax.mail.internet.InternetAddress;


public class Email {
	private InternetAddress sender;
	private List<InternetAddress> recipients;
	private List<InternetAddress> bccRecipients;
	private List<InternetAddress> ccRecipients;
	private String subject;
	private StringBuffer contents;
	private String template;
	private Object contentObject; // used to populate dynamic fields template specified
	
	public InternetAddress getSender() {
		return sender;
	}
	public void setSender(InternetAddress sender) {
		this.sender = sender;
	}

	public List<InternetAddress> getBccRecipients() {
		return bccRecipients;
	}
	
	public void setBccRecipients(List<InternetAddress> bccRecipients) {
		this.bccRecipients = bccRecipients;
	}
	
	public List<InternetAddress> getCcRecipients() {
		return ccRecipients;
	}
	public void setCcRecipients(List<InternetAddress> ccRecipients) {
		this.ccRecipients = ccRecipients;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public StringBuffer getContents() {
			return contents;
	}
	
	public void setContents(StringBuffer contents) {
		this.contents = contents;
	}
	
	public List<InternetAddress> getRecipients() {
		return recipients;
	}
	
	public void setRecipients(List<InternetAddress> recipients) {
		this.recipients = recipients;
	}
	public String getTemplate() {
		return template;
	}
	public void setTemplate(String template) {
		this.template = template;
	}
	public Object getContentObject() {
		return contentObject;
	}
	public void setContentObject(Object contentObject) {
		this.contentObject = contentObject;
	}
	
	
	
}
