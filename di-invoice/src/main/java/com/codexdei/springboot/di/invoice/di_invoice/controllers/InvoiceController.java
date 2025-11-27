package com.codexdei.springboot.di.invoice.di_invoice.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.codexdei.springboot.di.invoice.di_invoice.models.Client;
import com.codexdei.springboot.di.invoice.di_invoice.models.Invoice;

@RestController
@RequestMapping("/invoices")            
public class InvoiceController {

    private final Client client;

    @Autowired
    private Invoice invoice;

    InvoiceController(Client client) {
        this.client = client;
    }

    @GetMapping("/show")
    public Invoice showInvoice(){

        Client c = new Client();
        Invoice i = new Invoice();

        c.setName(invoice.getClient().getName());
        c.setLastname(invoice.getClient().getLastname());

        i.setClient(c);
        i.setDescription(invoice.getDescription());
        i.setItems(invoice.getItems());

        return i;
    }

}
