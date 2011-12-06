package com.bluevia.demo;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

import javax.xml.bind.JAXBException;

import com.bluevia.java.AbstractClient.Mode;
import com.bluevia.java.sms.MessageMT;
import com.bluevia.java.exception.BlueviaException;
import com.bluevia.java.oauth.AccessToken;
import com.bluevia.java.oauth.OAuthToken;
import com.bluevia.java.oauth.RequestToken;
import com.telefonica.schemas.unica.rest.sms.v1.DeliveryInformationType;
import com.telefonica.schemas.unica.rest.sms.v1.SMSDeliveryStatusType;


public class demoSMS2 {

	/**
	 * @param args
	 * @throws JAXBException 
	 * @throws BlueviaException 
	 * @throws IOException 
	 */
	public static void main(String[] args) throws JAXBException, BlueviaException, IOException {
		// App keys
		OAuthToken consumer = new OAuthToken("Pk11120623706811", "bPfx34274197");
		// Get request token
		RequestToken rt = new RequestToken();
		OAuthToken requestToken = rt.getRequestToken(consumer, null);
		System.out.println("Request token id : " + requestToken.getToken());
		System.out.println("Activate you app at : " + requestToken.getUrl());

		// Get activation code from the user
		System.out.print("Enter activation code : ");
	    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	    String activationCode = br.readLine();

		// Get keys from the user
	    AccessToken at = new AccessToken();
	    OAuthToken accessToken = at.get(consumer, requestToken, activationCode);
	    System.out.println("User key : " + accessToken.getToken());
	    System.out.println("User secret : " + accessToken.getSecret());
	    	    
		// Create SMS Sender client
		MessageMT smsSender = new MessageMT(consumer, accessToken, Mode.SANDBOX);	
		
		// Send message
		String recipients[] = { "34650119986" };
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
