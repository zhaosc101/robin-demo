package com.robin.demo.slideVaild.controller;

import com.alibaba.fastjson.JSONObject;
import com.robin.demo.slideVaild.util.CaptchaHelper;
import com.robin.demo.slideVaild.util.FileUtil;
import com.robin.demo.slideVaild.util.PictureFactory;
import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.nio.file.Files;
import java.util.Map;

/**
 * @author : IceSeed
 * @version : v0.0.1
 * @since : 2018/11/1
 */
@Controller
public class validationController {

    // 滑块边长
    private final Double L = 62D;
    // 图片宽度
    private final Double W = 310D;
    // 图片高度
    private final Double H = 155D;
    //误差值
    private final Double OFFSET = 5D;
    //缓存前缀
    private final String slidingCode = "slidingCode:";

    @RequestMapping(value = "/test", method = RequestMethod.GET)
    public String test(HttpServletRequest request, HttpServletResponse response) {
        return "slidingValidation";
    }


    /**
     * 获取滑动验证码信息
     *
     *
     * @return 视图
     */
    @ResponseBody
    @RequestMapping(value = "/getSliding", method = RequestMethod.POST)
    public String getSliding(HttpServletRequest request, HttpServletResponse response) {
        JSONObject json = new JSONObject();
        Long x = getRandomNumberByRange(L+10,  W - L);
        Long y = getRandomNumberByRange(0D, H - L);
        Long num = getRandomNumberByRange(1D, 28D);

        File imgFile = FileUtil.readFile("static/images/" + num + ".png");


        if(!imgFile.exists()) {
            throw new IllegalArgumentException("图片不存在！！");
        }

        CaptchaHelper.CaptchaValue component = CaptchaHelper.captSelector(imgFile, x.intValue(), y.intValue(), L.intValue());
        component.setyPoint(y.toString());
        json.put("component",component);

        return json.toString();
    }


    /**
     * 获取滑动验证码信息
     *
     * @return 视图
     */
    @ResponseBody
    @RequestMapping(value = "/validateSliding", method = RequestMethod.POST)
    public Boolean validateSliding(Double move,String sessionId) {

        return CaptchaHelper.checkValue(sessionId,6D,move);
    }

    /**
     * 随机获取区间值
     * @param start
     * @param end
     * @return
     */
    public Long getRandomNumberByRange(Double start, Double end) {
        return Math.round(Math.random() * (end - start) + start);

    }
}
