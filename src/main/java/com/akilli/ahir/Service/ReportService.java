package com.akilli.ahir.Service;

import com.akilli.ahir.Model.AnimalProductivity;
import com.akilli.ahir.Model.EnvironmentData;
import com.akilli.ahir.Repository.AnimalProductivityRepository;
import com.akilli.ahir.Repository.EnvironmentDataRepository;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

@Service
public class ReportService {

    @Autowired
    private EnvironmentDataRepository environmentRepo;

    @Autowired
    private AnimalProductivityRepository animalRepo;

   

    public ByteArrayInputStream exportEnvironmentExcel(LocalDate start, LocalDate end) throws IOException {
        String[] columns = {"ID", "Tarih", "Saat", "Sıcaklık (°C)", "Nem (%)", "Işık", "Amonyak"};
        try (Workbook workbook = new XSSFWorkbook(); ByteArrayOutputStream out = new ByteArrayOutputStream()) {
            Sheet sheet = workbook.createSheet("Ortam Verileri");
            createExcelHeader(workbook, sheet, columns, IndexedColors.DARK_BLUE.getIndex());

            List<EnvironmentData> dataList = environmentRepo.findByTarihBetween(start, end);
            int rowIdx = 1;
            for (EnvironmentData data : dataList) {
                Row row = sheet.createRow(rowIdx++);
                row.createCell(0).setCellValue(data.getId());
                row.createCell(1).setCellValue(data.getTarih().toString());
                row.createCell(2).setCellValue(data.getSaat().toString());
                row.createCell(3).setCellValue(data.getSicaklik());
                row.createCell(4).setCellValue(data.getNem());
                row.createCell(5).setCellValue(data.getIsik());
                row.createCell(6).setCellValue(data.getAmonyak());
            }
            workbook.write(out);
            return new ByteArrayInputStream(out.toByteArray());
        }
    }

    public ByteArrayInputStream exportAnimalExcel(LocalDate start, LocalDate end) throws IOException {
        String[] columns = {"ID", "Tarih", "Hayvan ID", "Yem Tüketimi (kg)", "Süt Verimi (L)"};
        try (Workbook workbook = new XSSFWorkbook(); ByteArrayOutputStream out = new ByteArrayOutputStream()) {
            Sheet sheet = workbook.createSheet("Hayvan Verimliliği");
            createExcelHeader(workbook, sheet, columns, IndexedColors.DARK_GREEN.getIndex());

            List<AnimalProductivity> dataList = animalRepo.findByTarihBetween(start, end);
            int rowIdx = 1;
            for (AnimalProductivity data : dataList) {
                Row row = sheet.createRow(rowIdx++);
                row.createCell(0).setCellValue(data.getId());
                row.createCell(1).setCellValue(data.getTarih().toString());
                row.createCell(2).setCellValue(data.getHayvanId());
                row.createCell(3).setCellValue(data.getYemTuketimi());
                row.createCell(4).setCellValue(data.getSutVerimi());
            }
            workbook.write(out);
            return new ByteArrayInputStream(out.toByteArray());
        }
    }

    

    public ByteArrayInputStream exportEnvironmentPdf(LocalDate start, LocalDate end) throws DocumentException {
        Document document = new Document();
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        PdfWriter.getInstance(document, out);
        document.open();

        
        com.itextpdf.text.Font headFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 14);
        Paragraph title = new Paragraph("AKILLI AHIR ANALIZ RAPORU (" + start + " / " + end + ")", headFont);
        title.setAlignment(Element.ALIGN_CENTER);
        document.add(title);
        document.add(Chunk.NEWLINE);

        PdfPTable table = new PdfPTable(6);
        table.setWidthPercentage(100);
        String[] headers = {"Tarih", "Saat", "Sic.", "Nem", "Isik", "Amon."};
        for (String h : headers) {
            PdfPCell cell = new PdfPCell(new Phrase(h));
            cell.setBackgroundColor(BaseColor.LIGHT_GRAY);
            table.addCell(cell);
        }

        List<EnvironmentData> dataList = environmentRepo.findByTarihBetween(start, end);
        for (EnvironmentData d : dataList) {
            table.addCell(d.getTarih().toString());
            table.addCell(d.getSaat().toString());
            table.addCell(String.valueOf(d.getSicaklik()));
            table.addCell(String.valueOf(d.getNem()));
            table.addCell(String.valueOf(d.getIsik()));
            table.addCell(String.valueOf(d.getAmonyak()));
        }

        document.add(table);
        document.close();
        return new ByteArrayInputStream(out.toByteArray());
    }

    
    private void createExcelHeader(Workbook workbook, Sheet sheet, String[] columns, short color) {
        Row headerRow = sheet.createRow(0);
        CellStyle style = workbook.createCellStyle();
        
        
        org.apache.poi.ss.usermodel.Font font = workbook.createFont();
        font.setBold(true);
        font.setColor(IndexedColors.WHITE.getIndex());
        
        style.setFont(font);
        style.setFillForegroundColor(color);
        style.setFillPattern(FillPatternType.SOLID_FOREGROUND);

        for (int i = 0; i < columns.length; i++) {
            Cell cell = headerRow.createCell(i);
            cell.setCellValue(columns[i]);
            cell.setCellStyle(style);
            sheet.autoSizeColumn(i);
        }
    }
}