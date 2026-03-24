package com.akilli.ahir.Service;

import com.akilli.ahir.Model.AnimalProductivity;
import com.akilli.ahir.Model.EnvironmentData;
import com.akilli.ahir.Repository.AnimalProductivityRepository;
import com.akilli.ahir.Repository.EnvironmentDataRepository;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.time.LocalDate;
import java.time.LocalTime;

@Service
public class ExcelService {

    private final EnvironmentDataRepository environmentDataRepository;
    private final AnimalProductivityRepository animalProductivityRepository;

    public ExcelService(EnvironmentDataRepository environmentDataRepository,
                        AnimalProductivityRepository animalProductivityRepository) {
        this.environmentDataRepository = environmentDataRepository;
        this.animalProductivityRepository = animalProductivityRepository;
    }

    public void importExcel(MultipartFile file) throws Exception {
        try (InputStream inputStream = file.getInputStream();
             Workbook workbook = new XSSFWorkbook(inputStream)) {

            Sheet environmentSheet = workbook.getSheet("environment_data");
            Sheet animalSheet = workbook.getSheet("animal_productivity");

            if (environmentSheet != null) {
                importEnvironmentData(environmentSheet);
            }

            if (animalSheet != null) {
                importAnimalProductivity(animalSheet);
            }
        }
    }

    private void importEnvironmentData(Sheet sheet) {
        boolean firstRow = true;

        for (Row row : sheet) {
            if (firstRow) {
                firstRow = false;
                continue;
            }

            if (row == null || row.getCell(0) == null) {
                continue;
            }

            EnvironmentData data = new EnvironmentData();

            data.setTarih(LocalDate.parse(getCellStringValue(row.getCell(0))));
            data.setSaat(LocalTime.parse(getCellStringValue(row.getCell(1))));
            data.setSicaklik(getCellDoubleValue(row.getCell(2)));
            data.setNem(getCellDoubleValue(row.getCell(3)));
            data.setIsik(getCellDoubleValue(row.getCell(4)));
            data.setAmonyak(getCellDoubleValue(row.getCell(5)));

            environmentDataRepository.save(data);
        }
    }

    private void importAnimalProductivity(Sheet sheet) {
        boolean firstRow = true;

        for (Row row : sheet) {
            if (firstRow) {
                firstRow = false;
                continue;
            }

            if (row == null || row.getCell(0) == null) {
                continue;
            }

            AnimalProductivity data = new AnimalProductivity();

            data.setTarih(LocalDate.parse(getCellStringValue(row.getCell(0))));
            data.setHayvanId((int) getCellDoubleValue(row.getCell(1)));
            data.setYemTuketimi(getCellDoubleValue(row.getCell(2)));
            data.setSutVerimi(getCellDoubleValue(row.getCell(3)));

            animalProductivityRepository.save(data);
        }
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

        return Double.parseDouble(cell.toString().trim());
    }
}