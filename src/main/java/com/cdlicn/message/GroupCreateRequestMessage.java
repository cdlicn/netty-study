package com.cdlicn.message;

import lombok.*;

import java.util.Set;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
public class GroupCreateRequestMessage extends Message {
    private String groupName;
    private Set<String> members;

    @Override
    public int getMessageType() {
        return GROUP_CREATE_REQUEST_MESSAGE;
    }
}
