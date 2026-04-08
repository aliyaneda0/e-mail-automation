package com.aliya.e_mail_automation.controller;


import com.aliya.e_mail_automation.service.EmailGeneratorService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/email")
@AllArgsConstructor
public class EmailGeneratorController {

    private final EmailGeneratorService emailGeneratorService;


    @PostMapping(" ")
    public ResponseEntity<String> generateEmail(){


    }


}
