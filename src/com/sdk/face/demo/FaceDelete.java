package com.sdk.face.demo;

import java.util.HashMap;

import org.json.JSONObject;

import com.sdk.face.util.Utils;
import com.xiuye.util.log.LogUtil;
import com.xy.util.Base64Util;
/**
 * 人脸删除【人脸管理库】
 * @author admin
 *
 */
public class FaceDelete {

	public static void main(String[] args) {

			Utils.client().ifPresent(c->{
				
				
				
				 // 传入可选参数调用接口
			    HashMap<String, String> options = new HashMap<String, String>();
			    
//			    JSONObject res1 = c.detect(Utils.base64Image("faces/AndyLou/face-1.jpg").get(), "BASE64", options);
//			    LogUtil.log(res1.toString(4));
			    
			    String userId = "liudehua";
			    String groupId = "group1";
			    //可以精确定位到用户下的特征脸删除
			    String faceToken = "c833ff0ad940dd1b068557e09101e86f";
			    
			    // 人脸删除
			    JSONObject res = c.faceDelete(userId, groupId, faceToken, options);
			    System.out.println(res.toString(2));
//			    
//			    {
//			    	  "result": null,
//			    	  "log_id": 1368654433530304281,
//			    	  "error_msg": "SUCCESS",
//			    	  "cached": 0,
//			    	  "error_code": 0,
//			    	  "timestamp": 1563353030
//			    	}
			    
			});
	}

}
