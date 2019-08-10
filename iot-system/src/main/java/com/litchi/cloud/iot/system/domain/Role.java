package com.litchi.cloud.iot.system.domain;

import com.baomidou.mybatisplus.extension.activerecord.Model;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author wjhf
 * @since 2019-08-10
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class Role extends Model<Role> {

    private static final long serialVersionUID=1L;

    private Integer id;

    private String name;

    private String remark;

    private Integer seq;

    private Integer pid;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
