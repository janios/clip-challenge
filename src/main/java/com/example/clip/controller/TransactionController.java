package com.example.clip.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.clip.model.dto.PaymentDto;
import com.example.clip.model.dto.UserDto;
import com.example.clip.request.PaymentRequest;
import com.example.clip.service.TransactionService;



@RestController
@RequestMapping("/api/clip")
public class TransactionController {

    @Autowired
    TransactionService transactionService;


    @RequestMapping(value = "/createPayload", method = RequestMethod.POST)
    public ResponseEntity<PaymentDto> create(@RequestBody PaymentRequest paymentRequest) {
    	return transactionService.create(paymentRequest);
       
    }
    
    @GetMapping(value="/usersPayments")
    public ResponseEntity<List<UserDto>> getUserWithPayment(){
    	return transactionService.getUserWithPayment();
    }

}
