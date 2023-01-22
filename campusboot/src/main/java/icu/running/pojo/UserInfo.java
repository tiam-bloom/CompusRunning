package icu.running.pojo;

import lombok.Data;

/**
 * 用户信息表userInfo
 */

@Data
public class UserInfo {
    /**
     * 用户id
     */
    private Integer id;
    /**
     * 用户昵称
     */
    private String name;
    /**
     * 头像
     */
    private String bigHead;
    /**
     * 邮箱
     */
    private String email;
    /**
     * 电话
     */
    private String tel;
    /**
     * 地址
     */
    private String address;
    /**
     * 账户余额
     */
    private Integer balance;
}
