 package com.example.clip.controller;

import static org.mockito.Mockito.doReturn;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.nio.file.Paths;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import com.example.clip.model.dto.DisbursementDto;
import com.example.clip.model.dto.PaymentDto;
import com.example.clip.model.dto.ReportDto;
import com.example.clip.model.dto.UserDto;
import com.example.clip.request.PaymentRequest;
import com.example.clip.service.TransactionService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

@ExtendWith(SpringExtension.class)
@WebMvcTest(TransactionController.class)
public class TransactionControllerTest {

	@MockBean
	private TransactionService service;
	
	@Autowired
	private MockMvc mockMvc;
	
	private static PaymentDto paymentDto;
	private static List<UserDto> usersDto;
	private static List<DisbursementDto> disbursmentsDto;
	private static ReportDto reportDto;
	private static PaymentRequest paymentRequest;
	private static final String USERS_WITH_PAYMENTS_ENDPOINT= "/api/clip/usersPayments";
	private static final String DISBURSEMENT_PROCESS_ENDPOINT= "/api/clip/disbursementProcess";
	private static final String REPORT_ENDPOINT = "/api/clip/report/1";
	private static final String PAYMENT_ENDPOINT = "/api/clip/createPayload";
	
		
	@Test
	public void getUsersWithPaymentsTest() throws Exception {
		usersDto = new ObjectMapper().readValue(Paths.get("src/test/resources/usersWithPayments.json").toFile(),
				new TypeReference<List<UserDto>>() {
				});
		usersDto = new ObjectMapper().readValue(Paths.get("src/test/resources/usersWithPayments.json").toFile(),
				new TypeReference<List<UserDto>>() {
				});
		doReturn(usersDto).when(service).getUserWithPayment();
		this.mockMvc.perform(get(USERS_WITH_PAYMENTS_ENDPOINT).accept(MediaType.APPLICATION_JSON))
		     .andDo(print()).andExpect(status().isOk());
		
	}
	
	@Test
	public void processDisbursementNotAutenticatedTest() throws Exception {
		disbursmentsDto = new ObjectMapper().readValue(Paths.get("src/test/resources/disbursement.json").toFile(),
				new TypeReference<List<DisbursementDto>>() {
				});
		doReturn(disbursmentsDto).when(service).disbursementProcess();
		this.mockMvc.perform(post(DISBURSEMENT_PROCESS_ENDPOINT).accept(MediaType.APPLICATION_JSON))
		.andDo(print()).andExpect(status().isUnauthorized());
	}
	
	@Test
	@WithMockUser(username = "clip", password = "clip-pass")
	public void processDisbursementAutenticatedTest() throws Exception {
		disbursmentsDto = new ObjectMapper().readValue(Paths.get("src/test/resources/disbursement.json").toFile(),
				new TypeReference<List<DisbursementDto>>() {
				});
		doReturn(disbursmentsDto).when(service).disbursementProcess();
		this.mockMvc.perform(post(DISBURSEMENT_PROCESS_ENDPOINT).accept(MediaType.APPLICATION_JSON))
		.andDo(print()).andExpect(status().isCreated());
	}
	
	@Test
	public void getReportTest() throws Exception{
		reportDto = new ObjectMapper().readValue(Paths.get("src/test/resources/report.json").toFile(),
				new TypeReference<ReportDto>() {
				});
		doReturn(reportDto).when(service).getReportByIdUser(ArgumentMatchers.eq(1L));
		this.mockMvc.perform(get(REPORT_ENDPOINT).accept(MediaType.APPLICATION_JSON))
		.andDo(print()).andExpect(status().isOk());
	}
	
	@Test
	public void createPayLoadTest() throws JsonProcessingException, Exception{
		paymentDto = new ObjectMapper().readValue(Paths.get("src/test/resources/paymentDto.json").toFile(),
				new TypeReference<PaymentDto>() {
				});
		paymentRequest = new ObjectMapper().readValue(Paths.get("src/test/resources/paymentRequest.json").toFile(),
				new TypeReference<PaymentRequest>() {
				});
		doReturn(paymentDto).when(service).create(paymentRequest);
		this.mockMvc.perform(post(PAYMENT_ENDPOINT).contentType(MediaType.APPLICATION_JSON)
				.content(new ObjectMapper().writeValueAsString(paymentRequest)).accept(MediaType.APPLICATION_JSON))
				.andDo(print()).andExpect(status().isCreated());
	}
	
}
