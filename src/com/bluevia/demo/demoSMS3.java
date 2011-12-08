package com.bluevia.demo;

import java.util.List;

import javax.xml.bind.JAXBException;

import com.bluevia.java.AbstractClient.Mode;
import com.bluevia.java.sms.MessageMO;
import com.bluevia.java.sms.MessageMT;
import com.bluevia.java.exception.BlueviaException;
import com.bluevia.java.oauth.OAuthToken;
import com.telefonica.schemas.unica.rest.sms.v1.DeliveryInformationType;
import com.telefonica.schemas.unica.rest.sms.v1.ReceivedSMSType;
import com.telefonica.schemas.unica.rest.sms.v1.SMSDeliveryStatusType;
import com.telefonica.schemas.unica.rest.sms.v1.SMSMessageType;


public class demoSMS3 {

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
		
		// Send test message
		String recipients[] = { "217040" };
		String messageId = smsSender.send(recipients, "hola AppCircus Academy!");
		System.out.println("Message id : " + messageId);

		// Create SMS Receiver client
		MessageMO mo = new MessageMO(consumer, accesstoken, Mode.SANDBOX);

		// Set up shortcode to receive messages from
		ReceivedSMSType receivedMessages = mo.getMessages("217040");

		// Get messages
		List<SMSMessageType> lm = receivedMessages.getReceivedSMS();

		// Print messages
		for (SMSMessageType sms : lm) {
			System.out.println(sms.getMessage());
		}

		
	}

	
	
		
		
		
	
	
}
