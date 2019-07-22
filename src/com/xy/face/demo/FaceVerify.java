package com.xy.face.demo;



import java.util.HashMap;
import java.util.Map;

import com.xy.face.AuthService;
import com.xy.util.Base64Util;
import com.xy.util.FileUtil;
import com.xy.util.GsonUtils;
import com.xy.util.HttpUtil;

/**
* 在线活体检测
*/
public class FaceVerify {

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
        String url = "https://aip.baidubce.com/rest/2.0/face/v3/faceverify";
        try {
            Map<String, Object> map = new HashMap<>();
//            map.put("image", "027d8308a2ec665acb1bdf63e513bcb9");
//            map.put("image", "https://ai.bdstatic.com/file/EF2B8C2B8BCD43DA931B218759D59C22");
            
            map.put("image", Base64Util.encode(FileUtil.readFileByBytes("E:/code3/python-win32-quickstart3/faces.jpg")));
//            map.put("image", Base64Util.encode(FileUtil.readFileByBytes("E:code3analyzelaji2.jpg")));
//            map.put("image", Base64Util.encode(FileUtil.readFileByBytes("E:code3analyzezhumouq.jpg")));
//            Encoder encoder = Base64.getEncoder();
//            map.put("image", String.valueOf(encoder.encode(FileUtil.readFileByBytes("E:/code3/python-win32-quickstart3/faces.jpg"))));
//            map.put("face_field", "faceshape,facetype");
            map.put("face_field", "gender,age");
//            map.put("image_type", "FACE_TOKEN");
//            map.put("image_type", "URL");
            map.put("image_type", "BASE64");
            map.put("max_face_num", "10");

            String param = GsonUtils.toJson(map);
            
            param = "[{\"image\":\""+ Base64Util.encode(FileUtil.readFileByBytes("E:/code3/python-win32-quickstart3/faces.jpg"))+"\",\"image_type\":\"BASE64\",\"face_field\":\"age,beauty,expression\"},"
            		+ "{\"image\":\""+ Base64Util.encode(FileUtil.readFileByBytes("E:/code3/python-win32-quickstart3/faces.jpg"))+"\",\"image_type\":\"BASE64\",\"face_field\":\"age,beauty,expression\"}]";

            // 注意这里仅为了简化编码每一次请求都去获取access_token，线上环境access_token有过期时间， 客户端可自行缓存，过期后重新获取。
//            String accessToken = "[调用鉴权接口获取的token]";
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
    	FaceVerify.faceVerify();
    }
}