package com.xwatcher.plugin.conditions.arraycompare;

import com.fasterxml.jackson.databind.JsonNode;
import com.xwatcher.core.conditions.ConditionExecutor;
import com.xwatcher.core.watcher.ExecutionContext;
import com.xwatcher.plugin.conditions.compare.CompareHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.Iterator;
import java.util.Map;

/**
 * Created by meng li on 2017/3/6.
 */
@Scope("prototype")
@Qualifier("arrayCompareCondtion")
@Component
public class ArrayCompareCondition extends ConditionExecutor<ArrayCompareConditionEntity> {
    private Logger logger = LoggerFactory.getLogger(ArrayCompareCondition.class);

    /**
     * @return
     */
    @Override
    protected Class<ArrayCompareConditionEntity> convert2Entity() {
        return ArrayCompareConditionEntity.class;
    }

    /**
     * @param ctx
     * @return
     */
    @Override
    public boolean doCondition(ExecutionContext ctx) {
        //some只要有一条数据满足即可，all要全部满足
        boolean is_quantifier_all = this.getEntity().getQuantifier().equals("all") ? true : false;
        try {
            if (is_quantifier_all) {
                return do_quantifier_all(ctx);
            } else {
                return do_quantifier_some(ctx);
            }
        } catch (Exception e) {
            logger.error("array_compare doCondition error", e);
            return false;
        }
    }
    /**
     * 执行all
     *
     * @param ctx
     * @return
     */
    boolean do_quantifier_all(ExecutionContext ctx) {
        boolean b0 = true;
        //循环1 条件-params字段
        for (Map.Entry<String, Map<String, Map<String, Object>>> entry1 : this.getEntity().getParams().entrySet()) {
            //获取上下文中的数据对象-数组
            JsonNode arrayDataJsonNode = CompareHelper.findJsonNode(entry1.getKey(), ctx.getCtx());
            //判断是否是数组对象
            if (!arrayDataJsonNode.isArray()) {
                throw new IllegalArgumentException(entry1.getKey() + " is not array node path");
            }
            //循环2 条件
            for (Map.Entry<String, Map<String, Object>> entry2 : entry1.getValue().entrySet()) {
                //循环3 数据
                Iterator<JsonNode> iterator = arrayDataJsonNode.iterator();
                while (iterator.hasNext()) {
                    JsonNode currentDataJsonNode = iterator.next();
                    Object currentObjectValue = CompareHelper.getCurrentObjectValue(entry2.getKey(), currentDataJsonNode);
                    //循环4 条件
                    boolean b1 = true;
                    for (Map.Entry<String, Object> entry3 : entry2.getValue().entrySet()) {
                        b1 = b1 && CompareHelper.doCompare(currentObjectValue, entry3);
                    }
                    if (b1 == false) {
                        b0 = false;
                        break;
                    }
                }
                if (b0 == false) {
                    break;
                }
            }
        }
        return b0;
    }

    /**
     * 执行some
     *
     * @param ctx
     * @return
     */
    boolean do_quantifier_some(ExecutionContext ctx) {
        boolean b0 = false;
        //循环1 条件-params字段
        for (Map.Entry<String, Map<String, Map<String, Object>>> entry1 : this.getEntity().getParams().entrySet()) {
            //获取上下文中的数据对象-数组
            JsonNode arrayDataJsonNode = CompareHelper.findJsonNode(entry1.getKey(), ctx.getCtx());
            //判断是否是数组对象
            if (!arrayDataJsonNode.isArray()) {
                throw new IllegalArgumentException(entry1.getKey() + " is not array node path");
            }
            //循环2 条件
            for (Map.Entry<String, Map<String, Object>> entry2 : entry1.getValue().entrySet()) {
                //循环3 数据
                Iterator<JsonNode> iterator = arrayDataJsonNode.iterator();
                while (iterator.hasNext()) {
                    JsonNode currentDataJsonNode = iterator.next();
                    Object currentObjectValue = CompareHelper.getCurrentObjectValue(entry2.getKey(), currentDataJsonNode);
                    //循环4 条件
                    boolean b1 = true;
                    for (Map.Entry<String, Object> entry3 : entry2.getValue().entrySet()) {
                        b1 = b1 && CompareHelper.doCompare(currentObjectValue, entry3);
                    }
                    if (b1) {
                        b0 = true;
                        break;
                    }
                }
                if (b0) {
                    break;
                }
            }
            if (b0) {
                break;
            }
        }
        return b0;
    }
}
