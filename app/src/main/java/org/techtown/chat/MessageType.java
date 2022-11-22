package org.techtown.chat;

public enum MessageType {
    NONE(-1), LEFT_CONTENTS(0), CENTER_CONTENTS(1), RIGHT_CONTENTS(2);

    private int code;

    MessageType(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public static MessageType of(int code) {
        MessageType[] types = MessageType.values();
        for (MessageType type : types) {
            if (type.getCode() == code) {
                return type;
            }
        }
        return NONE;
    }
}
