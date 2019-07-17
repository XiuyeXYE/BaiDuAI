package com.sdk.face.demo;

import java.util.ArrayList;

import org.json.JSONObject;

import com.baidu.aip.face.FaceVerifyRequest;
import com.sdk.face.util.Utils;
/**
 * 在线活体检测
 * @author admin
 *
 */
public class FaceLiveness {

	public static void main(String[] args) {
		Utils.client().ifPresent(c->{
			
//			String image = Utils.base64Image("faces/whiteMaleFrontFace.png").get();
			//斯大林
			String image = Utils.base64Image("faces/political_stalin_gray_male_face.jpg").get();
			FaceVerifyRequest req = new FaceVerifyRequest(image, "BASE64");
			ArrayList<FaceVerifyRequest> list = new ArrayList<FaceVerifyRequest>();
			list.add(req);
			JSONObject res = c.faceverify(list);
			System.out.println(res.toString(4));
		});
	}

}
