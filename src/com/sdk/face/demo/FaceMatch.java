package com.sdk.face.demo;

import java.util.ArrayList;

import org.json.JSONObject;

import com.baidu.aip.face.MatchRequest;
import com.sdk.face.util.Utils;
/**
 * 人脸对比
 * @author admin
 *
 */
public class FaceMatch {

	public static void main(String[] args) {

		Utils.client().ifPresent(c -> {
			String filename1 = "faces/whiteMaleSideFace.png";
			String filename2 = "faces/whiteMaleFrontFace.png";
			String image1 = Utils.base64Image(filename1).get();
			String image2 = Utils.base64Image(filename2).get();

			// image1/image2也可以为url或facetoken, 相应的imageType参数需要与之对应。
			MatchRequest req1 = new MatchRequest(image1, "BASE64");
			MatchRequest req2 = new MatchRequest(image2, "BASE64");
			ArrayList<MatchRequest> requests = new ArrayList<MatchRequest>();
			requests.add(req1);
			requests.add(req2);

			JSONObject res = c.match(requests);
			System.out.println(res.toString(4));
		});

	}

}
