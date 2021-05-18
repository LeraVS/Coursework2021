package com.vodyanchuk.coursework.controller;

import com.vodyanchuk.coursework.model.*;
import com.vodyanchuk.coursework.service.IncomeTaxRateService;
import com.vodyanchuk.coursework.service.SingleTaxRateService;
import com.vodyanchuk.coursework.service.TaxRateUnderSimplifiedSystemService;
import com.vodyanchuk.coursework.service.TypeOfBusinessService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/admin")
public class AdminController {
    private final TaxRateUnderSimplifiedSystemService taxRateUnderSimplifiedSystemService;
    private final IncomeTaxRateService incomeTaxRateService;
    private final SingleTaxRateService singleTaxRateService;

    public AdminController(TaxRateUnderSimplifiedSystemService taxRateUnderSimplifiedSystemService, IncomeTaxRateService incomeTaxRateService, SingleTaxRateService singleTaxRateService, TypeOfBusinessService typeOfBusinessService) {
        this.taxRateUnderSimplifiedSystemService = taxRateUnderSimplifiedSystemService;
        this.incomeTaxRateService = incomeTaxRateService;
        this.singleTaxRateService = singleTaxRateService;
    }

    @GetMapping()
    public String getAdminPage(Model model) {
        return "admin";
    }

    @GetMapping("/taxRatesUnderSimplifiedSystem")
    public String editTaxRatesUnderSimplifiedSystemPage(Model model) {
        model.addAttribute("taxRateUnderSimplifiedSystemForm", new ChangedTaxRatesUnderSimplifiedSystem(taxRateUnderSimplifiedSystemService.findAll()));
        return "editTaxRatesUnderSimplifiedSystem";
    }

    @PutMapping("/taxRatesUnderSimplifiedSystem/update")
    public String updateTaxRatesUnderSimplifiedSystemPage(@ModelAttribute("taxRateUnderSimplifiedSystemForm") ChangedTaxRatesUnderSimplifiedSystem taxes, Model model) {
        for (TaxRateUnderSimplifiedSystem tax: taxes.getTaxRatesUnderSimplifiedSystem())
            taxRateUnderSimplifiedSystemService.update(tax.getIdTaxRateUnderSimplifiedSystem(), tax);
        model.addAttribute("taxRateUnderSimplifiedSystemForm", new ChangedTaxRatesUnderSimplifiedSystem(taxRateUnderSimplifiedSystemService.findAll()));
        return "redirect:/admin/taxRatesUnderSimplifiedSystem";
    }

    @DeleteMapping("/taxRatesUnderSimplifiedSystem/delete/{taxId}")
    public String deleteTaxRatesUnderSimplifiedSystemPage(@PathVariable Long taxId, Model model) {
        taxRateUnderSimplifiedSystemService.delete(taxId);
        model.addAttribute("taxRateUnderSimplifiedSystemForm", new ChangedTaxRatesUnderSimplifiedSystem(taxRateUnderSimplifiedSystemService.findAll()));
        return "redirect:/admin/taxRatesUnderSimplifiedSystem";
    }

    @GetMapping("/incomeTaxRates")
    public String editIncomeTaxesPage(Model model) {
        model.addAttribute("incomeTaxRateForm", new ChangedIncomeTaxes(incomeTaxRateService.findAll()));
        return "editIncomeTaxRates";
    }

    @PutMapping("/incomeTaxRates/update")
    public String updateIncomeTaxesPage(@ModelAttribute("incomeTaxRateForm") ChangedIncomeTaxes taxes, Model model) {
        for (IncomeTaxRate tax: taxes.getIncomeTaxRates())
            incomeTaxRateService.update(tax.getIdIncomeTaxRate(), tax);
        model.addAttribute("incomeTaxRateForm", new ChangedIncomeTaxes(incomeTaxRateService.findAll()));
        return "redirect:/admin/incomeTaxRates";
    }

    @DeleteMapping("/incomeTaxRates/delete/{taxId}")
    public String deleteIncomeTaxesPage(@PathVariable Long taxId, Model model) {
        incomeTaxRateService.delete(taxId);
        model.addAttribute("incomeTaxRateForm", new ChangedIncomeTaxes(incomeTaxRateService.findAll()));
        return "redirect:/admin/incomeTaxRates";
    }

    @GetMapping("/singleTaxRates")
    public String editSingleTaxRatesPage(Model model) {
        model.addAttribute("singleTaxRatesForm", new ChangedSingleTaxRates(singleTaxRateService.findAll()));
        return "editSingleTaxRates";
    }

    @PutMapping("/singleTaxRates/update")
    public String updateSingleTaxRatesPage(@ModelAttribute("singleTaxRatesForm") ChangedSingleTaxRates taxes, Model model) {
        for (SingleTaxRate tax: taxes.getSingleTaxRates())
            singleTaxRateService.update(tax.getIdSingleTaxRate(), tax);
        model.addAttribute("singleTaxRatesForm", new ChangedSingleTaxRates(singleTaxRateService.findAll()));
        return "redirect:/admin/incomeTaxRates";
    }

    @DeleteMapping("/singleTaxRates/delete/{taxId}")
    public String deleteSingleTaxRatesPage(@PathVariable Long taxId, Model model) {
        singleTaxRateService.delete(taxId);
        model.addAttribute("singleTaxRatesForm", new ChangedSingleTaxRates(singleTaxRateService.findAll()));
        return "redirect:/admin/incomeTaxRates";
    }
}
