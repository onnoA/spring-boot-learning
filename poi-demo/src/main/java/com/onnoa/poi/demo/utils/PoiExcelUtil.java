package com.onnoa.poi.demo.utils;


import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.List;
import java.util.Map;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * @Description: Apache Poi Excel 工具类
 * @Author: onnoA
 * @Date: 2019/7/12 15:22
 */
public class PoiExcelUtil {

    public static File export(String[] titles, String[] keyList, String fileName, List<Map<String,Object>> mapList) throws Exception{
        try{
            // 第一步，创建一个workbook，对应一个Excel文件
            HSSFWorkbook workbook = new HSSFWorkbook();

            // 第二步，在webbook中添加一个sheet,对应Excel文件中的sheet
            HSSFSheet hssfSheet = workbook.createSheet("sheet1");

            // 第三步，在sheet中添加表头第0行,注意老版本poi对Excel的行数列数有限制short
            HSSFRow row = hssfSheet.createRow(0);
            // 第四步，创建单元格，并设置值表头 设置表头居中
            HSSFCellStyle hssfCellStyle = workbook.createCellStyle();

            //居中样式
            hssfCellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);

            HSSFCell hssfCell = null;
            for (int i = 0; i < titles.length; i++) {
                hssfCell = row.createCell(i);//列索引从0开始
                hssfCell.setCellValue(titles[i]);//列名1
                hssfCell.setCellStyle(hssfCellStyle);//列居中显示
            }
            for (int i = 0; i < mapList.size(); i++) {
                row = hssfSheet.createRow(i+1);
                Map<String,Object> map = mapList.get(i);
                for(int j=0;j<keyList.length;j++) {
                    if(map.get(keyList[j])==null){
                        row.createCell(j).setCellValue("");
                    }else{
                        row.createCell(j).setCellValue(map.get(keyList[j]).toString());
                    }
                }
            }
            File file = File.createTempFile(fileName, ".xlsx");
            file.deleteOnExit();
            FileOutputStream outputStream = new FileOutputStream(file);
            try {
                workbook.write(outputStream);
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                outputStream.close();
            }
            return file;

            // 第七步，将文件输出到客户端浏览器
//            try {
//                workbook.write(out);
//                out.flush();
//                out.close();
//
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
        }catch(Exception e){
            e.printStackTrace();
            throw new Exception("导出信息失败！");
        }
    }



    private static Logger logger = LoggerFactory.getLogger(PoiExcelUtil.class);
    /**
     * 设置excel压缩文件导出时的请求头
     */
    public static void setResponseHeader(final HttpServletResponse response, final String fileName) {

        logger.info("begin to set response header");
        try {
            response.reset();
            response.setContentType("application/octet-stream");
            response.setCharacterEncoding("UTF-8");
            response.addHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(fileName, "UTF-8"));
            response.addHeader("Pargam", "no-cache");
            response.addHeader("Cache-Control", "no-cache");
            logger.info("set response header successfully");

        } catch (final Exception e) {
            logger.error("set response header failed", e);
        }
    }
    /**
     * 生成excel文件
     */
    public static void toExcel(final String[] labels, final List<String[]> dataList, final HttpServletRequest request,
                               final String fileName, final List<String> fileNameList){
        logger.info("begin to create excel");
        final File dirFile = new File(request.getRealPath(File.separator + "excel") + File.separator);
        if (!dirFile.exists()) {
            dirFile.mkdirs();
        }
        final String file = request.getRealPath(File.separator + "excel") + File.separator + fileName + ".xls";
        FileOutputStream fos = null;
        Workbook workbook = null;
        try {
            fos = new FileOutputStream(file);
            workbook = new HSSFWorkbook();
            final Sheet sheet = workbook.createSheet("数据列表");
            Row row = sheet.createRow(0);
            for (int i = 0; i < labels.length; i++) {
                row.createCell(i).setCellValue(labels[i]);
            }
            for (int i = 0; i < dataList.size(); i++) {
                final String[] vals = dataList.get(i);
                row = sheet.createRow(i + 1);
                for (int j = 0; j < vals.length; j++) {
                    row.createCell(j).setCellValue(vals[j]);
                }
            }
            workbook.write(fos);
            fos.flush();
            fileNameList.add(file);
        } catch (final Exception e) {
            logger.error("create excel failed");
        } finally {
            if (fos != null) {
                try {
                    fos.close();
                } catch (final IOException e) {
                    logger.error("输出流关闭失败", e);
                }
            }
        }
    }
    public static String toZipFiles(final HttpServletRequest request, final List<String> fileNameList,
                                    final String zipFileName) {
        String zipFilePath = request.getRealPath(File.separator + "excel") + File.separator + zipFileName;
        logger.info("begin to create zip file");
        final File[] files = new File[fileNameList.size()];
        for (int i = 0; i < fileNameList.size(); i++) {
            files[i] = new File(fileNameList.get(i));
        }
        // 压缩文件
        final File zipFile = new File(zipFilePath);
        // 将excel文件压缩成zip文件
        final byte[] buf = new byte[1024];
        ZipOutputStream zipOut = null;
        FileInputStream fis = null;
        try {
            zipOut = new ZipOutputStream(new FileOutputStream(zipFile));
            for (int i = 0; i < files.length; i++) {
                fis = new FileInputStream(files[i]);
                zipOut.putNextEntry(new ZipEntry(files[i].getName()));
                int len = 0;
                while ((len = fis.read(buf)) > 0) {
                    zipOut.write(buf, 0, len);
                }
                zipOut.closeEntry();
                fis.close();
            }
        } catch (final Exception e) {
            zipFilePath = null;
            logger.error("failed to create zip file");
        } finally {
            if (zipOut != null) {
                try {
                    zipOut.close();
                } catch (final IOException e) {
                    logger.error("failed to close ZipOutputStream");
                }
            }
            if (fis != null) {
                try {
                    fis.close();
                } catch (final IOException e) {
                    logger.error("failed to close FileInputStream");
                }
            }
        }
        return zipFilePath;
    }

    public static void downloadZip(final OutputStream out, final String zipFilePath) {
        logger.info("begin to download zip file from " + zipFilePath);
        FileInputStream inStream = null;
        try {
            inStream = new FileInputStream(zipFilePath);
            final byte[] buf = new byte[4096];
            int readLength;
            while (((readLength = inStream.read(buf)) != -1)) {
                out.write(buf, 0, readLength);
            }
            out.flush();
        } catch (final Exception e) {
            logger.error("download zip excel failed");
        } finally {
            try {
                inStream.close();
            } catch (final IOException e) {
                logger.error("failed to close FileInputStream");
            }
            try {
                out.close();
            } catch (final IOException e) {
                logger.error("failed to close OutputStream");
            }
        }
    }
}
