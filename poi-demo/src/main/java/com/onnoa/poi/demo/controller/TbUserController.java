package com.onnoa.poi.demo.controller;

import com.onnoa.poi.demo.dto.TbUserDto;
import com.onnoa.poi.demo.service.TbUserService;
import com.onnoa.poi.demo.utils.BeanUtils;
import com.onnoa.poi.demo.utils.DateUtil;
import com.onnoa.poi.demo.utils.PoiExcelUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @Description:
 * @Author: onnoA
 * @Date: 2019/7/12 15:19
 */
@RestController
@RequestMapping(value = "user")
public class TbUserController {

    @Autowired
    private TbUserService tbUserService;

    @RequestMapping(value = "downloadExcel")
    public String downloadExcel (HttpServletResponse response, HttpServletRequest request){
        String[] titles = new String[]{"用户名","电话","邮箱","创建时间","修改时间"};
        String[] keyList = new String[]{"username","phone","email","created","updated"};
        StringBuffer sb = new StringBuffer();
        String fileName = sb.append("用户管理").append(DateUtil.formate(new Date(),"yyyyMMddHHmmss")).append(".xlsx").toString();
        List<TbUserDto> tbUserDtoList = tbUserService.selectAll();
        List<Map<String,Object>> mapList = new ArrayList<>();
        for (TbUserDto tbUserDto : tbUserDtoList) {
            Map<String, Object> userDtoMap = BeanUtils.beanToMap(tbUserDto);
            mapList.add(userDtoMap);
        }
        try {
            File file = PoiExcelUtil.export(titles, keyList, fileName, mapList);
            byte[] bFile = Files.readAllBytes(file.toPath());
            PoiExcelUtil.setResponseHeader(response,fileName);
            BufferedOutputStream fos = new BufferedOutputStream(response.getOutputStream());
            if(bFile!=null){
                fos.write(bFile);
            }
            fos.flush();
            fos.close();
        } catch (Exception e) {
            e.printStackTrace();
            return "excel导出失败";
        }
        return "excel导出成功";
    }

}
