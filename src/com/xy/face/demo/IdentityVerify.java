package com.xy.face.demo;



import com.xy.util.HttpUtil;
import com.xy.face.AuthService;
import com.xy.util.Base64Util;
import com.xy.util.FileUtil;
import com.xy.util.GsonUtils;

import java.util.*;
import java.util.Base64.Encoder;

/**
* 公安验证
*/
public class IdentityVerify {

    /**
    * 重要提示代码中所需工具类
    * FileUtil,Base64Util,HttpUtil,GsonUtils请从
    * https://ai.baidu.com/file/658A35ABAB2D404FBF903F64D47C1F72
    * https://ai.baidu.com/file/C8D81F3301E24D2892968F09AE1AD6E2
    * https://ai.baidu.com/file/544D677F5D4E4F17B4122FBD60DB82B3
    * https://ai.baidu.com/file/470B3ACCA3FE43788B5A963BF0B625F3
    * 下载
    */
    public static String faceVerify() {
        // 请求url
        String url = "https://aip.baidubce.com/rest/2.0/face/v3/person/verify";
        try {
        	  Map<String, Object> map = new HashMap<>();
              map.put("image", Base64Util.encode(FileUtil.readFileByBytes("pic/")));
              map.put("image_type", "BASE64");
              map.put("id_card_number", "1234");
              map.put("liveness_control", "HIGH");
              map.put("name", "张三");
              map.put("quality_control", "LOW");


            String param = GsonUtils.toJson(map);
            
            String accessToken = AuthService.getAuth();

            String result = HttpUtil.post(url, accessToken, "application/json", param);
            System.out.println(GsonUtils.format(result));
            return result;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void main(String[] args) {
    	IdentityVerify.faceVerify();
    }
}