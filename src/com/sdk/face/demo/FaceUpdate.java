package com.sdk.face.demo;

import java.util.HashMap;

import org.json.JSONObject;

import com.sdk.face.util.Utils;
/**
 * 人脸更新 【更新人脸管理库】
 * @author admin
 *
 */
public class FaceUpdate {

	public static void main(String[] args) {

			Utils.client().ifPresent(c->{
				HashMap<String, String> options = new HashMap<String, String>();
			    options.put("user_info", "user's info");
			    options.put("quality_control", "NORMAL");
//			    options.put("liveness_control", "LOW");
			    options.put("action_type", "REPLACE");
			    
			    String image = Utils.base64Image("faces/AndyLou/face-11.jpg").get();
			    String imageType = "BASE64";
			    String groupId = "group1";
			    String userId = "user1";
			    
			    // 人脸更新
			    JSONObject res = c.updateUser(image, imageType, groupId, userId, options);
			    System.out.println(res.toString(2));

			});
	}

}
