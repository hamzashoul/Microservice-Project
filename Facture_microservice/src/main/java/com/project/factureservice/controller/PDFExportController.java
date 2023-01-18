package com.project.factureservice.controller;

import com.project.factureservice.Facture;
import com.project.factureservice.Order;
import com.project.factureservice.service.PDFgeneratorService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;


@RestController
public class PDFExportController {

    @Autowired
    RestTemplate restTemplate;

    @Autowired
    Order order;

    @Autowired
    Facture facture;

    @Autowired
    private final PDFgeneratorService pdFgeneratorService;

    public PDFExportController(PDFgeneratorService pdFgeneratorService) {
        this.pdFgeneratorService = pdFgeneratorService;
    }

    @RequestMapping ("/facture/{id}")
    public void generatePDF(HttpServletResponse response, Facture facture, @PathVariable Long id) throws IOException {

        order=restTemplate.getForObject("http://orderservice/orders/"+id,Order.class);
        facture.setFactureId(order.getId());
        facture.setCustomerName(order.getBuyer());
        facture.setProductName(order.getProduct());
        facture.setPrice(order.getPrice());
        facture.setQuantity(order.getQuantity());
        DateFormat dateFormator=new SimpleDateFormat("yyyy-MM-dd_hh:mm:ss");
        String currentDateTime=dateFormator.format(new Date());

        String headerkey="Content-Disposition";
        String headerValue="attachment; filename=pdf_"+currentDateTime+".pdf";
        response.setContentType(MediaType.APPLICATION_PDF_VALUE);
        response.setHeader(headerkey,headerValue);
        this.pdFgeneratorService.export(response,facture);
    }

}
