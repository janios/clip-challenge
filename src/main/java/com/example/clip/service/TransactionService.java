package com.example.clip.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.clip.model.dto.PaymentDto;
import com.example.clip.model.entities.Payment;
import com.example.clip.repository.PaymentRepository;
import com.example.clip.request.PaymentRequest;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class TransactionService {

	 @Autowired
	 PaymentRepository paymentRepository;
	 
	 @Autowired 
	 ModelMapper mapper;
	 
	 public ResponseEntity<Object> create(PaymentRequest paymentRequest){
	 
	 var payment = new Payment();
     payment.setAmount(paymentRequest.getAmount());
     payment.setUserId(paymentRequest.getUserId());
     
         var createdPayment = paymentRepository.save(payment);
         log.info("Payload Created Successfully");
              return ResponseEntity.status(HttpStatus.CREATED).body(mapper.map(createdPayment, PaymentDto.class));
    
     
	 }
}
