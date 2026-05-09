package com.example.test_restful.service;

import java.util.List;
import java.util.Optional;
import com.example.test_restful.model.Supplier;
import com.example.test_restful.repository.SupplierRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class SupplierService {
    @Autowired
    private SupplierRepository supplierRepository;

    public List<Supplier> getAllSuppliers() { return supplierRepository.findAll(); }
    public Optional<Supplier> getSupplierById(Long id) { return supplierRepository.findById(id); }

    @Transactional
    public Supplier createSupplier(Supplier supplier) { return supplierRepository.save(supplier); }

    public Supplier updateSupplier(Supplier supplier) { return supplierRepository.save(supplier); }

    public void deleteSupplier(Long id) { supplierRepository.deleteById(id); }
}