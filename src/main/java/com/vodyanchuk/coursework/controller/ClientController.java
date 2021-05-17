package com.vodyanchuk.coursework.controller;

import com.vodyanchuk.coursework.model.Client;
import com.vodyanchuk.coursework.model.enums.TypeOfTax;
import com.vodyanchuk.coursework.service.ClientService;
import com.vodyanchuk.coursework.service.ObjectTypeService;
import com.vodyanchuk.coursework.service.TradeLocationService;
import com.vodyanchuk.coursework.service.TypeOfBusinessService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.stream.Collectors;
import java.util.stream.Stream;

@Controller
@RequestMapping("/client")
public class ClientController {
    private final ClientService clientService;
    private final TypeOfBusinessService typeOfBusinessService;
    private final TradeLocationService tradeLocationService;
    private final ObjectTypeService objectTypeService;

    public ClientController(ClientService clientService, TypeOfBusinessService typeOfBusinessService, TradeLocationService tradeLocationService, ObjectTypeService objectTypeService) {
        this.clientService = clientService;
        this.typeOfBusinessService = typeOfBusinessService;
        this.tradeLocationService = tradeLocationService;
        this.objectTypeService = objectTypeService;
    }

    @GetMapping("")
    public String getClientPage(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Client client = clientService.findByEmail(authentication.getName());

        model.addAttribute("client", client);
        return "landing";
    }

    @GetMapping("/profile")
    public String getClientProfilePage(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Client client = clientService.findByEmail(authentication.getName());

        model.addAttribute("client", client);
        model.addAttribute("typesOfTax", Stream.of(TypeOfTax.SINGLETAX, TypeOfTax.INCOMETAX, TypeOfTax.TAXUNDERSIMPLIFIEDSYSTEM).collect(Collectors.toList()));
        model.addAttribute("typesOfBusiness", typeOfBusinessService.findAll());
        model.addAttribute("tradeLocations", tradeLocationService.findAll());
        model.addAttribute("objectTypes", objectTypeService.findAll());
        return "profile";
    }

    @PutMapping("/update/{clientId}")
    public String updateClient(@PathVariable Long clientId, @ModelAttribute("client") Client client, Model model) {
        clientService.update(clientId, client);
        model.addAttribute("typesOfTax", Stream.of(TypeOfTax.SINGLETAX, TypeOfTax.INCOMETAX, TypeOfTax.TAXUNDERSIMPLIFIEDSYSTEM).collect(Collectors.toList()));
        model.addAttribute("typesOfBusiness", typeOfBusinessService.findAll());
        model.addAttribute("tradeLocations", tradeLocationService.findAll());
        model.addAttribute("objectTypes", objectTypeService.findAll());
        return "redirect:/client/profile";
    }

}
