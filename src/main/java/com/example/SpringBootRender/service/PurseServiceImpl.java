package com.example.SpringBootRender.service;

import com.example.SpringBootRender.model.Purse;
import com.example.SpringBootRender.repository.PurseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PurseServiceImpl implements PurseService {

    private final PurseRepository purseRepository;

    @Override
    public void createPurse(Purse purse) {
        purseRepository.save(purse);
    }

    @Override
    public void updatePurse(Purse purse) {
        purseRepository.save(purse);
    }

    @Override
    public void deletePurse(Purse purse) {
        purseRepository.delete(purse);
    }

    @Override
    public void deletePurseById(int id) {
        purseRepository.deleteById(id);
    }

    @Override
    public List<Purse> getAllPurses() {
        return purseRepository.findAll();
    }
}
