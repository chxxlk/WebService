package com.example.test_restful.service;

import java.util.List;
import java.util.Optional;
import com.example.test_restful.model.Shipment;
import com.example.test_restful.repository.ShipmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ShipmentService {
    @Autowired
    private ShipmentRepository shipmentRepository;

    public List<Shipment> getAllShipments() { return shipmentRepository.findAll(); }
    public Optional<Shipment> getShipmentById(Long id) { return shipmentRepository.findById(id); }

    @Transactional
    public Shipment createShipment(Shipment shipment) { return shipmentRepository.save(shipment); }

    public Shipment updateShipment(Shipment shipment) { return shipmentRepository.save(shipment); }

    public void deleteShipment(Long id) { shipmentRepository.deleteById(id); }
}