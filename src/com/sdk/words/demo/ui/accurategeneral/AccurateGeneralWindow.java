package com.sdk.words.demo.ui.accurategeneral;

import com.xiuye.window.FxmlWindow;

public class AccurateGeneralWindow {

	public static void main(String[] args) {
//		System.out.println(WordsWindow.class.getResource("app.fxml"));
		FxmlWindow.running("通用文字识别（含位置高精度版）",AccurateGeneralWindow.class.getResource("accurateGeneral.fxml"));
	}

}
