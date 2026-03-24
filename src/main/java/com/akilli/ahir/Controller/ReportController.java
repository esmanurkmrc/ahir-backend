package com.akilli.ahir.Controller;

import com.akilli.ahir.Service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.ByteArrayInputStream;
import java.time.LocalDate;

@RestController
@RequestMapping("/api/reports")
@CrossOrigin("*") // React'tan gelen isteklerin engellenmemesi için
public class ReportController {

    @Autowired
    private ReportService reportService;

    @GetMapping("/download")
    public ResponseEntity<InputStreamResource> downloadReport(
            @RequestParam("start") String start,
            @RequestParam("end") String end,
            @RequestParam("format") String format,
            @RequestParam("category") String category) {

        try {
            // Gelen tarihleri Java LocalDate formatına çeviriyoruz
            LocalDate startDate = LocalDate.parse(start);
            LocalDate endDate = LocalDate.parse(end);
            
            ByteArrayInputStream stream;
            String fileName;
            MediaType mediaType;

            // Format ve Kategori Kontrolü
            if ("pdf".equalsIgnoreCase(format)) {
                stream = reportService.exportEnvironmentPdf(startDate, endDate);
                fileName = "Ahir_Analiz_Raporu_" + start + ".pdf";
                mediaType = MediaType.APPLICATION_PDF;
            } else {
                // Excel Seçeneği
                if ("animal".equalsIgnoreCase(category)) {
                    stream = reportService.exportAnimalExcel(startDate, endDate);
                    fileName = "Hayvan_Verimlilik_Verisi_" + start + ".xlsx";
                } else {
                    stream = reportService.exportEnvironmentExcel(startDate, endDate);
                    fileName = "Ahir_Ortam_Verileri_" + start + ".xlsx";
                }
                // Excel için özel Media Type
                mediaType = MediaType.parseMediaType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
            }

            // Dosyayı tarayıcıya "İNDİR" komutuyla gönderiyoruz
            return ResponseEntity.ok()
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + fileName + "\"")
                    .header(HttpHeaders.ACCESS_CONTROL_EXPOSE_HEADERS, HttpHeaders.CONTENT_DISPOSITION)
                    .contentType(mediaType)
                    .body(new InputStreamResource(stream));

        } catch (Exception e) {
            // Bir hata oluşursa konsola yazdır (404 yerine 500 hatası alırsak nedenini buradan görürüz)
            System.err.println("Rapor oluşturma hatası: " + e.getMessage());
            return ResponseEntity.internalServerError().build();
        }
    }
}