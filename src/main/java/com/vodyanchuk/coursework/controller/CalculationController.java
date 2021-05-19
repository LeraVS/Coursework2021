package com.vodyanchuk.coursework.controller;

import com.vodyanchuk.coursework.model.*;
import com.vodyanchuk.coursework.model.enums.TypeOfTax;
import com.vodyanchuk.coursework.service.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/calculation")
public class CalculationController {
    private final CalculationHistoryService calculationHistoryService;
    private final ClientService clientService;
    private final SingleTaxRateService singleTaxRateService;
    private final IncomeTaxRateService incomeTaxRateService;
    private final TaxRateUnderSimplifiedSystemService taxRateUnderSimplifiedSystemService;
    private final TypeOfBusinessService typeOfBusinessService;
    private final TradeLocationService tradeLocationService;
    private final ObjectTypeService objectTypeService;

    public CalculationController(CalculationHistoryService calculationHistoryService, ClientService clientService, SingleTaxRateService singleTaxRateService, IncomeTaxRateService incomeTaxRateService, TaxRateUnderSimplifiedSystemService taxRateUnderSimplifiedSystemService, TypeOfBusinessService typeOfBusinessService, TradeLocationService tradeLocationService, ObjectTypeService objectTypeService) {
        this.calculationHistoryService = calculationHistoryService;
        this.clientService = clientService;
        this.singleTaxRateService = singleTaxRateService;
        this.incomeTaxRateService = incomeTaxRateService;
        this.taxRateUnderSimplifiedSystemService = taxRateUnderSimplifiedSystemService;
        this.typeOfBusinessService = typeOfBusinessService;
        this.tradeLocationService = tradeLocationService;
        this.objectTypeService = objectTypeService;
    }

    @GetMapping("")
    public String getCalculationPage(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Client client = clientService.findByEmail(authentication.getName());
        if (!client.hasTypeOfTax())
            return "redirect:/calculation/singleTax";
        if (client.isTypeSingleTax())
            return "redirect:/calculation/singleTax";
        if (client.isTypeIncomeTax())
            return "redirect:/calculation/incomeTax";
        if (client.isTypeTaxRateUnderSimplifiedSystem())
            return "redirect:/calculation/taxUnderSimplifiedSystem";
        return null;
    }

    @GetMapping("/singleTax")
    public String getSingleTaxPage( Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Client client = clientService.findByEmail(authentication.getName());
        String address = client.getAddress();
        TemporarySingleTaxRate temporarySingleTaxRate = new TemporarySingleTaxRate();
        temporarySingleTaxRate.setIdTypeOfBusiness(client.getTypeOfBusiness().getIdTypeOfBusiness());
        temporarySingleTaxRate.setIdTradeLocation(client.getTradeLocation()!= null?client.getTradeLocation().getIdTradeLocation():null);
        temporarySingleTaxRate.setIdObjectType(client.getObjectType()!=null?client.getObjectType().getIdObjectType():null);
        temporarySingleTaxRate.setCity(address.substring(0, address.indexOf(',')));
        model.addAttribute("singleTaxForm", temporarySingleTaxRate);
        model.addAttribute("typesOfBusiness", typeOfBusinessService.findAll());
        model.addAttribute("tradeLocations", tradeLocationService.findAll());
        model.addAttribute("objectTypes", objectTypeService.findAll());
        return "singleTaxCalculation";
    }

    @PutMapping("/singleTax/calculate")
    public String getSubmitSingleTaxPage(@ModelAttribute("temporarySingleTaxRate") TemporarySingleTaxRate tSingleTaxRate, Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Client client = clientService.findByEmail(authentication.getName());
        TemporarySingleTaxRate temporarySingleTaxRate =singleTaxRateService.calculateRate(tSingleTaxRate);

        CalculationHistory calculationHistory = calculationHistoryService.preSaveOperation(client, TypeOfTax.SINGLETAX, temporarySingleTaxRate.getTax());
        calculationHistoryService.save(calculationHistory);

        model.addAttribute("singleTaxForm", temporarySingleTaxRate);
        model.addAttribute("typesOfBusiness", typeOfBusinessService.findAll());
        model.addAttribute("tradeLocations", tradeLocationService.findAll());
        model.addAttribute("objectTypes", objectTypeService.findAll());
        return "singleTaxCalculation";
    }

    @GetMapping("/incomeTax")
    public String getIncomeTaxPage( Model model) {

        TemporaryIncomeTaxRate temporaryIncomeTaxRate = new TemporaryIncomeTaxRate();

        model.addAttribute("incomeTaxForm", temporaryIncomeTaxRate);
        return "incomeTaxCalculation";
    }

    @PutMapping("/incomeTax/calculate")
    public String getSubmitIncomeTaxPage(@ModelAttribute("temporaryIncomeTaxRate") TemporaryIncomeTaxRate tTemporaryIncomeTaxRate, Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Client client = clientService.findByEmail(authentication.getName());
        TemporaryIncomeTaxRate temporaryIncomeTaxRate = incomeTaxRateService.calculateRate(tTemporaryIncomeTaxRate);

        CalculationHistory calculationHistory = calculationHistoryService.preSaveOperation(client, TypeOfTax.INCOMETAX, tTemporaryIncomeTaxRate.getTax());
        calculationHistoryService.save(calculationHistory);

        model.addAttribute("incomeTaxForm", temporaryIncomeTaxRate);
        return "incomeTaxCalculation";
    }

    @GetMapping("/taxUnderSimplifiedSystem")
    public String getTaxRateUnderSimplifiedSystemPage( Model model) {

        TemporaryTaxRateUnderSimplifiedSystem temporaryTaxRateUnderSimplifiedSystem = new TemporaryTaxRateUnderSimplifiedSystem();

        model.addAttribute("taxRateUnderSimplifiedSystemForm", temporaryTaxRateUnderSimplifiedSystem);
        return "taxUnderSimplifiedSystemCalculation";
    }

    @PutMapping("/taxUnderSimplifiedSystem/calculate")
    public String getSubmitTaxRateUnderSimplifiedSystemPage(@ModelAttribute("temporaryTaxRateUnderSimplifiedSystem") TemporaryTaxRateUnderSimplifiedSystem tTemporaryTaxRateUnderSimplifiedSystem, Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Client client = clientService.findByEmail(authentication.getName());
        TemporaryTaxRateUnderSimplifiedSystem temporaryTaxRateUnderSimplifiedSystem = taxRateUnderSimplifiedSystemService.calculateRate(tTemporaryTaxRateUnderSimplifiedSystem);

        CalculationHistory calculationHistory = calculationHistoryService.preSaveOperation(client, TypeOfTax.TAXUNDERSIMPLIFIEDSYSTEM, tTemporaryTaxRateUnderSimplifiedSystem.getTax());
        calculationHistoryService.save(calculationHistory);

        model.addAttribute("taxRateUnderSimplifiedSystemForm", temporaryTaxRateUnderSimplifiedSystem);
        return "taxUnderSimplifiedSystemCalculation";
    }


}
