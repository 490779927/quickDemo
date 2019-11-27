package com.example.demo.model.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableId;
import com.example.demo.base.dto.BaseEntity;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author yangjin
 * @since 2019-11-27
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@ApiModel(value="WxInfo对象", description="")
public class WxInfo extends BaseEntity<WxInfo> {

    private static final long serialVersionUID = 1L;

    @TableField("phone")
    private String phone;

    @TableField("wx_open_id")
    private String wxOpenId;


    public static final String PHONE = "phone";

    public static final String WX_OPEN_ID = "wx_open_id";

    @Override
    protected Serializable pkVal() {
        return null;
    }

}
