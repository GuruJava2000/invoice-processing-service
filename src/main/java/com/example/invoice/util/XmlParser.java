
package com.example.invoice.util;

import com.example.invoice.entity.Invoice;
import jakarta.xml.bind.JAXBContext;

import java.io.InputStream;

public class XmlParser {

    public static Invoice parse(InputStream xml) {
        try {
            JAXBContext context = JAXBContext.newInstance(Invoice.class);
            return (Invoice) context.createUnmarshaller().unmarshal(xml);
        } catch (Exception e) {
            throw new RuntimeException("XML Parsing failed", e);
        }
    }
}
