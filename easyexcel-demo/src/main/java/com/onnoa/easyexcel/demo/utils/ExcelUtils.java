package com.onnoa.easyexcel.demo.utils;

import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.metadata.BaseRowModel;
import com.alibaba.excel.support.ExcelTypeEnum;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.alibaba.excel.metadata.Sheet;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.Date;
import java.util.List;

/**
 * @Description:
 * @Author: onnoA
 * @Date: 2019/7/17 09:18
 */
@Slf4j
public class ExcelUtils {

    private static Logger LOGGER = LoggerFactory.getLogger(ExcelUtils.class);

    /**
     * 功能描述: 导出excel
     * @param fileName  文件名
     * @param data      导出的数据
     * @auther: onnoA
     * @date: 2019/7/17 10:20
     */
    public static File export(String fileName, List<? extends BaseRowModel> data,Class<? extends BaseRowModel> clazz)throws IOException{
        FileOutputStream outputStream = null;
        File file = null;
        try {
            Sheet sheet = new Sheet(1,0, clazz);
            fileName = fileName == null ? new Date().toString() :  fileName;
            file = File.createTempFile(fileName, ".xlsx");
            file.deleteOnExit();
            outputStream = new FileOutputStream(file);
            ExcelWriter writer = new ExcelWriter(outputStream, ExcelTypeEnum.XLSX);
            writer.write(data,sheet);
            writer.finish();
        } catch (IOException e) {
            throw new IOException("导出文件失败");
        }finally {
            outputStream.flush();
            outputStream.close();
        }
        return file;
    }
    /**
     * 设置excel文件导出时的请求头
     */
    public static void setResponseHeader(final HttpServletResponse response, final String fileName) {

        LOGGER.info("开始设置请求头");
        try {
            response.reset();
            response.setContentType("application/octet-stream");
            response.setCharacterEncoding("UTF-8");
            response.addHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(fileName + ".xlsx", "UTF-8"));
            response.addHeader("Pargam", "no-cache");
            response.addHeader("Cache-Control", "no-cache");
            LOGGER.info("设置请求头成功");
        } catch (final Exception e) {
            LOGGER.error("设置请求头失败", e);
        }
    }


}
