package utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtil {

    public static Object[][] getTestData(String filePath, String sheetName) {

        List<Object[]> dataList = new ArrayList<>();

        try {
            FileInputStream file = new FileInputStream(filePath);
            Workbook workbook = new XSSFWorkbook(file);
            Sheet sheet = workbook.getSheet(sheetName);

            Iterator<Row> rows = sheet.iterator();

            rows.next(); // Skip header row

            while (rows.hasNext()) {
                Row row = rows.next();

                List<String> cellData = new ArrayList<>();

                for (Cell cell : row) {
                    cellData.add(cell.toString());
                }

                dataList.add(cellData.toArray());
            }

            workbook.close();
            file.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

        Object[][] data = new Object[dataList.size()][];

        for (int i = 0; i < dataList.size(); i++) {
            data[i] = dataList.get(i);
        }

        return data;
    }
}