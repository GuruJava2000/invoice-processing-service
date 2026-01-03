
package com.example.invoice.controller;

import com.example.invoice.service.InvoiceService;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/invoice")
public class InvoiceController {

    private final InvoiceService service;

    public InvoiceController(InvoiceService service) {
        this.service = service;
    }

    @PostMapping("/process")
    public String process(@RequestBody Map<String, String> request) {
        service.process(request.get("bucket"), request.get("key"));
        return "SUCCESS";
    }
}
