package com.subhash.iot.handler;

import java.net.URI;
import java.net.URISyntaxException;

import com.amazonaws.services.lambda.runtime.Context;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.subhash.iot.model.IOTButtonRequest;
import com.subhash.iot.model.IOTResponse;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Call;
import com.twilio.type.PhoneNumber;

public class IOTLambdaHandler {
	public static final String ACCOUNT_SID ="Test";
    public static final String AUTH_TOKEN ="Test";
    
    public static final String SubhashTwilioNumber = "+13366630430";
    
    public static final String Dial_Subhash_TWIML = "https://handler.twilio.com/twiml/EHde789dba73dd59ceef20cf8ce8d7b419"; 
    public static final String Dial_Arch_CustomerService_TWIML = "https://handler.twilio.com/twiml/EH118797e3a20fe90527254a53c2797564";
    public static final String Dial_Jonathan_TWIML = "https://handler.twilio.com/twiml/EHfafa5bc372ba6598e94d63e08d56aced";
    public static final String Dial_Andrew_TWIML = "https://handler.twilio.com/twiml/EHb52a1a56c54d9b639b8a9812eb3f52be";
    public static final String Dial_SubhashDesk_TWIML ="https://handler.twilio.com/twiml/EH04403bc1cc3e394dd305dfa0714f6f5b";

	public IOTResponse handleRequest(IOTButtonRequest request, Context context) throws JsonProcessingException, URISyntaxException {

		System.out.println("Subhash Is TESTING from AWS BUTTON - REQUEST is - " + request);
		
		Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
		
		String singleClkFromPhNum = System.getenv("SINGLECLK_From_PHNum");
		String doubleClkFromPhNum = System.getenv("DOUBLECLK_From_PHNum");
		
		if(singleClkFromPhNum == null || "".equals(singleClkFromPhNum)) {
			singleClkFromPhNum = "+16177945452";
		}
		
		if(doubleClkFromPhNum == null || "".equals(doubleClkFromPhNum)) {
			doubleClkFromPhNum = "+16177945452";
		}
		
		System.out.println("SINGLE Click FROM Phone Number : " + singleClkFromPhNum);
		System.out.println("DOUBLE Click FROM Phone Number : " + doubleClkFromPhNum);

		if("SINGLE".equalsIgnoreCase(request.getClickType())){
	        Call call = Call.creator(new PhoneNumber(singleClkFromPhNum), new PhoneNumber(SubhashTwilioNumber),
	                new URI(Dial_Arch_CustomerService_TWIML)).create();
	        System.out.println("Call Successfully Placed FOR SINGLE CLICK - " + call.getStatus().toString());
		} 
		
		else if("DOUBLE".equalsIgnoreCase(request.getClickType())){
	        Call call = Call.creator(new PhoneNumber(doubleClkFromPhNum), new PhoneNumber(SubhashTwilioNumber),
	                new URI(Dial_SubhashDesk_TWIML)).create();
	        
	        System.out.println("Call Successfully Placed FOR DOUBLE CLICK - " + call.getStatus().toString());
		} else {
			Call call = Call.creator(new PhoneNumber("+16177945452"), new PhoneNumber(SubhashTwilioNumber),
	                new URI(Dial_Subhash_TWIML)).create();
			System.out.println("Call Successfully Placed. NONE OF CLICK TYPES MATCHED - " + call.getStatus().toString());
		}

		return new IOTResponse();
		
		
	}
}
