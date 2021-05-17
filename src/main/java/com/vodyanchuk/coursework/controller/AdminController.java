package com.vodyanchuk.coursework.controller;

import com.vodyanchuk.coursework.model.TaxRateUnderSimplifiedSystem;
import com.vodyanchuk.coursework.service.TaxRateUnderSimplifiedSystemService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {
    private final TaxRateUnderSimplifiedSystemService taxRateUnderSimplifiedSystemService;

    public AdminController(TaxRateUnderSimplifiedSystemService taxRateUnderSimplifiedSystemService) {
        this.taxRateUnderSimplifiedSystemService = taxRateUnderSimplifiedSystemService;
    }

    @GetMapping()
    public String getAdminPage(Model model) {
        return "admin";
    }

    @GetMapping("/taxRatesUnderSimplifiedSystem")
    public String editTaxRatesUnderSimplifiedSystemPage(Model model) {
        model.addAttribute("taxRateUnderSimplifiedSystemForm", taxRateUnderSimplifiedSystemService.findAll());
        return "editTaxRatesUnderSimplifiedSystem";
    }

    @PutMapping("/taxRatesUnderSimplifiedSystem/update")
    public String updateTaxRatesUnderSimplifiedSystemPage(@ModelAttribute("taxRateUnderSimplifiedSystemForm") ArrayList<TaxRateUnderSimplifiedSystem> taxs, Model model) {
        for (TaxRateUnderSimplifiedSystem tax: taxs)
            taxRateUnderSimplifiedSystemService.update(tax.getIdTaxRateUnderSimplifiedSystem(), tax);
        model.addAttribute("taxRateUnderSimplifiedSystemForm", taxRateUnderSimplifiedSystemService.findAll());
        return "redirect:/admin/taxRatesUnderSimplifiedSystem";
    }

    @PutMapping("/taxRatesUnderSimplifiedSystem/delete/{taxid}")
    public String deleteTaxRatesUnderSimplifiedSystemPage(@PathVariable Long taxid, @ModelAttribute("taxRateUnderSimplifiedSystemForm") ArrayList<TaxRateUnderSimplifiedSystem> taxs, Model model) {
        taxRateUnderSimplifiedSystemService.delete(taxid);
        model.addAttribute("taxRateUnderSimplifiedSystemForm", taxRateUnderSimplifiedSystemService.findAll());
        return "redirect:/admin/taxRatesUnderSimplifiedSystem";
    }
}
