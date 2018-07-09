package com.xwatcher.plugin.compents.mustache;

import com.github.mustachejava.DefaultMustacheFactory;
import com.github.mustachejava.Mustache;
import com.github.mustachejava.MustacheFactory;

import java.io.*;
import java.util.Map;

/**
 * Created by meng li on 2017/3/8.
 * 调用mustache工具包执行代码
 */
public class MustacheHelper {
    static {

    }

    private MustacheHelper() {

    }
    /**
     * @param template
     * @param map      必须是单个Class/或者Map结构的
     * @return
     * @throws IOException
     */
    public static String getTemplateContent(String template, Map<String, Object> map) throws IOException {
        Writer writer = new StringWriter();
        MustacheFactory mf = new DefaultMustacheFactory();
        Mustache mustache = mf.compile(new StringReader(template), "Mustache01");
        mustache.execute(writer, map);

        writer.flush();
        writer.close();

        return writer.toString();
    }
    /**
     * @param template
     * @param map      必须是单个Class/或者Map结构的
     * @return
     * @throws IOException
     */
    public static String getTemplateContent(String template, Object object) throws IOException {
        Writer writer = new StringWriter();
        MustacheFactory mf = new DefaultMustacheFactory();
        Mustache mustache = mf.compile(new StringReader(template), "Mustache01");
        mustache.execute(writer, object);

        writer.flush();
        writer.close();

        return writer.toString();
    }
}
