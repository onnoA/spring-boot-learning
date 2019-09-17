package com.onnoa.easyexcel.demo;

import com.alibaba.excel.EasyExcelFactory;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.metadata.Sheet;
import com.onnoa.easyexcel.demo.dto.TbUserDto;
import com.onnoa.easyexcel.demo.service.TbUserService;
import com.onnoa.easyexcel.demo.utils.DateUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class EasyexcelDemoApplicationTests {

    @Autowired
    private TbUserService tbUserService;

    /**
     * 功能描述: 导入excel
     * @auther: onnoA
     * @date: 2019/7/12 13:57
     */
    @Test
    public void readExcel(){

    }

    /**
     * 功能描述: excel导出
     * @auther: onnoA
     * @date: 2019/7/12 13:56
     */
    @Test
    public void writeExcelOneSheet() throws FileNotFoundException {
        StringBuffer sb = new StringBuffer();
        String filePath = sb.append("用户管理").append(DateUtil.formate(new Date(),"yyyymmdd-hhmmss")).append(".xlsx").toString();
        FileOutputStream outputStream = new FileOutputStream("F:\\code\\springboot-learning\\" + filePath);
        ExcelWriter writer = EasyExcelFactory.getWriter(outputStream);
        // 写仅有一个 Sheet 的 Excel 文件, 此场景较为通用
        // TbUserDto 这个对象就是要写入 Excel 的数据模型对象，
        Sheet sheet = new Sheet(1, 0,TbUserDto.class);
        // 第一个 sheet 名称
        sheet.setSheetName("sheet1");
        List<TbUserDto> tbUserDtoList = tbUserService.selectAll();
        // 写数据到 Writer 上下文中
        // 入参1: 创建要写入的模型数据
        // 入参2: 要写入的目标 sheet
        writer.write(tbUserDtoList,sheet);
        // 将上下文中的最终 outputStream 写入到指定文件中
        writer.finish();
        try {
            // 关闭流
            outputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @Test
    public void testExcel2003NoModel() {
        List<TbUserDto> tbUserDtos = tbUserService.selectAll();
        tbUserDtos.forEach(tbUser -> System.out.println(tbUser));
    }

}
