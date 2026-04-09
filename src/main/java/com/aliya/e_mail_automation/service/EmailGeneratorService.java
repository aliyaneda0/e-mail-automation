package com.aliya.e_mail_automation.service;

import com.aliya.e_mail_automation.EmailRequest;
import org.springframework.stereotype.Service;

@Service
public class EmailGeneratorService {



    public String generateEmail(EmailRequest emailRequest) {

        //build prompt
        //Prepare JSON body
        //Send request
        //Extract Response


        private String buildPrompt(EmailRequest emailRequest) {

           StringBuilder prompt = new StringBuilder();
           prompt.append("Generate a professional email reply for the following email: ")

                   if(emailRequest.getTone() != null && !emailRequest.getTone().isEmpty()){
                       prompt.append("Use a ").append(emailRequest.getTone()).append(" tone.");
                       // Use a polite tone.
                   }

                   prompt.append("original Email: \n").append(emailRequest.getEmailContent()) ;

                   return prompt.toString();
        }

    }
}
