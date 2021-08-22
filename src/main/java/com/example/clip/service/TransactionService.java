package com.example.clip.service;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.clip.model.dto.PaymentDto;
import com.example.clip.model.dto.UserDto;
import com.example.clip.model.entities.Payment;
import com.example.clip.model.enums.PaymentStatus;
import com.example.clip.repository.PaymentRepository;
import com.example.clip.repository.UserRepository;
import com.example.clip.request.PaymentRequest;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class TransactionService {

	 @Autowired
	 PaymentRepository paymentRepository;
	 
	 @Autowired
	 UserRepository userRepository;
	 
	 @Autowired 
	 ModelMapper mapper;
	 
	 public ResponseEntity<PaymentDto> create(PaymentRequest paymentRequest){
	 
	 var payment = new Payment();
     payment.setAmount(paymentRequest.getAmount());
     payment.setUserId(paymentRequest.getUserId());
     payment.setStatus(PaymentStatus.NEW.name());
  
     
         var createdPayment = paymentRepository.save(payment);
         log.info("Payload Created Successfully");
              return ResponseEntity.status(HttpStatus.CREATED).body(mapper.map(createdPayment, PaymentDto.class));
 }
	 
	 public ResponseEntity<List<UserDto>> getUserWithPayment(){
		 List<Long> userId = paymentRepository.findDistinctUserId(); 
		 List<UserDto> returnList = new ArrayList<>();
		 userId.stream().forEach(e-> 
			 returnList.add(mapper.map(userRepository.findById(e).get(),UserDto.class))
		 );	
		 
		 return ResponseEntity.status(HttpStatus.OK).body(returnList);
	 }
	 
}
