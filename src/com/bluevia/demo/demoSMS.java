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
		OAuthToken consumer = new OAuthToken("Pk11120623706811", "bPfx34274197");
		// User keys
		OAuthToken accesstoken = new OAuthToken("a7061a98feb5ec1a348cc3edaff104e9", "84b5be2f01b79d1e0f6d8da19ed610e7");
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
