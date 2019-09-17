package com.onnoa.pay.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.onnoa.pay.config.impl.MyConfig;
import com.onnoa.pay.util.WXPayUtil;



/**
 * 微信支付成功后的回调链接
 */
@WebServlet("/WxCallbackServlet")
public class WxCallbackServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    public WxCallbackServlet() {
        super();
    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("**********************************************");
        doPost(request, response);
    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        System.out.println("來到了這裏");
        BufferedReader reader = request.getReader();
        String line="";
        StringBuffer inputString=new StringBuffer();
        while((line=reader.readLine())!=null){
            inputString.append(line);
        }
        request.getReader().close();
        System.out.println("接受到的報文"+inputString.toString());
        String result_code = "";
        String return_code = "";
        String out_trade_no = "";
        try {
            Map<String, String> map = WXPayUtil.xmlToMap(inputString.toString());
            result_code = map.get("result_code");
            out_trade_no = map.get("out_trade_no");
            return_code = map.get("return_code");
            MyConfig config=new MyConfig();
            //重新签名判断签名是否正确
            boolean signatureValid = WXPayUtil.isSignatureValid(map,config.getKey());
            if(signatureValid){//签名正确
                //更新数据库  1，订单 2，userid表
                //告诉微信服务器，我收到信息了，不要在调用回调action了
                //boolean updateOrderInfo = WxAndAliPayService.updateOrderInfo(out_trade_no);
                String returnXML = returnXML(return_code);
                response.getWriter().write(returnXML);
            }else{
                String returnXML = returnXML("FAIL");
                response.getWriter().write(returnXML);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    /**
     * 返回给微信服务器的消息
     * @param return_code
     * @return
     */
    private String returnXML(String return_code) {
        return "<xml><return_code><![CDATA["
                + return_code
                + "]]></return_code><return_msg><![CDATA[OK]]></return_msg></xml>";
    }
}
