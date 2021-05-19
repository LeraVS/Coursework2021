package com.vodyanchuk.coursework.controller;

import com.vodyanchuk.coursework.model.CalculationHistory;
import com.vodyanchuk.coursework.model.Client;
import com.vodyanchuk.coursework.model.enums.TypeOfTax;
import com.vodyanchuk.coursework.service.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Controller
@RequestMapping("/client")
public class ClientController {
    private final ClientService clientService;
    private final TypeOfBusinessService typeOfBusinessService;
    private final TradeLocationService tradeLocationService;
    private final ObjectTypeService objectTypeService;
    private final CalculationHistoryService calculationHistoryService;

    public ClientController(ClientService clientService, TypeOfBusinessService typeOfBusinessService, TradeLocationService tradeLocationService, ObjectTypeService objectTypeService, CalculationHistoryService calculationHistoryService) {
        this.clientService = clientService;
        this.typeOfBusinessService = typeOfBusinessService;
        this.tradeLocationService = tradeLocationService;
        this.objectTypeService = objectTypeService;
        this.calculationHistoryService = calculationHistoryService;
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

    @GetMapping("/history")
    public String getHistoryPage(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Client client = clientService.findByEmail(authentication.getName());
        model.addAttribute("print", "");
        model.addAttribute("history", calculationHistoryService.findByClientIdClient(client.getIdClient()));
        model.addAttribute("typesOfTax", Stream.of(TypeOfTax.SINGLETAX, TypeOfTax.INCOMETAX, TypeOfTax.TAXUNDERSIMPLIFIEDSYSTEM).collect(Collectors.toList()));
        return "history";
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

    @PostMapping("/history/print")
    public String printHistory(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Client client = clientService.findByEmail(authentication.getName());

        try(FileWriter writer = new FileWriter("E:\\Учёба\\Курсовые работы\\6 семестр\\Курсовая работа\\history.txt", false))
        {
            // запись всей строки
            String text = "Business tax calculator\n"
                        + "История расчетов\n\n"
                        +"==================================\n";
            List<CalculationHistory> histories = calculationHistoryService.findByClientIdClient(client.getIdClient());
            if (histories.isEmpty())
                text += "Истории нет.";
            else {
                for (CalculationHistory history : histories)
                    text += history.getDate() + " - " + history.getTypeOfTax() + " - " + history.getTax() + "\n";
                text += "==================================\n";
            }
            writer.write(text);
            writer.flush();
        }
        catch(IOException ex){
            System.out.println(ex.getMessage());
        }
        model.addAttribute("print", "Запись прошла успешно!");
        model.addAttribute("history", calculationHistoryService.findByClientIdClient(client.getIdClient()));
        model.addAttribute("typesOfTax", Stream.of(TypeOfTax.SINGLETAX, TypeOfTax.INCOMETAX, TypeOfTax.TAXUNDERSIMPLIFIEDSYSTEM).collect(Collectors.toList()));
        return "history";
    }

}
