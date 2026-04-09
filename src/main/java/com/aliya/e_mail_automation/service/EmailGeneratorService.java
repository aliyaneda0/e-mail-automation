package com.aliya.e_mail_automation.service;

import com.aliya.e_mail_automation.EmailRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
public class EmailGeneratorService {

    private final WebClient webClient;
    private final String apiKey;

    public EmailGeneratorService(WebClient.Builder webClientBuilder,
                                 @Value("${gemini.api.url}") String baseUrl,
                                 @Value("${gemini.api.key}") String geminiApiKey)
    {    
        this.apiKey = geminiApiKey;
        this.webClient = webClientBuilder.baseUrl(baseUrl)
                         .build();
    }


    public String generateEmail(EmailRequest emailRequest) {

        //build prompt
        String prompt = buildPrompt(emailRequest);
        //Prepare JSON body

        String requestBody = String.format("""
                {
                     "contents" : [
                     
                          {
                            "parts":[
                               {
                                  "text": "%s"
                                }
                            ]
                          }
                     ]
                
                }""", prompt);



        //Send request

         String response = webClient.post()
                 .uri(uriBuilder -> uriBuilder
                         .path("/v1beta/models/gemini-2.0-flash-exp:generateContent")
                         .build())
                 .header("x-goog-api-key",apiKey)
                 .header("Content-Type","application/json")
                 .bodyValue(requestBody)
                 .retrieve()
                 .bodyToMono(String.class)
                 .block();

        //Extract Response


    }

        private String buildPrompt(EmailRequest emailRequest) {

           StringBuilder prompt = new StringBuilder();
           prompt.append("Generate a professional email reply for the following email: ");

                   if(emailRequest.getTone() != null && !emailRequest.getTone().isEmpty()){
                       prompt.append("Use a ").append(emailRequest.getTone()).append(" tone.");
                       // Use a polite tone.
                   }

                   prompt.append("original Email: \n").append(emailRequest.getEmailContent()) ;

                   return prompt.toString();
        }


}
