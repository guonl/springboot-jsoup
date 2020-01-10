package com.guonl.service;

import com.alibaba.fastjson.JSONObject;
import com.guonl.jsoup.WechatSpider;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;
import javax.annotation.PostConstruct;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by guonl
 * Date: 2019-11-23 13:42
 * Description:
 */
@Slf4j
@Service
public class WechatSpiderService implements WechatSpider {

    private List<String> paramList = new ArrayList<>();

    @PostConstruct
    private void initParam(){
//  var round_head_img = "http://mmbiz.qpic.cn/mmbiz_png/wc7YNPm3YxWfwr9cMunibQ1xib6G8NGHwKpMTicjK8Ax6GAoerOE20Z5lsLOufRlnUQbGUoibfZMHrEbvvUUmQMvcQ/0?wx_fmt=png";
//      var hd_head_img = "http://wx.qlogo.cn/mmhead/Q3auHgzwzM46WJlQ8GYRWPhThl25rSKJEYBm408fnEkYS9DUkiaSxGg/0"||"";
//      var ori_head_img_url = "http://wx.qlogo.cn/mmhead/Q3auHgzwzM46WJlQ8GYRWPhThl25rSKJEYBm408fnEkYS9DUkiaSxGg/132";
//      var msg_title = "Spot和Atlas除了炫技还能做什么？波士顿动力CEO深度解答“网红机器人”的一切";
//      var msg_desc = "波士顿动力“网红机器人”将会被谁领走？那些病毒视频又将何去何从？";
//      var msg_cdn_url = "http://mmbiz.qpic.cn/mmbiz_jpg/wc7YNPm3YxWx4LA9abVF0siaosLsBMr7AapUJKt8icuX76z5iaFBuRaYcd70Pgnh3icZ0JqOX0XgfZKv8wK05oGEeQ/0?wx_fmt=jpeg";
//      var nickname = "大数据文摘";
//      var ct = "1574484768";
        paramList.add("var round_head_img");
        paramList.add("var hd_head_img");
        paramList.add("var ori_head_img_url");
        paramList.add("var msg_title");//标题
        paramList.add("var msg_desc");//内容描述
        paramList.add("var msg_cdn_url");//封面图片
        paramList.add("var nickname");//作者名称
        paramList.add("var ct");//创建时间
    }

    @Override
    public JSONObject getWXArticleInfo(String url) {
        JSONObject json = new JSONObject();
        Document doc = null;
        try {
            doc = Jsoup.connect(url).get();
            log.info(doc.title());
            Elements elScripts = doc.getElementsByTag("script");

            for (Element element : elScripts) {
                String targetString = element.childNodes().get(0).outerHtml().replace("\n","").trim();
                if(targetString.startsWith("var new_appmsg")){
                    List<String> list = Arrays.asList(targetString.split(";"));
                    List<String> collect = list.stream().filter(x -> paramList.contains(x.split("=")[0].trim())).collect(Collectors.toList());
                    collect.forEach(x->{
                        String[] split = x.split("=");
                        json.put(split[0].trim().replace("var ",""),split[1].replace("\"","").replace("|","").trim());
                    });
                }
            }
            json.put("url",url);

        } catch (IOException e) {
            e.printStackTrace();
        }

        return json;
    }

    @Override
    public JSONObject getHotel() {
        String url = "https://hotel.baidu.com/h5/mapdetail.html?uid=84197873ce93d112f1193702&start_time=2019-11-29&end_time=2019-11-30&src_from=pcmap_detail";
//        Document doc = Jsoup.connect(url).get();
        return null;
    }

    public static void main(String[] args) throws IOException {
        String url = "https://hotel.baidu.com/h5/mapdetail.html?uid=84197873ce93d112f1193702&start_time=2019-11-29&end_time=2019-11-30&src_from=pcmap_detail";
        Document doc = Jsoup.connect(url).get();
        System.out.println(doc);
        Elements elements = doc.getElementsByClass("room-name");
        for (Element element : elements) {
            System.out.println(element);
        }

//        Element elementById = doc.getElementById("hotel-price-main");
//        Elements elements = elementById.getElementsByClass("hotel-price-item");
//        //div[contains(@class,'room-name')]/text()
//        for (Element element : elements) {
//            Elements roomName = element.getElementsByAttribute("room-name");
//            Elements img = element.getElementsByTag("img");
//            System.out.println(roomName.outerHtml());
//            System.out.println(img);
//        }
    }

}
