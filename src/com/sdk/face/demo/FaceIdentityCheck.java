package com.sdk.face.demo;

import java.util.HashMap;

import org.json.JSONObject;

import com.sdk.face.util.Utils;
/**
 * 身份验证
 * @author admin
 *
 */
public class FaceIdentityCheck {

	
	public static void main(String[] args) {
		Utils.client().ifPresent(c->{
			// 传入可选参数调用接口
		    HashMap<String, String> options = new HashMap<String, String>();
		    options.put("quality_control", "NORMAL");
		    options.put("liveness_control", "LOW");
		    
		    String image = Utils.base64Image("faces/whiteMaleFrontFace.png").get();
		    String imageType = "BASE64";
		    String idCardNumber = "110233112299822211";
		    String name = "张三";
		    
		    // 身份验证
		    JSONObject res = c.personVerify(image, imageType, idCardNumber, name, options);
		    System.out.println(res.toString(2));

		});
	}
	
}
