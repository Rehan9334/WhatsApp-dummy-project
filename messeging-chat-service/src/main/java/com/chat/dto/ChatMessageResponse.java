package com.chat.dto;

import java.time.Instant;

public class ChatMessageResponse {

	private String messageId;
    private String fromUser;
    private String toUser;
    private String content;
    private Instant sentAt;
    private String status;

    public ChatMessageResponse() {
    }

	public ChatMessageResponse(String messageId, String fromUser, String toUser, String content, Instant sentAt,
			String status) {
		super();
		this.messageId = messageId;
		this.fromUser = fromUser;
		this.toUser = toUser;
		this.content = content;
		this.sentAt = sentAt;
		this.status = status;
	}

	public String getMessageId() {
		return messageId;
	}

	public void setMessageId(String messageId) {
		this.messageId = messageId;
	}

	public String getFromUser() {
		return fromUser;
	}

	public void setFromUser(String fromUser) {
		this.fromUser = fromUser;
	}

	public String getToUser() {
		return toUser;
	}

	public void setToUser(String toUser) {
		this.toUser = toUser;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Instant getSentAt() {
		return sentAt;
	}

	public void setSentAt(Instant sentAt) {
		this.sentAt = sentAt;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	
    
	
}
