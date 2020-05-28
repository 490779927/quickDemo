package com.example.demo.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.example.demo.model.domain.UserInfo;
import com.example.demo.mapper.UserInfoMapper;
import com.example.demo.service.IUserInfoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author yangjin
 * @since 2020-03-14
 */
@Service
public class UserInfoServiceImpl extends ServiceImpl<UserInfoMapper, UserInfo> implements IUserInfoService {


    public static void main(String[] args) {
        UserInfo userInfo = new UserInfo();
        userInfo.setAge(10);
        userInfo.setUserName("asd");
        String s = JSON.toJSONString(userInfo);
        System.out.println(s);


        JSONObject jsonObject = new JSONObject();
        jsonObject.put("age",1.2f);
        jsonObject.put("userName","asd");
        UserInfo userInfo1 = jsonObject.toJavaObject(UserInfo.class);
        System.out.println(userInfo1);
    }
}
