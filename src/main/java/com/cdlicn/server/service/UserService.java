package com.cdlicn.server.service;

/**
 * 用户管理接口
 */
public interface UserService {

    /**
     * 登录
     *
     * @param username {@link  String}
     * @param password {@link  String}
     * @return {@link  Boolean}
     */
    boolean login(String username, String password);
}
