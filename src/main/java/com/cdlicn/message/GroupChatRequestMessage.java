package com.cdlicn.message;

import lombok.*;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString(callSuper = true)
public class GroupChatRequestMessage extends Message {
    private String content;
    private String groupName;
    private String from;

    @Override
    public int getMessageType() {
        return GROUP_CHAT_REQUEST_MESSAGE;
    }
}
