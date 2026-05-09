package com.example.test_restful.service;

import java.util.List;
import java.util.Optional;
import com.example.test_restful.model.Payment;
import com.example.test_restful.repository.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PaymentService {
    @Autowired
    private PaymentRepository paymentRepository;

    public List<Payment> getAllPayments() { return paymentRepository.findAll(); }
    public Optional<Payment> getPaymentById(Long id) { return paymentRepository.findById(id); }

    @Transactional
    public Payment createPayment(Payment payment) { return paymentRepository.save(payment); }

    public Payment updatePayment(Payment payment) { return paymentRepository.save(payment); }

    public void deletePayment(Long id) { paymentRepository.deleteById(id); }
}