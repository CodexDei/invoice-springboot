package com.codexdei.springboot.di.invoice.di_invoice.models;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.RequestScope;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Component
@RequestScope
//le dice a la libreria Jackson que no serialice las propiedades que estan en las llaves, ya que intentar
//hacerlo genera errores, otra opcion es crear nuevos objetos en el controlador para no inyectar el proxy
//@JsonIgnoreProperties({"targetSource","advisors"})
public class Client {

    @Value("${client.name}")
    private String name;
    @Value("${client.lastname}")
    private String lastname;

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getLastname() {
        return lastname;
    }
    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    
}
