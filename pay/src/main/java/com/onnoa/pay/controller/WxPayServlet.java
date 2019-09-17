package com.onnoa.pay.controller;


import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.onnoa.pay.config.impl.MyConfig;
import com.onnoa.pay.pay.WXPay;
import com.onnoa.pay.util.WXPayUtil;
import org.apache.poi.ss.usermodel.DateUtil;

import com.github.wxpay.sdk.WXPayConstants.SignType;
import org.json.JSONObject;


/**
 * 微信支付请求链接
 */
@WebServlet("/WxPayServlet")
public class WxPayServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    public WxPayServlet() {
        super();
    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        String ip = request.getParameter("ip");
        String userid = request.getParameter("userid");
        String cardid = request.getParameter("cardid");
        String mess = getMess(ip,cardid,userid);
        response.getWriter().print(mess);
    }
    private String getMess(String ip, String cardid,String userid) {
        //String uid = WxAndAliPayService.getuid(userid);
        JSONObject orderInfo = null; //WxAndAliPayService.getOrderInfo(cardid);
        JSONObject joo=new JSONObject();
        try {
            String cardname= null; //orderInfo.getString("cardname");
            String cardprice=null; //orderInfo.getString("cardprice");
            System.out.println("money"+cardprice);
            float cardprice1=Float.parseFloat(cardprice)*100;//微信的支付单位是分所以要转换一些单位
            int cardmoney=(int) cardprice1;
            System.out.println("money1"+cardmoney);
            String totalproce=String.valueOf(cardmoney);
            //String orderNum = UtilDate.getOrderNum();
            //插入一条订单记录
           //WxAndAliPayService.insertOrderInfo(cardname,uid,orderNum,cardprice,"微信支付购买会员卡");
            MyConfig config = new MyConfig();
            WXPay wxpay = new WXPay(config);

            Map<String, String> data = new HashMap<String, String>();
            data.put("body", "哈哈哈-"+cardname);
            // data.put("out_trade_no", orderNum);//生成随机订单号
            data.put("total_fee", totalproce);
            data.put("spbill_create_ip",ip);
            data.put("notify_url", MyConfig.service);
            data.put("trade_type", "APP");  // 此处app支付

            // 调取微信支付ID接口
            Map<String, String> resp = wxpay.unifiedOrder(data);

            JSONObject jo=new JSONObject(resp);
            Map<String, String> data1 = new HashMap<String, String>();
            if(jo.get("result_code").equals("SUCCESS") &&jo.get("return_code").equals("SUCCESS")){
                data1.put("appid",config.getAppID());
                //data1.put("partnerid", UtilDate.getOrderNum());//生成随机订单号
                data1.put("prepayid", jo.getString("prepay_id"));
                data1.put("package","Sign=WXPay");
                data1.put("noncestr",jo.getString("nonce_str"));
                //data1.put("timestamp", DateUtil.getTimestamp());
                String sign = WXPayUtil.generateSignature(data1,config.getKey(),SignType.MD5);//再签名一次
                System.out.println(sign);
                data1.put("sign", sign);
            }
            JSONObject jj=new JSONObject(data1);
            joo.put("info", jj);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return joo.toString();
    }
}
