package com.cdlicn.message;


import com.cdlicn.message.Message;

public class PongMessage extends Message {
    @Override
    public int getMessageType() {
        return PONG_MESSAGE;
    }
}
