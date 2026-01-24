package com.cdlicn.message;

import lombok.*;

@EqualsAndHashCode(callSuper = true)
@Data
@ToString(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
public class LoginRequestMessage extends Message {
    private String username;
    private String password;

    /**
     * 获取消息列
     *
     * @return {@link Integer}
     */
    @Override
    public int getMessageType() {
        return LOGIN_REQUEST_MESSAGE;
    }

}
