package com.example.demo.base.dto;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.Date;

/**
 * 实体继承类
 *
 * @author ：yangjin.
 * @Date ：Created in 21:17 2019/11/27
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class BaseEntity extends Model implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键id")
    @TableField("id")
    private Long id;

    @ApiModelProperty(value = "版本号", hidden = true)
    @TableField("version")
    private String version;

    @ApiModelProperty(value = "创建人", hidden = true)
    @TableField("creator")
    private String creator;

    @ApiModelProperty(value = "创建人id", hidden = true)
    @TableField("creator_id")
    private Long creatorId;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @ApiModelProperty(value = "创建时间", hidden = true)
    @TableField("creator_time")
    private Date creatorTime;

    @ApiModelProperty(value = "最近操作人", hidden = true)
    @TableField("last_operator")
    private String lastOperator;

    @ApiModelProperty(value = "最近操作人id", hidden = true)
    @TableField("last_operator_id")
    private Long lastOperatorId;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @ApiModelProperty(value = "修改时间", hidden = true)
    @TableField("update_time")
    private Date updateTime;

    public static final String ID = "id";

    public static final String VERSION = "version";

    public static final String CREATOR = "creator";

    public static final String CREATOR_ID = "creator_id";

    public static final String CREATOR_TIME = "creator_time";

    public static final String LAST_OPERATOR = "last_operator";

    public static final String LAST_OPERATOR_ID = "last_operator_id";

    public static final String UPDATE_TIME = "update_time";

}
