package com.example.clip.service;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;

import org.assertj.core.util.Lists;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import com.example.clip.config.ClipConfiguration;
import com.example.clip.model.dto.DisbursementDto;
import com.example.clip.model.dto.PaymentDto;
import com.example.clip.model.dto.ReportDto;
import com.example.clip.model.dto.UserDto;
import com.example.clip.model.entities.Disbursement;
import com.example.clip.model.entities.Payment;
import com.example.clip.model.entities.User;
import com.example.clip.model.enums.PaymentStatus;
import com.example.clip.repository.DisbursementRepository;
import com.example.clip.repository.PaymentRepository;
import com.example.clip.repository.UserRepository;
import com.example.clip.request.PaymentRequest;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

@ExtendWith(MockitoExtension.class)
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
	private static User user;
	private static Disbursement disbursement;
	private static PaymentRequest paymentRequest;

	@BeforeEach
	public void init() throws IOException {
		payment = new ObjectMapper().readValue(Paths.get("src/test/resources/paymentDto.json").toFile(),
				new TypeReference<Payment>() {
				});
		disbursement = new ObjectMapper().readValue(Paths.get("src/test/resources/disbursementOnly.json").toFile(),
				new TypeReference<Disbursement>() {
		});
		paymentRequest = new ObjectMapper().readValue(Paths.get("src/test/resources/paymentRequest.json").toFile(),
				new TypeReference<PaymentRequest>() {
				});
		user = new ObjectMapper().readValue(Paths.get("src/test/resources/user.json").toFile(),
				new TypeReference<User>() {
				});
	}

	@Test
	public void createTest() {
		when(userRepository.findById(1L)).thenReturn(Optional.of(user));
		when(paymentRepository.save(any(Payment.class))).thenReturn(payment);
		PaymentDto paymentdto = service.create(paymentRequest);
		assertNotNull(paymentdto);
		assertEquals(BigDecimal.valueOf(1000), paymentdto.getAmount());
	}

	@Test
	public void getUserWithPaymentTest() {
		when(paymentRepository.findDistinctUserId()).thenReturn(Lists.newArrayList(1L));
		when(userRepository.findById(1L)).thenReturn(Optional.of(user));
		List<UserDto> result = service.getUserWithPayment();
		assertNotNull(result);
		assertEquals(1, result.size());

	}
	
	@Test
	public void disbursementProcessTest() {
		when(paymentRepository.findByStatus(PaymentStatus.NEW.name())).thenReturn(Lists.newArrayList(payment));
		when(disbursementRepository.save(any(Disbursement.class))).thenReturn(disbursement);
		when(clipConfiguration.getDisbursmentFee()).thenReturn(BigDecimal.valueOf(1.035));
		List<DisbursementDto> result = service.disbursementProcess();
		assertNotNull(result);
		assertEquals(1, result.size());
	}
	
	@Test
	public void reportByIdUserTest() {
		when(userRepository.findById(1L)).thenReturn(Optional.of(user));
		when(paymentRepository.findByUserId(1L)).thenReturn(Lists.newArrayList(payment));
		ReportDto result = service.getReportByIdUser(1L);
		assertNotNull(result);
		assertEquals(1, result.getPayments_sum());
	}

}
