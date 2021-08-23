 package com.example.clip.controller;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.List;

import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.example.clip.model.dto.DisbursementDto;
import com.example.clip.model.dto.PaymentDto;
import com.example.clip.model.dto.ReportDto;
import com.example.clip.model.dto.UserDto;
import com.example.clip.service.TransactionService;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@RunWith(SpringRunner.class)
@WebMvcTest(TransactionController.class)
public class TransactionControllerTest {

	@MockBean
	private TransactionService service;
	
	@Autowired
	private MockMvc mockMvc;
	
	private static PaymentDto paymentDto;
	private static List<UserDto> usersDto;
	private static List<DisbursementDto> disbursmentsDto;
	private static ReportDto reportdto;
	
	@BeforeClass
	public static void init() throws IOException {
		
		paymentDto = new ObjectMapper().readValue(Paths.get("src/test/resources/transactions.json").toFile(),
				new TypeReference<PaymentDto>() {
				});
		
		
	}
	
}
