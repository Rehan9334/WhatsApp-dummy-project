package com.chat.dto;

public class TypingEvent {

	private String fromUser;
    private String toUser;
    private boolean typing;

    public TypingEvent() {
    }

    public TypingEvent(String fromUser, String toUser, boolean typing) {
        this.fromUser = fromUser;
        this.toUser = toUser;
        this.typing = typing;
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

    public boolean isTyping() {
        return typing;
    }

    public void setTyping(boolean typing) {
        this.typing = typing;
    }
	
}
