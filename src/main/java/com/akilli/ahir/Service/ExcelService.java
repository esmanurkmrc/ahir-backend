package com.akilli.ahir.Service;

import java.io.InputStream;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.akilli.ahir.Model.AnalysisEnvironment;
import com.akilli.ahir.Model.AnalysisProductivity;
import com.akilli.ahir.Repository.AnalysisEnvironmentRepository;
import com.akilli.ahir.Repository.AnalysisProductivityRepository;

@Service
public class ExcelService {

    private final AnalysisEnvironmentRepository analysisEnvironmentRepository;
    private final AnalysisProductivityRepository analysisProductivityRepository;

    public ExcelService(
            AnalysisEnvironmentRepository analysisEnvironmentRepository,
            AnalysisProductivityRepository analysisProductivityRepository
    ) {
        this.analysisEnvironmentRepository = analysisEnvironmentRepository;
        this.analysisProductivityRepository = analysisProductivityRepository;
    }

    public void importExcel(MultipartFile file) throws Exception {
        try (InputStream inputStream = file.getInputStream();
             Workbook workbook = new XSSFWorkbook(inputStream)) {

            Sheet environmentSheet = workbook.getSheet("sensor_verileri");
            Sheet productivitySheet = workbook.getSheet("sut_yem_verileri");

            if (environmentSheet != null) {
                importAnalysisEnvironment(environmentSheet);
            }

            if (productivitySheet != null) {
                importAnalysisProductivity(productivitySheet);
            }
        }
    }

    private void importAnalysisEnvironment(Sheet sheet) {
        boolean firstRow = true;

        for (Row row : sheet) {
            if (firstRow) {
                firstRow = false;
                continue;
            }

            if (row == null || row.getCell(0) == null) {
                continue;
            }

            AnalysisEnvironment data = new AnalysisEnvironment();

            data.setTarih(getCellLocalDate(row.getCell(0)));
            data.setSaat(getCellLocalTime(row.getCell(1)));
            data.setSicaklik(getCellDoubleValue(row.getCell(2)));
            data.setNem(getCellDoubleValue(row.getCell(3)));
            data.setIsik(getCellDoubleValue(row.getCell(4)));
            data.setAmonyak(getCellDoubleValue(row.getCell(5)));

            double thi = thiHesapla(data.getSicaklik(), data.getNem());
            data.setThi(thi);
            data.setDurum(durumBelirle(thi, data.getAmonyak()));

            analysisEnvironmentRepository.save(data);
        }
    }

    private void importAnalysisProductivity(Sheet sheet) {
        boolean firstRow = true;

        for (Row row : sheet) {
            if (firstRow) {
                firstRow = false;
                continue;
            }

            if (row == null || row.getCell(0) == null) {
                continue;
            }

            AnalysisProductivity data = new AnalysisProductivity();

            data.setTarih(getCellLocalDate(row.getCell(0)));
            data.setSaat(getCellLocalTime(row.getCell(1)));
            data.setHayvanId((int) getCellDoubleValue(row.getCell(2)));
            data.setYemTuketimi(getCellDoubleValue(row.getCell(3)));
            data.setSutVerimi(getCellDoubleValue(row.getCell(4)));
            data.setDurum(verimDurumuBelirle(data.getSutVerimi()));

            analysisProductivityRepository.save(data);
        }
    }

    private double thiHesapla(double sicaklik, double nem) {
        return Math.round(
                ((1.8 * sicaklik + 32) -
                        ((0.55 - 0.0055 * nem) * (1.8 * sicaklik - 26)))
                        * 10.0
        ) / 10.0;
    }

    private String durumBelirle(double thi, double amonyak) {
        if (thi >= 78 || amonyak >= 25) {
            return "Kritik";
        } else if (thi >= 72 || amonyak >= 20) {
            return "Risk";
        } else {
            return "Normal";
        }
    }

    private String verimDurumuBelirle(double sutVerimi) {
        if (sutVerimi < 3) {
            return "Düşük Verim";
        } else if (sutVerimi < 4) {
            return "Orta Verim";
        } else {
            return "Normal Verim";
        }
    }

    private LocalDate getCellLocalDate(Cell cell) {
        if (cell == null) {
            return null;
        }

        if (cell.getCellType() == CellType.NUMERIC && DateUtil.isCellDateFormatted(cell)) {
            return cell.getDateCellValue()
                    .toInstant()
                    .atZone(ZoneId.systemDefault())
                    .toLocalDate();
        }

        String value = getCellStringValue(cell);

        try {
            return LocalDate.parse(value);
        } catch (Exception e) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
            return LocalDate.parse(value, formatter);
        }
    }

    private LocalTime getCellLocalTime(Cell cell) {
        if (cell == null) {
            return null;
        }

        if (cell.getCellType() == CellType.NUMERIC && DateUtil.isCellDateFormatted(cell)) {
            return cell.getDateCellValue()
                    .toInstant()
                    .atZone(ZoneId.systemDefault())
                    .toLocalTime()
                    .withSecond(0)
                    .withNano(0);
        }

        String value = getCellStringValue(cell);

        if (value.length() == 5) {
            return LocalTime.parse(value);
        }

        return LocalTime.parse(value.substring(0, 5));
    }

    private String getCellStringValue(Cell cell) {
        if (cell == null) {
            return "";
        }
        return cell.toString().trim();
    }

    private double getCellDoubleValue(Cell cell) {
        if (cell == null) {
            return 0;
        }

        if (cell.getCellType() == CellType.NUMERIC) {
            return cell.getNumericCellValue();
        }

        String value = cell.toString().trim().replace(",", ".");
        return Double.parseDouble(value);
    }
}