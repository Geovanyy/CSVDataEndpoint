package com.example.csvdataendpoint.rest;

import org.springframework.core.io.ClassPathResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping("/api")
public class CsvController {

    @GetMapping("/download-csv")
    public ResponseEntity<byte[]> downloadCsv() {
        ClassPathResource csvFile = new ClassPathResource("jurisprudencia-metadados.csv");

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.parseMediaType("text/csv"));
        headers.setContentDispositionFormData("attachment", csvFile.getFilename());

        try {
            byte[] data = csvFile.getInputStream().readAllBytes();
            return ResponseEntity.ok().headers(headers).body(data);
        } catch (IOException e) {
            return ResponseEntity.status(500).build();
        }
    }

}
