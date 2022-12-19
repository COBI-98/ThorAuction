package com.goodee.finalproject.chatbot;

import lombok.Data;

@Data
public class KeyboardVO {
	private String type;
	private String[] buttons;
	
	public KeyboardVO(String[] buttons) {
		this.type = "buttons";
		this.buttons = buttons;
	}

}
