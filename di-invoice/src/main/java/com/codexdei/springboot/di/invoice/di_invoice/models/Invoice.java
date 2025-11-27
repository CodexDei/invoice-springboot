package com.codexdei.springboot.di.invoice.di_invoice.models;

import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.RequestScope;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;

@Component
@RequestScope
//le dice a la libreria Jackson que no serialice las propiedades que estan en las llaves, ya que intentar
//hacerlo genera errores, otra opcion es crear nuevos objetos en el controlador para no inyectar el proxy
//@JsonIgnoreProperties({"targetSource","advisors"})
public class Invoice {

    @Autowired
    private Client client;
    @Value("${invoice.description}")
    private String description;

    @Autowired
    @Qualifier("default")
    private List<Item> items;

    public Invoice(){
        
        System.out.println("Creating the invoice component in Construct");
        //client y description seran null porque no se han inyectado
        System.out.println(client);
        System.out.println(description);
    }
    
    //metodos del ciclo de vida
    @PostConstruct
    public void init(){
        
        System.out.println("Creating the invoice component in PostConstruct");
        //PostContruct si inyecta los valores, por lo que NO seran null como en el constructor
        System.out.println(client);
        System.out.println(description);
        client.setName(client.getName().concat(" Aurelio"));
        description = description.concat(" of ").concat(client.getName().concat(" ").concat(client.getLastname()));
    }

    @PreDestroy
    public void destroy(){

        System.out.println("Destroying the component or bean invoice");
    }
    
    public Client getClient() {
        return client;
    }
    public void setClient(Client client) {
        this.client = client;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public List<Item> getItems() {
        return items;
    }
    public void setItems(List<Item> items) {
        this.items = items;
    }

    public Integer getTotal() {
        //Forma tradicionl de sumar para obtener el total
/*         Double total = 0.0;
        for (Item item : items) {
            total += item.getImporte();
        }
        return total; */

        //sumando con APi stream
        return items.stream()
        //transforma el stream del tipo Item al tipo Integer, ya que ese es el tipo de dato de getImporte()
        .map(item -> item.getAmount())
        //descarta los valores nulos para evitar un NullPointerException
        .filter(Objects::nonNull)
        //reduce los elementos a un valor, en este caso sumando cada elemento getImporte()
        //0 es el valor inicial, sum el acumulador(puede tener cualquier nombre), item representa cada valor getImporte()
        .reduce(0,(sum,item) -> sum + item);

    }
    

}
