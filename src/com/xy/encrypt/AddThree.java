package com.xy.encrypt;

import com.xiuye.util.log.LogUtil;

public class AddThree {

	static char[] letters = { 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r',
			's', 't', 'u', 'v', 'w', 'x', 'y', 'z' };

	public static void main(String[] args) {
		char a = 'a';
//		for (int i = 0; i < 26; i++) {
//			LogUtil.log("'" + (char) (a + i) + "',");
//		}
		int total = 26;
		
		String s = "henddddtai";
		char []chs = s.toCharArray();
		LogUtil.log(chs[0]);
		for(int i=0;i<chs.length;i++) {
			int  ch = chs[i] - 'a';
			chs[i] = letters[(ch+3)%total];
		}
		LogUtil.log(chs[0]);
		LogUtil.log(String.valueOf(chs));
		
		for(int i=0;i<chs.length;i++) {
			int  ch = chs[i] - 'a';
			chs[i] = letters[(ch-3)%total];
		}
		LogUtil.log(chs[0]);
		LogUtil.log(String.valueOf(chs));
		
		
	}

}
