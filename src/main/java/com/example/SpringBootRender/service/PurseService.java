package com.example.SpringBootRender.service;

import com.example.SpringBootRender.model.Purse;

import java.util.List;

public interface PurseService {

    void createPurse(Purse purse);

    void updatePurse(Purse purse);

    void deletePurse(Purse purse);

    void deletePurseById(int id);

    List<Purse> getAllPurses();


}
