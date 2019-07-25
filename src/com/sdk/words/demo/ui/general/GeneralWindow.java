package com.sdk.words.demo.ui.general;

import com.xiuye.window.FxmlWindow;

/**
 * 通用文字识别（含位置信息版）
 * @author admin
 *
 */
public class GeneralWindow {

	public static void main(String[] args) {
//		System.out.println(WordsWindow.class.getResource("app.fxml"));
		FxmlWindow.running("通用文字识别（含位置信息版）",GeneralWindow.class.getResource("general.fxml"));
	}

}
