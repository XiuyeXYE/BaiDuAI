package com.xy.face.demo;



import com.xy.util.HttpUtil;
import com.xy.face.AuthService;
import com.xy.util.Base64Util;
import com.xy.util.FileUtil;
import com.xy.util.GsonUtils;

import java.util.*;
import java.util.Base64.Encoder;

/**
* 人脸检测与属性分析
*/
public class FaceDetect {

    /**
    * 重要提示代码中所需工具类
    * FileUtil,Base64Util,HttpUtil,GsonUtils请从
    * https://ai.baidu.com/file/658A35ABAB2D404FBF903F64D47C1F72
    * https://ai.baidu.com/file/C8D81F3301E24D2892968F09AE1AD6E2
    * https://ai.baidu.com/file/544D677F5D4E4F17B4122FBD60DB82B3
    * https://ai.baidu.com/file/470B3ACCA3FE43788B5A963BF0B625F3
    * 下载
    */
    public static String detect() {
        // 请求url
        String url = "https://aip.baidubce.com/rest/2.0/face/v3/detect";
        try {
            Map<String, Object> map = new HashMap<>();
//            map.put("image", "027d8308a2ec665acb1bdf63e513bcb9");
//            map.put("image", "https://ai.bdstatic.com/file/EF2B8C2B8BCD43DA931B218759D59C22");
            //清晰度高的图片识别效果好 face_probability >= 8.0
//            map.put("image", Base64Util.encode(FileUtil.readFileByBytes("faces/whiteMaleFrontFace.png")));
//            map.put("image", Base64Util.encode(FileUtil.readFileByBytes("faces/whiteMaleSideFace.png")));
            //半边脸效果不好
            map.put("image", Base64Util.encode(FileUtil.readFileByBytes("faces/whiteMalePartFace.png")));
//            map.put("image", Base64Util.encode(FileUtil.readFileByBytes("pic/blueFace.jpg")));
//            map.put("image", "b924a6568bf89138ac5aac64bce10104");
            //not recognize
//            map.put("image", Base64Util.encode(FileUtil.readFileByBytes("pic/bigNoseFace.jpg")));
//            map.put("image", Base64Util.encode(FileUtil.readFileByBytes("E:\\code3\\analyze\\laji2.jpg")));
//            map.put("image", Base64Util.encode(FileUtil.readFileByBytes("E:\\code3\\analyze\\zhumouq.jpg")));
//            Encoder encoder = Base64.getEncoder();
//            map.put("image", String.valueOf(encoder.encode(FileUtil.readFileByBytes("E:/code3/python-win32-quickstart3/faces.jpg"))));
//            map.put("face_field", "faceshape,facetype");
            map.put("face_field", "gender,age");
//            map.put("image_type", "FACE_TOKEN");
//            map.put("image_type", "URL");
            map.put("image_type", "BASE64");
            
            map.put("max_face_num", "10");//最大检测人脸个数

            String param = GsonUtils.toJson(map);

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
        FaceDetect.detect();
    }
}