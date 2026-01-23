package com.cdlicn.message;

import com.cdlicn.message.Message;

public class PingMessage extends Message {
    @Override
    public int getMessageType() {
        return PING_MESSAGE;
    }
}
