package com.codexdei.springboot.di.invoice.di_invoice;

import java.util.Arrays;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.PropertySource;

import com.codexdei.springboot.di.invoice.di_invoice.models.Item;
import com.codexdei.springboot.di.invoice.di_invoice.models.Product;

//Esta clase es necesaria para cargar el archivo de propiedades(data.properties) 
//en el contexto de Spring
@Configuration
@PropertySource(value = "classpath:data.properties", encoding = "UTF-8")
public class AppConfig {

    // Es necesario este metodo para que Spring cargue la lista de items
    // definida en el archivo de propiedades y la inyecte en la clase Invoice
    // usando @Value("${invoice.items}")
    @Bean
    @Primary
    List<Item> itemsInvoice() {

        Product p1 = new Product("Camara Sony", 100);
        Product p2 = new Product("Bicicleta Bianchi", 1200);

        return Arrays.asList(new Item(p1, 2), new Item(p2, 4));
    }

    @Bean("default")
    List<Item> itemsInvoiceOffice() {

        Product p1 = new Product("Escritorio Ejecutivo", 850000);
        Product p2 = new Product("Silla Ergonómica", 450000);
        Product p3 = new Product("Impresora Láser", 1200000);
        Product p4 = new Product("Lámpara de Escritorio", 95000);
        Product p5 = new Product("Archivador Metálico", 600000);

        return Arrays.asList(
                new Item(p1, 1),
                new Item(p2, 3),
                new Item(p3, 1),
                new Item(p4, 2),
                new Item(p5, 1));
    }

}
