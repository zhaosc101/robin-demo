/**
 * All rights Reserved, Designed By Suixingpay.
 *
 * @author: zhao_sc[zhao_sc@suixingpay.com]
 * @date: 2019/8/11 11:32
 * @Copyright ©2019 Suixingpay. All rights reserved.
 */
package com.robin.demo.slideVaild.util;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang3.StringUtils;
import sun.misc.BASE64Encoder;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.nio.file.Files;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;

/**
 * 生成驗證碼服務
 * 1 獲取 圖片地址
 * 2 剪切图片
 * 3
 * @author: zhao_sc[zhao_sc@suixingpay.com]
 * @date: 2019/8/11 11:32
 * @version: V1.0
 * @review: zhao_sc[zhao_sc@suixingpay.com]/2019/8/11 11:32
 */
public class CaptchaHelper {


    // 白底图片路径
    private static final String whiteImg = "static/images/mh.png";

    private static Map<String,String> map = new HashMap<>();
    private static Map<String,Double> redis = new HashMap<>();

    public static String getCaptchaImageBaseStr(String path) {

        if(map.containsKey(path)) {

            return map.get(path);
        }

        File file = new File(path);
        if(file.exists()) {
            throw new IllegalArgumentException("图片不存在！！");

        }
        String base64 = null ;
        try {

            byte [] img = Files.readAllBytes(file.toPath());
            BASE64Encoder encoder = new BASE64Encoder();
            base64 = encoder.encode(img);
            map.put(path,base64);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return base64;
    }


    public static InputStream getStreamByBase64Str(String base64Str) {

        if (StringUtils.isEmpty(base64Str)) {
            return null;
        }

        byte[] bytes = Base64.decodeBase64(base64Str);
        if (Objects.isNull(bytes) || bytes.length == 0) {
            return null;
        }
        return new ByteArrayInputStream(bytes);
    }


    /**
     * 获取字节码滑块组件
     *
     * @param srcImg：原图路径
     * @param x:          截取位置X坐标
     * @param y:          截取位置Y坐标
     * @param l：滑块边长
     */
    public static CaptchaValue captSelector(File srcImg, int x, int y, Integer l) {

        CaptchaValue captchaValue = new CaptchaValue();
        captchaValue.setSlider(cutImageByBase64(srcImg,new Rectangle(x, y, l, l)));
        captchaValue.setBackground(jointImageByBase64(srcImg,x,y,l));

        captchaValue.setSessionId(UUID.randomUUID().toString());
        redis.put(captchaValue.getSessionId(),Double.valueOf(x));
        return captchaValue;
    }

    public static boolean checkValue(String sessionId,Double offset,Double moveLen) {

        if(offset == null) {offset = 5D;}

        Double coordinates = redis.get(sessionId);

        if(coordinates> (moveLen - offset) && coordinates < (moveLen + offset)) {
            return true;
        }else {
            return false;
        }


    }
    /**
     *  剪切图片生成滑动图
     * @param srcImg
     * @param rect
     * @return
     */
    public static String cutImageByBase64(File srcImg, Rectangle rect) {

        BufferedImage image =  PictureFactory.getImage(srcImg, rect);
        // base64图片
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        try {
            ImageIO.write(image, "PNG", outputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
        BASE64Encoder encoder = new BASE64Encoder();
        return encoder.encode(outputStream.toByteArray());
    }

    /**
     * 生成背景图片
     * @param srcImg 图片文件
     * @param x 截取位置X坐标
     * @param y 截取位置Y坐标
     * @param l 滑块变长
     * @return
     */
    public static String jointImageByBase64(File srcImg,int x, int y, Integer l){
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        try {
            BufferedImage image1 = ImageIO.read(srcImg);
            BufferedImage image2 =  PictureFactory.getImage(FileUtil.readFile(whiteImg), new Rectangle(0, 0, l, l));
            // base64图片
            ImageIO.write( PictureFactory.jointImage(image1, image2, x, y, l), "PNG", outputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
        BASE64Encoder encoder = new BASE64Encoder();

        return encoder.encode(outputStream.toByteArray());
    }


    public static class CaptchaValue {

        private String slider;

        private String background;

        private String yPoint;

        private String sessionId;

        public String getSessionId() {
            return sessionId;
        }

        public void setSessionId(String sessionId) {
            this.sessionId = sessionId;
        }

        public String getyPoint() {
            return yPoint;
        }

        public void setyPoint(String yPoint) {
            this.yPoint = yPoint;
        }

        public String getSlider() {
            return slider;
        }

        public void setSlider(String slider) {
            this.slider = slider;
        }

        public String getBackground() {
            return background;
        }

        public void setBackground(String background) {
            this.background = background;
        }
    }
}
