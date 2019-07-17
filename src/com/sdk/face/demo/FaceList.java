package com.sdk.face.demo;

import java.util.HashMap;

import org.json.JSONObject;

import com.sdk.face.util.Utils;

/**
 * 人脸管理库 获取用户人脸列表
 * @author admin
 *
 */
public class FaceList {

	public static void main(String[] args) {
		
		Utils.client().ifPresent(c -> {
		    // 传入可选参数调用接口
		    HashMap<String, String> options = new HashMap<String, String>();
		    
		    String userId = "liudehua";
		    String groupId = "group1";
		    
		    // 获取用户人脸列表//就是人脸库里用户id下面有多少张用户face
		    JSONObject res = c.faceGetlist(userId, groupId, options);
		    System.out.println(res.toString(2));


		});
	}

}
