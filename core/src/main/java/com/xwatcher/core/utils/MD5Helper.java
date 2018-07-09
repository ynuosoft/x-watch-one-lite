package com.xwatcher.core.utils;

import com.xwatcher.core.triggers.cron.CronTrigger;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by meng li on 2017/3/14.
 */
public class MD5Helper {

    private static Logger logger = LoggerFactory.getLogger(MD5Helper.class);

    private MD5Helper() {

    }
    //静态方法，便于作为工具类
    public static String Md5(String source) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(source.getBytes());
            byte b[] = md.digest();
            int i;
            StringBuffer buf = new StringBuffer("");
            for (int offset = 0; offset < b.length; offset++) {
                i = b[offset];
                if (i < 0) {
                    i += 256;
                }
                if (i < 16) {
                    buf.append("0");
                }
                buf.append(Integer.toHexString(i));
            }
            //32位加密
            return buf.toString();
            // 16位的加密
            //return buf.toString().substring(8, 24);
        } catch (NoSuchAlgorithmException e) {
            logger.error("md5 parser errro" ,e);
        }
        return String.valueOf(source.hashCode());
    }
}
