package com.bluevia.demo;

import java.util.List;

import javax.xml.bind.JAXBException;

import com.bluevia.java.AbstractClient.Mode;
import com.bluevia.java.sms.MessageMT;
import com.bluevia.java.exception.BlueviaException;
import com.bluevia.java.oauth.OAuthToken;
import com.telefonica.schemas.unica.rest.sms.v1.DeliveryInformationType;
import com.telefonica.schemas.unica.rest.sms.v1.SMSDeliveryStatusType;


public class demoSMS {

	/**
	 * @param args
	 * @throws JAXBException 
	 * @throws BlueviaException 
	 */
	public static void main(String[] args) throws JAXBException, BlueviaException {
		// App keys
		OAuthToken consumer = new OAuthToken("IX11100175872028", "wWRA27497724");
		// User keys
		OAuthToken accesstoken = new OAuthToken("4359366727d5de1f18037cf409701ef6", "ff2fe570784ff0ce7b374acec8b593a6");
		// Create SMS Sender client
		MessageMT smsSender = new MessageMT(consumer, accesstoken, Mode.SANDBOX);	
		
		// Send message
		String recipients[] = { "5721100001" };
		String messageId = smsSender.send(recipients, "hola BlueVia");
		System.out.println("Message id : " + messageId);
		
		// Get deliveryStatus
		SMSDeliveryStatusType status = smsSender.getStatus(messageId);
		List<DeliveryInformationType> ldi = status.getSmsDeliveryStatus();
		for (DeliveryInformationType dit : ldi) {
			System.out.println("Delivery status : " + dit.getDeliveryStatus());
		} 
		
	}

	
	
		
		
		
	
	
}
