package com.chat.dto;

public class ChatMessageRequest {

	private String fromUser;
    private String toUser;
    private String content;

    public ChatMessageRequest() {
    }

	public ChatMessageRequest(String fromUser, String toUser, String content) {
		super();
		this.fromUser = fromUser;
		this.toUser = toUser;
		this.content = content;
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
    
    
	
}
