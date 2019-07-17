package com.sdk.face.demo;

import java.io.IOException;
import java.util.HashMap;

import org.json.JSONObject;

import com.sdk.face.util.Utils;
import com.xiuye.util.log.LogUtil;
import com.xy.util.Base64Util;
import com.xy.util.FileUtil;

/**
 * 人脸注册到人脸管理库【百度AI创建人脸识别应用的时候有个人脸管理库】
 * 
 * @author admin
 *
 */
public class FaceRegister {

	public static void main(String[] args) {
		Utils.client().ifPresent(c -> {
			// 传入可选参数调用接口
			HashMap<String, String> options = new HashMap<String, String>();
			options.put("user_info", "刘德华");
			options.put("quality_control", "NORMAL");
			options.put("liveness_control", "LOW");
			//replace 用于人脸更新？
//			options.put("action_type", "REPLACE");//default value : APPEND

			String image;
			try {
				image = Base64Util.encode(FileUtil.readFileByBytes("faces/AndyLou/face-10.jpg"));
				String imageType = "BASE64";
				String groupId = "group1";
				String userId = "liudehua";

				// 人脸注册
				JSONObject res = c.addUser(image, imageType, groupId, userId, options);
				LogUtil.log(res.toString(4));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
		});
	}

}
