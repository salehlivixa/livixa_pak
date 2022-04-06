package com.livixa.apacam.client.widget;

public class FontUtility {

	public static String getApplicationFont(int value) {
		switch (value) {
		case 1:
			return "fonts/Light.otf";
		case 2:
			return "fonts/Regular.otf";
		case 3:
			return "fonts/Medium.otf";
		case 4:
			return "fonts/Bold.otf";
		case 5:
			return "fonts/MYRIADPRO-REGULAR.OTF";
		default:
			return "fonts/Regular.otf";
		}

	}
}
