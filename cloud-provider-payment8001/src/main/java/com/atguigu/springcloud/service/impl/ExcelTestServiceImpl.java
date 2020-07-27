package com.atguigu.springcloud.service.impl;

import com.alibaba.excel.util.CollectionUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.core.io.ClassPathResource;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

@Slf4j
public class ExcelTestServiceImpl {

    /**
     * 导出数据
     *
     * @param dataList：数据
     * @param fileName：模板名称
     * @param response：
     */
    public static void export(List<List<Object>> dataList, String fileName, HttpServletResponse response) {
        if (null == dataList || StringUtils.isEmpty(fileName)) {
            return;
        }
        String filePath = fileName + ".xlsx";
        ClassPathResource cpr = new ClassPathResource("/templates/" + filePath);
        InputStream is;
        Workbook workbook = new XSSFWorkbook();
        try {
            is = cpr.getInputStream();
            workbook = new XSSFWorkbook(is);
            Sheet sheet0 = workbook.getSheetAt(0);
            log.info("======" + fileName + "|下载开始======");
            for (int i = 0; i < dataList.size(); i++) {
                List<Object> list = CollectionUtils.isEmpty(dataList.get(i)) ? new ArrayList<>() : dataList.get(i);
                for (int j = 0; j < list.size(); j++) {
                    Row row = sheet0.getRow(i + 1);//从第一行开始填充数据
                    if (row == null) {
                        row = sheet0.createRow(i + 1);
                    }
                    String cell = null == list.get(j) ? "" : String.valueOf(list.get(j));
                    row.createCell(j).setCellValue(cell);
                }
            }
            is.close();
            log.info("=======" + fileName + "|workbook=========" + workbook);
        } catch (IOException e) {
            log.info("下载异常" + e);
        }
        try {
            fileName = new String(fileName.getBytes("UTF-8"), "ISO-8859-1");
        } catch (IOException e) {
            log.error(fileName + "|fileName.getBytes()出现异常", e);
        }
        response.setContentType("application/vnd.ms-excel");
        response.setCharacterEncoding("UTF-8");
        response.setHeader("Content-Disposition", "Attachment;FileName=" + fileName + ".xlsx");
        try {
            OutputStream out = response.getOutputStream();
            workbook.write(out);
            out.close();
        } catch (IOException e) {
            log.error("=====" + fileName + "|workbook下载异常=====", e);
        }
    }

}
