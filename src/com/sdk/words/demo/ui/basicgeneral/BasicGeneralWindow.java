package com.sdk.words.demo.ui.basicgeneral;

import com.xiuye.window.FxmlWindow;

public class BasicGeneralWindow {

	public static void main(String[] args) {
//		System.out.println(WordsWindow.class.getResource("app.fxml"));
		FxmlWindow.running("文字识别通用识别",BasicGeneralWindow.class.getResource("basicGeneral.fxml"));
	}

}
