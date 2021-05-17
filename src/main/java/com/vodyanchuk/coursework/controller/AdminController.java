package com.vodyanchuk.coursework.controller;

import com.vodyanchuk.coursework.model.Client;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/admin")
public class AdminController {


    @GetMapping()
    public String getClientPage(Model model) {
        /*Long id = 1L;
        model.addAttribute("client", clientService.findById(id));
        model.addAttribute("typesOfBusiness", typeOfBusinessService.findAll());*/
        return "admin";
    }

    
}
