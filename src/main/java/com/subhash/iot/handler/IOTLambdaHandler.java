package com.subhash.iot.handler;

import java.net.URI;
import java.net.URISyntaxException;

import com.amazonaws.services.lambda.runtime.Context;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.subhash.iot.model.IOTButtonRequest;
import com.subhash.iot.model.IOTResponse;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Call;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;

public class IOTLambdaHandler {
	public static final String ACCOUNT_SID ="ACd676961592d88f4793e74cc146cab7e7";
    public static final String AUTH_TOKEN ="f1a35c0e11c67c569354d30f0aff5fc7";

	public IOTResponse handleRequest(IOTButtonRequest request, Context context) throws JsonProcessingException, URISyntaxException {
		System.out.println("Subhash Is TESTING from AWS BUTTON");
		System.out.println("Subhash Is TESTING from AWS BUTTON - REQUEST is - " + request);
		
		
		Twilio.init(ACCOUNT_SID, AUTH_TOKEN);

//        Message message = Message
//                .creator(new PhoneNumber("+16177945452"), // to
//                        new PhoneNumber("+13366630430"), // from
//                        "Subhash Is Testing IOT")
//                .create();
        
        Call call = Call.creator(new PhoneNumber("+13366625449"), new PhoneNumber("+13366630430"),
                new URI("https://handler.twilio.com/twiml/EHa8555e6a0d1bbdfee81cfba150211384")).create();

		return new IOTResponse();
		
		
	}
}
