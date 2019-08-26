package com.robin.demo.slideVaild.util;

import com.google.common.collect.Lists;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URL;
import java.util.List;
import java.util.Objects;

/**
 * 文件流辅助类
 *
 * @author zoubin
 * @since 2013-09-13
 */
public class FileUtil {

    private static Logger logger = LoggerFactory.getLogger(FileUtil.class);


    public static boolean exist(String path) {
        return new File(path).exists();
    }

    public static File readFile(String path) {

        File imgFile = null;
        try {

            InputStream resource = FileUtil.class.getClassLoader().getResourceAsStream(path);
            imgFile = new File(path);
            FileUtils.copyInputStreamToFile(resource, imgFile);
        }catch (Exception e) {

            e.printStackTrace();
        }
        return imgFile;
    }

}