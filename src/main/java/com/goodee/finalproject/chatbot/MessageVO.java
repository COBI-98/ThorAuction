package com.goodee.finalproject.chatbot;

import lombok.Data;

@Data
public class MessageVO {
	private String text;
	private PhotoVO photo;
	private MessageButtonVO message_button;
}
