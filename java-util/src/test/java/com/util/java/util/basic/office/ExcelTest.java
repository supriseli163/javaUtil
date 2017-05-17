package com.util.java.util.basic.office;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.junit.Test;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class ExcelTest {
    @Test
    public void testReadExcel() throws IOException {
        Workbook wb = new HSSFWorkbook(new FileInputStream("/Users/supriseli/Documents/doc/session_data/excel1.xls"));
        //获取第一个工作薄
        Sheet st = wb.getSheetAt(0);
        for(int i = 0; i <= st.getLastRowNum(); i++) {
            Row row = st.getRow(i);
            //获取第一个单元格
            for(int j = 0; j <= row.getLastCellNum(); j++) {
                Cell cell = row.getCell(j);
                if(cell == null) {
                    continue;
                }
                if(cell.getCellType() == Cell.CELL_TYPE_STRING) {
                    System.err.println(cell.getStringCellValue() + "\t");
                } else if(cell.getCellType() == Cell.CELL_TYPE_NUMERIC) {
                    Double d = cell.getNumericCellValue();
                    //转换为int输出
                    System.err.println(d.intValue() + "\t");
                }
            }
        }
    }

    @Test
    public void testWriteExcel() throws IOException {
        Workbook wb = new HSSFWorkbook();
        Sheet st = wb.createSheet();
        Row row = st.createRow(0);
        Cell c0 = row.createCell(0);
        Cell c1 = row.createCell(1);
        Cell c2 = row.createCell(2);
        Cell c3 = row.createCell(3);
        Cell c5 = row.createCell(5);

        c0.setCellValue("index");
        c1.setCellValue("number");
        c2.setCellValue("name");
        c3.setCellValue("age");
        c5.setCellValue("sex");

        wb.write(new FileOutputStream("/Users/supriseli/Documents/doc/session_data/excel1.xls"));
    }

    @Test
    public void testCreateComplicateExcel() {
        HSSFWorkbook wb = new HSSFWorkbook();
        HSSFSheet sheet = wb.createSheet("成绩表");
        HSSFRow row1 = sheet.createRow(0);
        HSSFCell cell = row1.createCell(0);
        cell.setCellValue("学员考试成绩表");
        sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, 3));
        HSSFRow row2 = sheet.createRow(1);

    }
}
