package com.cozz.seata.entity;

import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;


/**
 * @author zt
 * @date 2024/3/10
 **/
@Data
public class Account extends Model<Account> {

    /**
     * id
     */
    private Long id;

    /**
     * 用户id
     */
    private Long userId;

    /**
     * 总额度
     */
    private Long total;

    /**
     * 已用余额
     */
    private Long used;

    /**
     * 剩余可用额度
     */
    private Long residue;
}

