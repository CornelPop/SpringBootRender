package com.example.SpringBootRender.controller;

import com.example.SpringBootRender.model.Purse;
import com.example.SpringBootRender.service.PurseService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class PurseController {

    private final PurseService purseService;

    @GetMapping("/allPurses")
    public String home(Model model) {
        model.addAttribute("purses", purseService);
        return "purse/index";
    }

    @GetMapping("createPurse")
    public String renderCreatePurse (Model model){
        model.addAttribute("purse", new Purse());
        return "purse/create";
    }

    @PostMapping("createPurse")
    public String processCreatePurse(@ModelAttribute Purse newPurse, Model model) {
        purseService.createPurse(newPurse);
        return "redirect:/";
    }

    @GetMapping("deletePurse")
    public String renderDeletePurse (Model model) {
        model.addAttribute("purses", purseService.getAllPurses());
        return "purse/delete";
    }

    @PostMapping("deletePurse")
    public String processDeletePurse(@RequestParam(required = false) int[] purseIds) {
        if (purseIds != null) {
            for (int id : purseIds) {
                purseService.deletePurseById(id);
            }
        }
        return "redirect:/";
    }


    @GetMapping("updatePurse")
    public String renderUpdateUserForm(Model model) {
        model.addAttribute("purseList", purseService.getAllPurses());
        return "purse/update";
    }

    @PostMapping("updatePurse")
    public String processUpdatePurseForm(@ModelAttribute Purse updatedPurse,
                                         @RequestParam(required = false) int selectedId,
                                         @RequestParam(required = false) String name,
                                         @RequestParam(required = false) String brand,
                                         @RequestParam(required = false) String description,
                                         @RequestParam(required = false) int price) {

        Purse purseToUpdate = null;
        for (Purse purse : purseService.getAllPurses()) {
            if (purse.getId() == selectedId) {
                purseToUpdate = purse;
                break;
            }
        }
        if (purseToUpdate != null) {
            purseToUpdate.setName(name);
            purseToUpdate.setBrand(brand);
            purseToUpdate.setDescription(description);
            purseToUpdate.setPrice(price);
            purseService.updatePurse(purseToUpdate);
        }
        return "redirect:/";
    }
}
