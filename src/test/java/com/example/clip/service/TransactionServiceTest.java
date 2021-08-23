package com.example.clip.service;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.List;

import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;
import org.modelmapper.ModelMapper;

import com.example.clip.config.ClipConfiguration;
import com.example.clip.model.dto.DisbursementDto;
import com.example.clip.model.dto.PaymentDto;
import com.example.clip.model.dto.ReportDto;
import com.example.clip.model.dto.UserDto;
import com.example.clip.model.entities.Payment;
import com.example.clip.repository.DisbursementRepository;
import com.example.clip.repository.PaymentRepository;
import com.example.clip.repository.UserRepository;
import com.example.clip.request.PaymentRequest;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

@RunWith(MockitoJUnitRunner.class)
public class TransactionServiceTest {

	@Mock
	PaymentRepository paymentRepository;
	
	@Mock
	UserRepository userRepository;
	
	@Mock
	DisbursementRepository disbursementRepository;
	
	@Mock
	ClipConfiguration clipConfiguration;
	
	@Spy
	ModelMapper mapper;
	
	@InjectMocks
	private TransactionService service;
	
	private static Payment payment;
	private static List<UserDto> usersDto;
	private static List<DisbursementDto> disbursmentsDto;
	private static ReportDto reportDto;
	private static PaymentRequest paymentRequest;
	
	@Before
	public void init() throws IOException{
		payment = new ObjectMapper().readValue(Paths.get("src/test/resources/paymentDto.json").toFile(),
				new TypeReference<Payment>() {
				});
		usersDto = new ObjectMapper().readValue(Paths.get("src/test/resources/usersWithPayments.json").toFile(),
				new TypeReference<List<UserDto>>() {
				});
		disbursmentsDto = new ObjectMapper().readValue(Paths.get("src/test/resources/disbursement.json").toFile(),
				new TypeReference<List<DisbursementDto>>() {
				});
		reportDto = new ObjectMapper().readValue(Paths.get("src/test/resources/disbursement.json").toFile(),
				new TypeReference<ReportDto>() {
				});
		paymentRequest = new ObjectMapper().readValue(Paths.get("src/test/resources/paymentRequest.json").toFile(),
				new TypeReference<PaymentRequest>() {
				});
	}
	
	
	@Test
	public void createTest() {
		when(paymentRepository.save(payment)).thenReturn(payment);
		PaymentDto paymendto = service.create(paymentRequest);
		assertNotNull(paymendto);
	}
	
	
	
}
