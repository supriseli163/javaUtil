package com.base.java.util.xls;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class ExcelUtil {
    /**
     * 将workbook直接转成可供输出下载的InputStream
     * @param hssfWorkbook
     * @return
     * @throws IOException
     */
    public static InputStream exportxcel(HSSFWorkbook hssfWorkbook) throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        try {
            hssfWorkbook.write(baos);
            baos.flush();
            byte[] aa = baos.toByteArray();
            InputStream inputStream = new ByteArrayInputStream(aa, 0, aa.length);
            return inputStream;
        } finally {
            baos.close();
        }
    }
}
