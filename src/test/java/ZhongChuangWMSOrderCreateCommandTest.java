import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.itcuc.common.utils.HttpUtils;
import org.junit.Test;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.*;

public class ZhongChuangWMSOrderCreateCommandTest {
    private static final String HOST = "http://testweb.germanygou.cn/API/comprehensivePost.php";

    @Test
    public void testCreateOrder() {
        String key = "123456789123456789123456789123456789";
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
        SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        SimpleDateFormat sdf3 = new SimpleDateFormat("yyyy/MM/dd");
        //构建请求参数
        Map<String, Object> request = new TreeMap<>();
        //订单信息
        request.put("eCommerceCode","");//电商平台代码，不填则采用系统默认平台
        request.put("eCommerceName","");//电商平台名称，不填则采用系统默认名称
        request.put("msgid",sdf.format(new Date())+ String.valueOf((int)((Math.random()*9+1)*100000)));//建议采用YYYYMMDDHHMMSS+6位随机数字，表示系统内消息唯一性，如果重复的消息号表示该消息为对方系统重发消息
        request.put("payType","");// N,支付类型  01 在线支付 02 转单支付 01表示用订单中的数据申报，02表示用仓库的转单系统转单后发货
        request.put("payCompanyCode","ZF14021901");//支付公司编码 支付宝 ZF14021901 财付通 ZF14120401
        request.put("payNumber","");//N,支付单号
        request.put("orderTotalAmount","");//N,订单总金额,货款+订单税款+运费，即orderTotalAmount=orderGoodsAmount+orderTaxAmount+fee
        request.put("orderGoodsAmount","");//N,订单货款,表体部分商品总价
        request.put("orderNo","123456");//订单编号,电商平台订单号
        request.put("orderTaxAmount","");//N,订单税款,交易过程中商家向用户征收的税款，免税模式填写0
        request.put("fee","");//N,运杂费,默认为0.0000
        request.put("declareType","1");//N,申报方式
        request.put("commerceCode","");//N
        request.put("logisticNo","");//N
        //购买人信息
        request.put("purchaserId","");//N,购买人账户ID,不填报关时使用手机号码作为默认报关
        request.put("purchaserName","");//N,购买人姓名,不填默认采用收件人姓名作为购买人姓名
        request.put("purchaserCardNo","510722199504195049");//购买人身份证号
        //发件人信息
        request.put("shipper","小林");//发件人名称
        request.put("shipperPro","四川省");//发件人省份
        request.put("shipperCity","绵阳市");//发件人市
        request.put("shipperDistrict","三台县");//发件人区县
        request.put("shipperAdd","明泉路天天快递");//发件人地址
        request.put("shipperMobile","15208453373");//发件人手机
        //支付人信息
        request.put("payUserName","小林");//支付人姓名
        //收货人信息
        request.put("consignee","小林");//收货人姓名
        request.put("consigneePro","四川省");//收货人省份
        request.put("consigneeCity","绵阳市");//收货人市
        request.put("consigneeDistrict","三台县");//收货人区县
        request.put("consigneeAdd","名泉路天天快递");//收件人地址
        request.put("consigneeMobile","15208453373");//收货人手机
        request.put("inputdate",sdf1.format(new Date()));//N,订单日期,yyyy-MM-dd hh:mm:ss，这个是用于客户在货主系统上查看使用
        request.put("orderNoExt","");//N,OMS订单号,对接客户的OMS系统的预留字段
        request.put("comment","");//N,备注，打印在面单上的备注信息
        request.put("commentExt","");//N,特殊备注，不打印再面单上的备注信息，给备注人员看的信息

        request.put("logistic","");
        request.put("commerceName","");

        String signStr = getSignStr(request);



        //构造签名

        System.out.println("字典排序:"+signStr);
        try {
            signStr = URLEncoder.encode(signStr,"utf-8").replace("+","%C2%A0");
            System.out.println("urlEncode:" + signStr);
            signStr = Base64.getEncoder().encodeToString(signStr.getBytes("utf-8")).toLowerCase();
            System.out.println("base64小写:" + signStr);
            signStr = signStr + key;
            System.out.println("加key："+signStr);
//            signStr = signStr.toLowerCase();
//            signStr = (Base64Util.encodeToString(signStr.getBytes("utf-8"))).toLowerCase()+key;
        } catch (Exception e) {
            e.printStackTrace();
        }




        //商品明细
        ArrayList goods = new ArrayList();
        Map<String, Object> good = new TreeMap<>();
        good.put("partno","10000006");//产品编码,料号，与货主管理系统上备案料号一致
        good.put("qty","12");//数量
        good.put("amount","13");//金额,本表体内料号指定qty数量的商品不含税金额
        good.put("expirationDate","2019/03/17");//N,最早发货效期,YYYY/MM/DD 如2018/11/10表示发效期在2018年11月10日之后效期最近的商品，请务必保证该效期商品库存充足，否则可能消息推送失败
        goods.add(good);
        request.put("orderSkuList",goods);
        request.put("sign",signStr);

        request.put("tradeType","");//N,不填或者填0是保税模式，1为集货直邮,2是一般贸易
        request.put("totalCount","");//N,总件数，默认为1，表示订单不拆分包裹，不填表示为1

//        System.out.println(JSON.toJSONString(request) + "========================");

        //发送请求
        ObjectMapper mapper = new ObjectMapper();
        try {
            System.out.println(mapper.writeValueAsString(request));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        try {
            String result = HttpUtils.doPost(HOST,mapper.writeValueAsString(request));
//            String response = HttpUtil.postFormByHttp(HOST, JSON.toJSONString(request));
//            String resp = decodeUnicode(response);
//            System.out.println(resp + "------------------------------");
//            System.out.println("---------");
            System.out.println(decodeUnicode(result));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String [] args){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
        System.out.println(sdf.format(new Date()));
        System.out.println((int)((Math.random()*9+1)*100000));
    }

    public String getSignStr(Map request) {
        StringBuilder sb = new StringBuilder();
        Set<String> keys = request.keySet();
        for (String key : keys) {
            sb.append(key).append("=").append(request.get(key).toString()).append("&");
        }
        return sb.toString().substring(0,sb.lastIndexOf("&"));
    }


    public static String decodeUnicode(String theString) {
        char aChar;
        int len = theString.length();
        StringBuffer outBuffer = new StringBuffer(len);
        for (int x = 0; x < len;) {
            aChar = theString.charAt(x++);
            if (aChar == '\\') {
                aChar = theString.charAt(x++);
                if (aChar == 'u') {
                    // Read the xxxx
                    int value = 0;
                    for (int i = 0; i < 4; i++) {
                        aChar = theString.charAt(x++);
                        switch (aChar) {
                            case '0':
                            case '1':
                            case '2':
                            case '3':
                            case '4':
                            case '5':
                            case '6':
                            case '7':
                            case '8':
                            case '9':
                                value = (value << 4) + aChar - '0';
                                break;
                            case 'a':
                            case 'b':
                            case 'c':
                            case 'd':
                            case 'e':
                            case 'f':
                                value = (value << 4) + 10 + aChar - 'a';
                                break;
                            case 'A':
                            case 'B':
                            case 'C':
                            case 'D':
                            case 'E':
                            case 'F':
                                value = (value << 4) + 10 + aChar - 'A';
                                break;
                            default:
                                throw new IllegalArgumentException(
                                        "Malformed   \\uxxxx   encoding.");
                        }

                    }
                    outBuffer.append((char) value);
                } else {
                    if (aChar == 't')
                        aChar = '\t';
                    else if (aChar == 'r')
                        aChar = '\r';
                    else if (aChar == 'n')
                        aChar = '\n';
                    else if (aChar == 'f')
                        aChar = '\f';
                    outBuffer.append(aChar);
                }
            } else
                outBuffer.append(aChar);
        }
        return outBuffer.toString();
    }
    
    @Test
    public void test1() {
        String str = "{\"comment\":\"\",\"commentExt\":\"\",\"commerceCode\":\"\",\"commerceName\":\"\",\"consignee\":\"小林\",\"consigneeAdd\":\"名泉路天天快递\",\"consigneeCity\":\"绵阳市\",\"consigneeDistrict\":\"三台县\",\"consigneeMobile\":\"15208453373\",\"consigneePro\":\"四川省\",\"declareType\":\"1\",\"eCommerceCode\":\"\",\"eCommerceName\":\"\",\"fee\":\"\",\"inputdate\":\"2019-03-27 14:34:24\",\"logistic\":\"\",\"logisticNo\":\"\",\"msgid\":\"20190327143424710435\",\"orderGoodsAmount\":\"\",\"orderNo\":\"123456\",\"orderNoExt\":\"\",\"orderSkuList\":[{\"amount\":\"13\",\"expirationDate\":\"2019/03/17\",\"partno\":\"10000006\",\"qty\":\"12\"}],\"orderTaxAmount\":\"\",\"orderTotalAmount\":\"\",\"payCompanyCode\":\"ZF14021901\",\"payNumber\":\"\",\"payType\":\"\",\"payUserName\":\"小林\",\"purchaserCardNo\":\"510722199504195049\",\"purchaserId\":\"\",\"purchaserName\":\"\",\"shipper\":\"小林\",\"shipperAdd\":\"明泉路天天快递\",\"shipperCity\":\"绵阳市\",\"shipperDistrict\":\"三台县\",\"shipperMobile\":\"15208453373\",\"shipperPro\":\"四川省\"}";
        ObjectMapper mapper = new ObjectMapper();
        TreeMap map = new TreeMap();
        try {
            map = mapper.readValue(str,TreeMap.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            str = getSignStr(map);
            System.out.println(str);
            str = URLEncoder.encode(str,"utf-8");
            System.out.println(Base64.getEncoder().encodeToString(str.getBytes("utf-8")).toLowerCase());
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }
}

