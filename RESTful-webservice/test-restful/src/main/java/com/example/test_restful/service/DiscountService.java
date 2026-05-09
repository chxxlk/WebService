package com.example.test_restful.service;

import java.util.List;
import java.util.Optional;
import com.example.test_restful.model.Discount;
import com.example.test_restful.repository.DiscountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class DiscountService {
    @Autowired
    private DiscountRepository discountRepository;

    public List<Discount> getAllDiscounts() { return discountRepository.findAll(); }
    public Optional<Discount> getDiscountById(Long id) { return discountRepository.findById(id); }

    @Transactional
    public Discount createDiscount(Discount discount) { return discountRepository.save(discount); }

    public Discount updateDiscount(Discount discount) { return discountRepository.save(discount); }

    public void deleteDiscount(Long id) { discountRepository.deleteById(id); }
}