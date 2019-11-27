package com.example.demo.base.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * @author ：yangjin.
 * @Date ：Created in 20:38 2019/11/27
 */
@Data
public class WrapperBool implements Serializable {
    private static final long serialVersionUID = 1L;

    private Boolean flag;
}
