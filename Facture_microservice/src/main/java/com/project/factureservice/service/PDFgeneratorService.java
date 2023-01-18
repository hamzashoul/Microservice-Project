package com.project.factureservice.service;


import com.lowagie.text.*;
import com.lowagie.text.Font;
import com.lowagie.text.pdf.PdfWriter;
import com.project.factureservice.Facture;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.awt.*;
import java.io.IOException;

@Service
public class PDFgeneratorService {

    @Autowired
    Facture facture;

    public void export(HttpServletResponse response,Facture facture) throws IOException {

        Document document=new Document(PageSize.A4);
        PdfWriter.getInstance(document, response.getOutputStream());
        document.open();
        Font fontTitle= FontFactory.getFont(FontFactory.HELVETICA_BOLD);
        fontTitle.setColor(new Color(0x098312));
        fontTitle.setSize(24);

        Paragraph paragraph=new Paragraph("FACTURE", fontTitle);
        paragraph.setAlignment(Paragraph.ALIGN_TOP);

        Font fontLabels=FontFactory.getFont(FontFactory.HELVETICA);
        fontLabels.setSize(12);

        Paragraph customerName=new Paragraph("Le nom de client: "+facture.getCustomerName(),fontLabels);
        customerName.setAlignment(Paragraph.ALIGN_LEFT);

        Paragraph productName =new Paragraph("Le nom de produit: "+facture.getProductName(),fontLabels);
        productName.setAlignment(Paragraph.ALIGN_LEFT);

        Paragraph quantity =new Paragraph("La quantité total: "+facture.getQuantity(),fontLabels);
        quantity.setAlignment(Paragraph.ALIGN_LEFT);

        Paragraph price =new Paragraph("Le prix de chaque unité de produit: "+facture.getPrice(),fontLabels);
        price.setAlignment(Paragraph.ALIGN_LEFT);

        Font fontTotal=FontFactory.getFont(FontFactory.COURIER_BOLD);
        fontTotal.setSize(16);
        Paragraph total=new Paragraph("Le prix total: "+facture.getPrice()*facture.getQuantity(),fontTotal);
        total.setAlignment(Element.ALIGN_CENTER);
        document.add(paragraph);
        document.add(customerName);
        document.add(productName);
        document.add(quantity);
        document.add(price);
        document.add(total);
        document.close();
    }
}
