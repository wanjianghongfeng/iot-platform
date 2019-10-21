package com.litchi.cloud.iot.system.vo;

import lombok.Data;

/** 
 * 类说明
 * @author: wjhf-litchi
 * @date: 2019年10月21日 上午10:49:11
 * @vesion: 0.0.1
 */
@Data
public class UserPwdVO {

    private Integer id;

    // 原密码
    private String oldPwd;

    // 新密码
    private String newPwd;

}
