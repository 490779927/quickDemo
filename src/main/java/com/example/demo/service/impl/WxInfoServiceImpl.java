package com.example.demo.service.impl;

import com.example.demo.model.domain.WxInfo;
import com.example.demo.mapper.WxInfoMapper;
import com.example.demo.service.IWxInfoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author yangjin
 * @since 2019-11-27
 */
@Service
public class WxInfoServiceImpl extends ServiceImpl<WxInfoMapper, WxInfo> implements IWxInfoService {
}
