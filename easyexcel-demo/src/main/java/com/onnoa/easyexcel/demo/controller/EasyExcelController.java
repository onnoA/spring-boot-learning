package com.onnoa.easyexcel.demo.controller;

import com.onnoa.easyexcel.demo.dto.TbUserDto;
import com.onnoa.easyexcel.demo.service.TbUserService;
import com.onnoa.easyexcel.demo.utils.DateUtil;
import com.onnoa.easyexcel.demo.utils.ExcelUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Date;

/**
 * @Description: EasyExcel 工具类
 * @Author: onnoA
 * @Date: 2019/7/12 09:16
 */
@RestController
@RequestMapping(value = "excel")
public class EasyExcelController {

    private static Logger logger = LoggerFactory.getLogger(EasyExcelController.class);

    @Autowired
    private TbUserService tbUserService;

    @GetMapping(value = "export")
    public String export(HttpServletResponse response){
        try {
            StringBuffer sb = new StringBuffer();
            String fileName = sb.append("用户管理报表").append(DateUtil.formate(new Date(),"yyyyMMddHHmmss")).toString();
            File file = ExcelUtils.export(fileName, tbUserService.selectAll(),TbUserDto.class);
           ExcelUtils.setResponseHeader(response,fileName);
            byte[] bFile = Files.readAllBytes(file.toPath());
            BufferedOutputStream fos = new BufferedOutputStream(response.getOutputStream());
            if(bFile!=null){
                fos.write(bFile);
            }
            fos.flush();
            fos.close();
        } catch (IOException e) {
            e.printStackTrace();
            logger.error("文件导出失败");
            return "文件导出失败";
        }
        return "文件导出成功";
    }

}
