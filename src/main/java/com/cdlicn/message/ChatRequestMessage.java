package com.cdlicn.message;
import lombok.*;

@EqualsAndHashCode(callSuper = true)
@Data
@ToString(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
public class ChatRequestMessage extends Message {
    private String content;
    private String to;
    private String from;

    @Override
    public int getMessageType() {
        return CHAT_REQUEST_MESSAGE;
    }
}
