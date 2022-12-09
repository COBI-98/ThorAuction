package com.goodee.finalproject.chatbot;

import lombok.Data;

@Data
public class RequestMessageVO {
	private String user_key;
	private String type;
	private String content;
}
