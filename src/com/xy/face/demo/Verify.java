package com.xy.face.demo;

import java.net.URLEncoder;
import java.util.List;
import java.util.Map;

import com.xiuye.util.cls.TypeUtil;
import com.xy.face.AuthService;
import com.xy.util.Base64Util;
import com.xy.util.FileUtil;
import com.xy.util.GsonUtils;
import com.xy.util.HttpUtil;

/**
 * 视频活体检测接口
 */
public class Verify {

	/**
	 * 重要提示代码中所需工具类 FileUtil,Base64Util,HttpUtil,GsonUtils请从
	 * https://ai.baidu.com/file/658A35ABAB2D404FBF903F64D47C1F72
	 * https://ai.baidu.com/file/C8D81F3301E24D2892968F09AE1AD6E2
	 * https://ai.baidu.com/file/544D677F5D4E4F17B4122FBD60DB82B3
	 * https://ai.baidu.com/file/470B3ACCA3FE43788B5A963BF0B625F3 下载
	 */
	public static String faceVerify() {
		// 请求url
		String url = "https://aip.baidubce.com/rest/2.0/face/v1/faceliveness/verify";
		try {

			String param = URLEncoder.encode("video_base64", "UTF-8") + "="
					+ URLEncoder.encode(Base64Util.encode(FileUtil.readFileByBytes(
							"C:\\Users\\admin\\Documents\\WeChat Files\\XiuyeXY\\FileStorage\\Video\\2019-07\\eb2d6b5fce3bd266cb9c16bf56ae3f48.mp4")),
							"UTF-8");

			String accessToken = AuthService.getAuth();

			String result = HttpUtil.post(url, accessToken, param);
//			System.out.println(GsonUtils.format(result));
			
			FileUtil.writeFile("my/result2.json", GsonUtils.format(result).getBytes());
			
			@SuppressWarnings("unchecked")
			Map<String, Object> m = GsonUtils.fromJson(result, Map.class);
			Map<String, Object> res = TypeUtil.dynamic_cast(m.get("result"));
			List<Map<String, Object>> picList = TypeUtil.dynamic_cast(res.get("pic_list"));

			for (Map<String, Object> pic : picList) {
				FileUtil.writeFile("my/"+pic.get("face_id") + ".jpg",
						com.baidu.aip.util.Base64Util.decode(TypeUtil.dynamic_cast(pic.get("pic"))));
			}

			return result;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public static void main(String[] args) {
		Verify.faceVerify();
	}
}