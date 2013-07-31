package com.awsmailer.test;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.mail.internet.InternetAddress;

import com.awsmailer.dataobjects.Email;
import com.awsmailer.factories.GenericMailerFactory;

public class TestGenericMailerFactory {
	
	public static final String TO = "{tester@test.com}";
	public static final String FROM = "{sender@test.com}"; /* be sure this sender is an approved sender in your aws ses console */
	private static final String SUBJECT = "Testing AWSMailer: Generic Mailer Factory";
	private static final String CONTENT = "Hi, you got Generic Mailer to work. Good job!";
	private static final String CC_1 = "{cc1@test.com}";
	private static final String CC_2 = "{cc2@test.com}";

    public static final Calendar currentCal = Calendar.getInstance();		
	public final Date today = currentCal.getTime();


	public static void main (String [] args) throws Exception {
		Email emailObj = new Email();
		emailObj.setSender(new InternetAddress(FROM));
		List<InternetAddress> recipients = new ArrayList();
		recipients.add(new InternetAddress(TO));
		emailObj.setRecipients(recipients);
		emailObj.setSubject(SUBJECT);
		emailObj.setContents(new StringBuffer(CONTENT));
		
		List<InternetAddress> bccRecipients = new ArrayList();
		bccRecipients.add(new InternetAddress(CC_1));
		bccRecipients.add(new InternetAddress(CC_2));
		
		emailObj.setBccRecipients(bccRecipients);
 		
		Boolean status = GenericMailerFactory.send(emailObj, "HTML");
		if(status)
			System.out.println("test completed successfully!");
		else
			System.out.println("test failed");
	    
	} // main
    
}

