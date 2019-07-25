package com.sdk.words.demo.ui.basicgeneral;

import com.xiuye.window.FxmlWindow;

/**
 * 通用文字识别
 * @author admin
 *
 */
public class BasicGeneralWindow {

	public static void main(String[] args) {
//		System.out.println(WordsWindow.class.getResource("app.fxml"));
		FxmlWindow.running("文字识别通用识别",BasicGeneralWindow.class.getResource("basicGeneral.fxml"));
	}

}
