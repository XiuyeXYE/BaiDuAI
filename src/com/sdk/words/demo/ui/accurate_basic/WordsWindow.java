package com.sdk.words.demo.ui.accurate_basic;

import com.xiuye.window.FxmlWindow;

public class WordsWindow {

	public static void main(String[] args) {
//		System.out.println(WordsWindow.class.getResource("app.fxml"));
		FxmlWindow.running(WordsWindow.class.getResource("app.fxml"));
	}

}
