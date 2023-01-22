package icu.running.pojo;

import lombok.Data;

/**
 * 用户表user
 */
@Data
public class User {
    /**
     * 用户唯一id
     */
    private Integer id;
    /**
     * 用户名
     */
    private String username;
    /**
     * 密码
     */
    private String password;
    /**
     * 用户信息注入
     */
    private UserInfo userInfo;

}
