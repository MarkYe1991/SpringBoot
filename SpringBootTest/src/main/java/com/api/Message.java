package com.api;

import lombok.Data;

@Data
public class Message {
	public Message(String content, String sender) {
		this.content = content;
		this.sender = sender;
	}
	String content;
	String sender;
}
