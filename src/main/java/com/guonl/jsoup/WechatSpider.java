package com.guonl.jsoup;

import com.alibaba.fastjson.JSONObject;

/**
 * Created by guonl
 * Date: 2019-11-23 13:40
 * Description:
 */
public interface WechatSpider {

    JSONObject getWXArticleInfo(String url);

    JSONObject getHotel();

}
