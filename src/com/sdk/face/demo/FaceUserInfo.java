package com.sdk.face.demo;

import java.util.HashMap;

import org.json.JSONObject;

import com.sdk.face.util.Utils;
/**
 * 人脸用户信息查询【人脸管理库】
 * @author admin
 *
 */
public class FaceUserInfo {

	public static void main(String[] args) {

			Utils.client().ifPresent(c->{
				
				 // 传入可选参数调用接口
			    HashMap<String, String> options = new HashMap<String, String>();
			    
			    String userId = "liudehua";
			    String groupId = "group1";
			    
			    // 用户信息查询
			    JSONObject res = c.getUser(userId, groupId, options);
			    System.out.println(res.toString(4));
			    
			});
	}

}
