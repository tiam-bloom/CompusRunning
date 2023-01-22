package icu.running.pojo;

import lombok.Data;

/**
 * 订单表Demand
 */

@Data
public class Demand {
    /**
     * 贴子编号
     */
    private Integer did;
    /**
     * 发布人id
     */
    private Integer id;
    /**
     * 发布人昵称
     */
    private String name;
    /**
     * 贴子标题
     */
    private String title;
    /**
     * 贴子内容
     */
    private String content;
    /**
     * 悬赏金额
     */
    private Integer reward;
    /**
     * 状态码 0:未接单 ,1:已被接单
     */
    private Integer state;
    /**
     * 承接人id
     */
    private Integer ltd;
    /**
     * 用户信息注入
     */
    private UserInfo userInfo;
}
