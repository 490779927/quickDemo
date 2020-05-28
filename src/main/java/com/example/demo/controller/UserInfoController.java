package com.example.demo.controller;


import com.example.demo.base.wrapper.Wrapper;
import com.example.demo.model.domain.UserInfo;
import com.example.demo.service.IUserInfoService;
import com.example.demo.service.impl.UserInfoServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;
import com.example.demo.base.interceptor.BaseController;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author yangjin
 * @since 2020-03-14
 */
@RestController
@RequestMapping("/user-info")
public class UserInfoController extends BaseController {

    @Autowired
    private IUserInfoService iUserInfoService;

    @GetMapping("/test")
    public Wrapper test(){
        UserInfo byId = iUserInfoService.getById(1);
        return Wrapper.success(byId);
    }
}
