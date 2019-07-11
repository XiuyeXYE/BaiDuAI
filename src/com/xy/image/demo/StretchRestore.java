package com.xy.image.demo;


import java.net.URLEncoder;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Map;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.xiuye.util.cls.TypeUtil;
import com.xiuye.util.log.LogUtil;
import com.xy.image.AuthService;
import com.xy.util.Base64Util;
import com.xy.util.FileUtil;
import com.xy.util.GsonUtils;
import com.xy.util.HttpUtil;

/**
* 图像处理——拉伸图像恢复
*/
public class StretchRestore {

    /**
    * 重要提示代码中所需工具类
    * FileUtil,Base64Util,HttpUtil,GsonUtils请从
    * https://ai.baidu.com/file/658A35ABAB2D404FBF903F64D47C1F72
    * https://ai.baidu.com/file/C8D81F3301E24D2892968F09AE1AD6E2
    * https://ai.baidu.com/file/544D677F5D4E4F17B4122FBD60DB82B3
    * https://ai.baidu.com/file/470B3ACCA3FE43788B5A963BF0B625F3
    * 下载
    */
    public static String stretchRestore() {
        // 请求url
        String url = "https://aip.baidubce.com/rest/2.0/image-process/v1/stretch_restore";
        try {
            // 本地文件路径
            String filePath = "E:\\code3\\analyze\\zhumouq.jpg";
            byte[] imgData = FileUtil.readFileByBytes(filePath);
            String imgStr = Base64Util.encode(imgData);
            String imgParam = URLEncoder.encode(imgStr, "UTF-8");

            String param = "image=" + imgParam;
            // 注意这里仅为了简化编码每一次请求都去获取access_token，线上环境access_token有过期时间， 客户端可自行缓存，过期后重新获取。
            String accessToken = AuthService.getAuth();

            String result = HttpUtil.post(url, accessToken, param);
            System.out.println(GsonUtils.format(result));
            
            @SuppressWarnings("unchecked")
			Map<String,Object> m = GsonUtils.fromJson(result, Map.class);
            
            LogUtil.log(m);
            
            String image = TypeUtil.dynamic_cast(m.get("image"));

            Files.write(Paths.get("laji.jpg"), com.baidu.aip.util.Base64Util.decode(image));
            
            
            
            return result;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void main(String[] args) {
        StretchRestore.stretchRestore();
    }
}