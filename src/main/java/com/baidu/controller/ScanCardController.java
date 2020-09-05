//package com.lzq.jpa.controller;
//
//import com.fasterxml.jackson.databind.ObjectMapper;
//import com.triman.common.util.SpringContextHolder;
//import com.triman.shsyrkzzservice.pojo.AddressResolution;
//import com.triman.shsyrkzzservice.utils.AddressResolutionUtil;
//import org.apache.http.HttpEntity;
//import org.apache.http.client.methods.CloseableHttpResponse;
//import org.apache.http.client.methods.HttpPost;
//import org.apache.http.entity.mime.MultipartEntityBuilder;
//import org.apache.http.entity.mime.content.ByteArrayBody;
//import org.apache.http.impl.client.CloseableHttpClient;
//import org.apache.http.impl.client.HttpClients;
//import org.apache.http.util.EntityUtils;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.RestController;
//import org.springframework.web.multipart.MultipartFile;
//
//import java.io.IOException;
//import java.nio.charset.Charset;
//import java.util.HashMap;
//import java.util.Map;
//
//@RestController
//public class ScanCardController {
//    private static final String ENGINE_URL = "http://www.trimansoft.com/PIM_IDCard/SrvXMLAPI";
//
//    @RequestMapping(value = "/idcard/recognize", method = RequestMethod.POST)
//    public Map scan(@RequestParam("file") MultipartFile file) throws IOException {
//
//        Map result = new HashMap();
//
//        CloseableHttpClient httpclient = HttpClients.createDefault();
//        HttpPost httppost = new HttpPost(ENGINE_URL);
//        HttpEntity reqEntity = MultipartEntityBuilder.create()
//                .addPart("file", new ByteArrayBody(file.getBytes(), "a.jpg"))
//                .build();
//        httppost.setEntity(reqEntity);
//        CloseableHttpResponse response = null;
//        try {
//            response = httpclient.execute(httppost);
//            HttpEntity resEntity = response.getEntity();
//            String res = EntityUtils.toString(resEntity, Charset.forName("UTF-8"));
//            ObjectMapper objectMapper = new ObjectMapper();
//            Map map = objectMapper.readValue(res.toLowerCase(), Map.class);
//            String status = (String) map.get("status");
//            Map contenMap = (Map) map.get("content");
//            String period = null;
//            if ("ok".equals(status)) {
//                period = (String) contenMap.get("valid_period");
//                String cardno = (String) contenMap.get("cardno");
//                String name = (String) contenMap.get("name");
//                String address = (String) contenMap.get("address");
//
//                AddressResolution addressResolution = new AddressResolution();
//
//                String xzqh_dm = "";
//                addressResolution = AddressResolutionUtil.addressResolution(address);
//
//                String xzqh = addressResolution.getProvince() + addressResolution.getCity() + addressResolution.getCounty();
////              先用省市县匹配
//                xzqh_dm = SpringContextHolder.dictionaryService.getDmByMc("D_XZQH", xzqh);
//
//                if ("".equals(xzqh_dm)) {
////                    再用省市匹配
//                    xzqh_dm = SpringContextHolder.dictionaryService.getDmByMc("D_XZQH", addressResolution.getProvince() + addressResolution.getCity());
//                }
//                if ("".equals(xzqh_dm)) {
////                    再用省匹配
//                    xzqh_dm = SpringContextHolder.dictionaryService.getDmByMc("D_XZQH", addressResolution.getProvince());
//                }
//                System.out.println("----------------" + xzqh_dm);
//                if ("".equals(xzqh_dm)) {
////                    如果还为空
//                    contenMap.put("hjd", cardno.substring(0, 6));
//                } else {
//                    contenMap.put("hjd", xzqh_dm);
//                }
//                if (null != cardno) {
//                    if (cardno.indexOf('x') != -1) {
//                        cardno = cardno.replace("x", "X");
//                        contenMap.put("cardno", cardno);
//                    }
////                    contenMap.put("hjd", cardno.substring(0,6));
//                }
//                contenMap.put("zjzl", "01");
//                contenMap.put("hjd", xzqh_dm);
//                String sex = (String) contenMap.get("sex");
//                String xbdm = SpringContextHolder.dictionaryService.getDmByMc("D_XB", sex);
//                contenMap.put("xbdm", xbdm);
//                String birthday = (String) contenMap.get("birthday");
//                String csrq = birthday.replace("年", "-").replace("月", "-").replace("日", "");
//                contenMap.put("csrq", csrq);
//                String folk = (String) contenMap.get("folk");
//                String mzdm = SpringContextHolder.dictionaryService.getDmByMc("D_MZ", folk + "族");
//                contenMap.put("mzdm", mzdm);
//                if (null == period) {
//                    if (cardno == null || cardno.length() != 18 || name == null || name.length() < 2) {
//                        result.put("status", "ERROR");
//                    } else {
//                        result = map;
//                    }
//                } else {
//                    result = map;
//                }
//            } else {
//                result.put("status", "ERROR");
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//            result.put("status", "ERROR");
//        } finally {
//            try {
//                response.close();
//                httpclient.close();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }
//        System.out.println(result.toString());
//        return result;
//    }
//}
//
