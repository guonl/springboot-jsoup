package com.guonl.controller;

import com.alibaba.fastjson.JSONObject;
import com.guonl.jsoup.WechatSpider;
import com.guonl.vo.FrontResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Created by guonl
 * Date: 2019-11-23 13:43
 * Description:
 */
@Controller
@RequestMapping("/wechat")
public class WechatSpiderController {

    @Autowired
    private WechatSpider wechatSpider;

    @GetMapping("/detail")
    @ResponseBody
    public FrontResult getWXArticleInfo(String url){
//        JSONObject json = new JSONObject();
        JSONObject json = wechatSpider.getWXArticleInfo(url);
        return FrontResult.success(json);
    }

    @GetMapping("/hotel")
    @ResponseBody
    public ResponseEntity<JSONObject> getHotel(){
        JSONObject json = wechatSpider.getHotel();
        return new ResponseEntity<>(json, HttpStatus.OK);
    }



}
