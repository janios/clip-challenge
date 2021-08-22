package com.example.clip.service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.example.clip.config.ClipConfiguration;
import com.example.clip.model.dto.DisbursementDto;
import com.example.clip.model.dto.PaymentDto;
import com.example.clip.model.dto.ReportDto;
import com.example.clip.model.dto.UserDto;
import com.example.clip.model.entities.Disbursement;
import com.example.clip.model.entities.Payment;
import com.example.clip.model.enums.PaymentStatus;
import com.example.clip.repository.DisbursementRepository;
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
	DisbursementRepository disbursementRepository;

	@Autowired
	ClipConfiguration clipConfiguration;

	@Autowired
	ModelMapper mapper;

	public PaymentDto create(PaymentRequest paymentRequest) {

		userRepository.findById(paymentRequest.getUserId())
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
						String.format("User with the id  %d does not exists", paymentRequest.getUserId())));

		var payment = new Payment();
		payment.setAmount(paymentRequest.getAmount());
		payment.setUserId(paymentRequest.getUserId());
		payment.setStatus(PaymentStatus.NEW.name());

		var createdPayment = paymentRepository.save(payment);
		log.info("Payload Created Successfully");
		return mapper.map(createdPayment, PaymentDto.class);
	}

	public List<UserDto> getUserWithPayment() {
		List<Long> userId = paymentRepository.findDistinctUserId();
		List<UserDto> returnList = new ArrayList<>();
		userId.stream().forEach(e -> returnList.add(mapper.map(userRepository.findById(e).get(), UserDto.class)));
		return returnList;
	}

	@Transactional(rollbackOn = { DataAccessException.class, SQLException.class })
	public List<DisbursementDto> disbursementProcess() {
		List<Payment> newPayments = paymentRepository.findByStatus(PaymentStatus.NEW.name());
		List<DisbursementDto> response = new ArrayList<>();
		newPayments.stream().forEach(p -> {
			Disbursement current = new Disbursement();
			current.setAmount(p.getAmount().divide(clipConfiguration.getDisbursmentFee(), 2, RoundingMode.HALF_UP));
			current.setUserId(p.getUserId());
			current.setPaymentId(p.getId());
			current = disbursementRepository.save(current);
			p.setStatus(PaymentStatus.PROCESSED.name());
			paymentRepository.save(p);
			DisbursementDto currentDis = mapper.map(current, DisbursementDto.class);
			currentDis.setPayment(p.getAmount());
			response.add(currentDis);
		});
		return response;

	}

	public ReportDto getReportByIdUser(Long id) {
		var report = new ReportDto();
		var currentUser = userRepository.findById(id)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
						String.format("User with the id  %d does not exists", id)));
		report.setIdUsuario(currentUser.getId());
		report.setName(currentUser.getName());
		List<Payment> totalPayments = paymentRepository.findByUserId(id);
		report.setPayments_sum(totalPayments.size());
		report.setNew_payments(
				totalPayments.stream().filter(p -> p.getStatus().equals(PaymentStatus.NEW.name())).count());
		report.setNew_payments_amount(totalPayments.stream().filter(p -> p.getStatus().equals(PaymentStatus.NEW.name()))
				.map(p -> p.getAmount()).reduce(BigDecimal.ZERO, BigDecimal::add));
		return report;
	}

}
