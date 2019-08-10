package com.litchi.cloud.iot.authentication.domain;

import com.baomidou.mybatisplus.extension.activerecord.Model;
import java.util.Date;
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
public class Organ extends Model<Organ> {

    private static final long serialVersionUID=1L;

    private Integer id;

    /**
     * æœºæž„åç§°
     */
    private String name;

    /**
     * æž„æœºç¼–ç 
     */
    private String organCode;

    /**
     * å»ºåˆ›æ—¶é—´
     */
    private Date createTime;

    /**
     * æ–°æ›´æ—¶é—´
     */
    private Date updateTime;

    /**
     * æž„æœºpath
     */
    private String path;

    /**
     * çˆ¶æœºæž„id
     */
    private Integer parentId;

    /**
     * åˆ›å»ºç”¨æˆ·
     */
    private Integer createUserId;

    /**
     * æž„æœºç±»åž‹
     */
    private Integer organType;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
