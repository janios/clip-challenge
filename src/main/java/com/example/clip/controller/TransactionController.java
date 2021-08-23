package com.example.clip.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.clip.model.dto.DisbursementDto;
import com.example.clip.model.dto.PaymentDto;
import com.example.clip.model.dto.ReportDto;
import com.example.clip.model.dto.UserDto;
import com.example.clip.request.PaymentRequest;
import com.example.clip.service.TransactionService;



@RestController
@RequestMapping("/api/clip")
public class TransactionController {

    @Autowired
    TransactionService transactionService;


    @PostMapping(value = "/createPayload")
    public ResponseEntity<PaymentDto> create(@RequestBody PaymentRequest paymentRequest) {
    	return new ResponseEntity<>(transactionService.create(paymentRequest), HttpStatus.CREATED);
       
    }
    
    @GetMapping(value="/usersPayments")
    public ResponseEntity<List<UserDto>> getUserWithPayment(){
    	return new ResponseEntity<>(transactionService.getUserWithPayment(), HttpStatus.OK);
    }
    
    @PostMapping(value="/disbursementProcess")
    public ResponseEntity<List<DisbursementDto>> processDisbursement(){
    	return new ResponseEntity<>(transactionService.disbursementProcess(), HttpStatus.CREATED);
    }
    
    @GetMapping(value="/report/{id}")
    public ResponseEntity<ReportDto> getReport(@PathVariable Long id){
    	return new ResponseEntity<>(transactionService.getReportByIdUser(id), HttpStatus.OK);
    }
    

}
