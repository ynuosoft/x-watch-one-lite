package com.xwatcher.plugin.conditions.compare;

import com.fasterxml.jackson.databind.JsonNode;

import java.util.Map;

/**
 * Created by meng li on 2017/3/17.
 */
public final class CompareHelper {
    private CompareHelper(){

    }
    /**
     * 等于
     */
    public static final String opt_eq = "eq";
    /**
     * 不等于
     */
    public static final String opt_not_eq = "not_eq";
    /**
     * 大于
     */
    public static final String opt_gt = "gt";
    /**
     * 大于等于
     */
    public static final String opt_gte = "gte";
    /**
     * 小于
     */
    public static final String opt_lt = "lt";
    /**
     * 小于等于
     */
    public static final String opt_lte = "lte";

    /**
     * @param currentObjectValue
     * @param entry
     * @return
     */
  public static  boolean doCompare(Object currentObjectValue, Map.Entry<String, Object> entry) {
        String opt_key_01 = entry.getKey();
        Object opt_value_01 = entry.getValue();
        //
        if (opt_eq.equals(opt_key_01)) {
            //值类型对比
            if (opt_value_01 instanceof Number) {
                return Integer.parseInt(currentObjectValue.toString()) == Integer.parseInt(opt_value_01.toString());
            } else {//字符串对比
                return currentObjectValue.equals(opt_value_01);
            }
        } else if (opt_not_eq.equals(opt_key_01)) {
            //值类型对比
            if (opt_value_01 instanceof Number) {
                return Integer.parseInt(currentObjectValue.toString()) != Integer.parseInt(opt_value_01.toString());
            } else {//字符串对比
                return !currentObjectValue.equals(opt_value_01);
            }
        } else if (opt_gt.equals(opt_key_01)) {
            //值类型对比
            return Integer.parseInt(currentObjectValue.toString()) > Integer.parseInt(opt_value_01.toString());
        } else if (opt_gte.equals(opt_key_01)) {
            //值类型对比
            return Integer.parseInt(currentObjectValue.toString()) >= Integer.parseInt(opt_value_01.toString());
        } else if (opt_lt.equals(opt_key_01)) {
            //值类型对比
            return Integer.parseInt(currentObjectValue.toString()) < Integer.parseInt(opt_value_01.toString());
        } else if (opt_lte.equals(opt_key_01)) {
            //值类型对比
            return Integer.parseInt(currentObjectValue.toString()) <= Integer.parseInt(opt_value_01.toString());
        } else {
        }
        return false;
    }
    /**
     * 从当前jsonNode中获取指定的值
     * @param currentPath
     * @param currentJsonNode
     * @return
     */
    public static Object getCurrentObjectValue(String currentPath, JsonNode currentJsonNode) {
        JsonNode idNode = findJsonNode(currentPath, currentJsonNode);
        if (idNode.isInt()) {
            return idNode.asInt();
        }
        return idNode.textValue();
    }
    /**
     * @param currentPath
     * @param currentJsonNode
     * @return
     */
   public static JsonNode findJsonNode(String currentPath, JsonNode currentJsonNode) {
        JsonNode idNode = null;
//        小数点需要转义符号，否则认为是正则表达式\\
        String[] propertyNameArr = currentPath.split("\\.");
        for (String propertyName : propertyNameArr) {
            if (idNode == null) {
                idNode = currentJsonNode.path(propertyName);
            } else {
                idNode = idNode.path(propertyName);
            }
        }
        if (idNode.isMissingNode()) {
            throw new IllegalArgumentException("could not find " + currentPath);
        }
        return idNode;
    }
}
