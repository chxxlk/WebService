package com.example.test_restful.service;

import java.util.List;
import java.util.Optional;
import com.example.test_restful.model.Inventory;
import com.example.test_restful.repository.InventoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class InventoryService {
    @Autowired
    private InventoryRepository inventoryRepository;

    public List<Inventory> getAllInventories() { return inventoryRepository.findAll(); }
    public Optional<Inventory> getInventoryById(Long id) { return inventoryRepository.findById(id); }

    @Transactional
    public Inventory createInventory(Inventory inventory) { return inventoryRepository.save(inventory); }

    public Inventory updateInventory(Inventory inventory) { return inventoryRepository.save(inventory); }

    public void deleteInventory(Long id) { inventoryRepository.deleteById(id); }
}