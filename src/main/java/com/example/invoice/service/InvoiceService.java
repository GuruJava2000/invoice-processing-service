
package com.example.invoice.service;

import com.example.invoice.entity.Invoice;
import com.example.invoice.repository.InvoiceRepository;
import com.example.invoice.util.XmlParser;
import org.springframework.stereotype.Service;

import java.io.InputStream;

@Service
public class InvoiceService {

    private final S3Service s3Service;
    private final InvoiceRepository repository;

    public InvoiceService(S3Service s3Service, InvoiceRepository repository) {
        this.s3Service = s3Service;
        this.repository = repository;
    }

    public void process(String bucket, String key) {
        try (InputStream xml = s3Service.download(bucket, key)) {
            Invoice invoice = XmlParser.parse(xml);
            repository.save(invoice);
            s3Service.copy(bucket, key);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
