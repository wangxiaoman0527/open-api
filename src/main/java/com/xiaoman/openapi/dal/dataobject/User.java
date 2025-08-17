package com.xiaoman.openapi.dal.dataobject;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.xiaoman.openapi.model.enums.UserRoleEnum;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@TableName(value = "user")
@Accessors(chain = true)
public class User {
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 用户名
     */
    private String username;

    /**
     * 密码
     */
    private String password;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 角色
     */
    private UserRoleEnum role = UserRoleEnum.DEVELOPER;

    /**
     * accessKey
     */
    private String accessKey;
    /**
     * secretKey
     */
    private String secretKey;

    /**
     * 激活状态
     */
    private boolean active = true;

}