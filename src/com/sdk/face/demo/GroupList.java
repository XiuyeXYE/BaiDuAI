package com.sdk.face.demo;

import java.util.HashMap;

import org.json.JSONObject;

import com.sdk.face.util.Utils;

public class GroupList {

	public static void main(String[] args) {

		Utils.client().ifPresent(c->{
			 // 传入可选参数调用接口
		    HashMap<String, String> options = new HashMap<String, String>();
		    options.put("start", "0");
		    options.put("length", "50");
		    
		    
		    // 组列表查询
		    JSONObject res = c.getGroupList( options);
		    System.out.println(res.toString(2));
		});		
	}

}
