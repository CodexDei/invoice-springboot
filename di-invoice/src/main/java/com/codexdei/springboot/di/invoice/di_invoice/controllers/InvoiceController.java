package com.codexdei.springboot.di.invoice.di_invoice.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.codexdei.springboot.di.invoice.di_invoice.models.Invoice;

@RestController
@RequestMapping("/invoices")            
public class InvoiceController {

    @Autowired
    private Invoice invoice;

    @GetMapping("/show")
    public Invoice showInvoice(){
        return invoice;
    }

}
