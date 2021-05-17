package com.vodyanchuk.coursework.controller;

import com.vodyanchuk.coursework.model.AuthorizationManager;
import com.vodyanchuk.coursework.model.CalculationHistory;
import com.vodyanchuk.coursework.model.Client;
import com.vodyanchuk.coursework.model.TemporaryUser;
import com.vodyanchuk.coursework.model.enums.TypeOfTax;
import com.vodyanchuk.coursework.service.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.stream.Collectors;
import java.util.stream.Stream;

@Controller
@RequestMapping("/")
public class MainController {
    private final AuthorizationManagerService authorizationManagerService;
    private final ClientService clientService;
    private final CalculationHistoryService calculationHistoryService;
    private final TypeOfBusinessService typeOfBusinessService;
    private final TradeLocationService tradeLocationService;
    private final ObjectTypeService objectTypeService;

    public MainController(AuthorizationManagerService authorizationManagerService, ClientService clientService, CalculationHistoryService calculationHistoryService, TypeOfBusinessService typeOfBusinessService, TradeLocationService tradeLocationService, ObjectTypeService objectTypeService) {
        this.authorizationManagerService = authorizationManagerService;
        this.clientService = clientService;
        this.calculationHistoryService = calculationHistoryService;
        this.typeOfBusinessService = typeOfBusinessService;
        this.tradeLocationService = tradeLocationService;
        this.objectTypeService = objectTypeService;
    }

    @GetMapping()
    public String getClientPage(Model model) {
        model.addAttribute("user", new AuthorizationManager());
        model.addAttribute("userToRegistr", new TemporaryUser());
        setProperties(model);
        return "index";
    }

    @PostMapping("/signUp")
    public String registration(@ModelAttribute("user") TemporaryUser user, Model model) {
        if (user.getEmail().equals("admin") && user.getPassword().equals("admin"))
            return "admin";
        Client readyToSaveClient = clientService.preSaveOperation(user);
        clientService.save(readyToSaveClient);
        AuthorizationManager readyToSaveManager = authorizationManagerService.preSaveOperation(user, readyToSaveClient);
        authorizationManagerService.save(readyToSaveManager);
        CalculationHistory readyToSaveHistory = calculationHistoryService.preSaveOperation(readyToSaveClient);
        calculationHistoryService.save(readyToSaveHistory);
        setProperties(model);
        return "redirect:/client";
    }

    private void setProperties(Model model) {
        model.addAttribute("typesOfTax", Stream.of(TypeOfTax.SINGLETAX, TypeOfTax.INCOMETAX, TypeOfTax.TAXUNDERSIMPLIFIEDSYSTEM).collect(Collectors.toList()));
        model.addAttribute("typesOfBusiness", typeOfBusinessService.findAll());
        model.addAttribute("tradeLocations", tradeLocationService.findAll());
        model.addAttribute("objectTypes", objectTypeService.findAll());
    }
}
