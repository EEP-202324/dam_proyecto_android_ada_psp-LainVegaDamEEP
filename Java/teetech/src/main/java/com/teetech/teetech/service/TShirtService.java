package com.teetech.teetech.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.teetech.teetech.TShirt;
import com.teetech.teetech.repository.TShirtRepository;

@Service
public class TShirtService {

    private final TShirtRepository tShirtRepository;

    @Autowired
    public TShirtService(TShirtRepository tShirtRepository) {
        this.tShirtRepository = tShirtRepository;
    }

    public TShirt saveTShirt(TShirt tShirt) {
        return tShirtRepository.save(tShirt);
    }

    public List<TShirt> findAllTShirts() {
        return tShirtRepository.findAll();
    }

    public TShirt findTShirtById(Long id) {
        return tShirtRepository.findById(id).orElse(null);
    }

    public TShirt updateTShirt(Long id, TShirt tShirtDetails) {
        return tShirtRepository.findById(id)
            .map(tShirt -> {
                tShirt.setSize(tShirtDetails.getSize());
                tShirt.setColor(tShirtDetails.getColor());
                tShirt.setGender(tShirtDetails.getGender());
                tShirt.setSleeve(tShirtDetails.getSleeve());
                tShirt.setWeight(tShirtDetails.getWeight());
                return tShirtRepository.save(tShirt);
            })
            .orElse(null);
    }

    public boolean deleteTShirt(Long id) {
        return tShirtRepository.findById(id)
            .map(tShirt -> {
                tShirtRepository.delete(tShirt);
                return true;
            })
            .orElse(false);
    }
}
