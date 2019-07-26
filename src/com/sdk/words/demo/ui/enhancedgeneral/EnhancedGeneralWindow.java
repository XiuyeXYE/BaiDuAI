package com.sdk.words.demo.ui.enhancedgeneral;

import com.xiuye.window.FxmlWindow;

/**
 * 通用文字识别（含生僻字版）
 * @author admin
 *
 */
public class EnhancedGeneralWindow {

	public static void main(String[] args) {
//		System.out.println(WordsWindow.class.getResource("app.fxml"));
		FxmlWindow.running("通用文字识别（含生僻字版）",EnhancedGeneralWindow.class.getResource("EnhancedGeneral.fxml"));
	}

}
