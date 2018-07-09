package com.xwatcher.plugin.conditions.compare;

import com.xwatcher.core.conditions.ConditionExecutor;
import com.xwatcher.core.watcher.ExecutionContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.Map;


/**
 * Created by meng li on 2017/3/6.
 * 单个值，比较
 * 可以多个条件并
 */
@Scope("prototype")
@Qualifier("compareCondition")
@Component
public class CompareCondition extends ConditionExecutor<CompareConditionEntity> {
    private Logger logger = LoggerFactory.getLogger(CompareCondition.class);
    @Override
    protected Class<CompareConditionEntity> convert2Entity() {
        return CompareConditionEntity.class;
    }
    /**
     * @param ctx
     */
    protected boolean doCondition(ExecutionContext ctx) {
        //or只要有一个条件数据满足即可，and要全部条件满足
        boolean is_quantifier_and = this.getEntity().getQuantifier().equals("and") ? true : false;
        try {
            if (is_quantifier_and) {
                return do_quantifier_and(ctx);
            } else {
                return do_quantifier_or(ctx);
            }
        } catch (Exception e) {
            logger.error("compare doCndition error", e);
            return false;
        }
    }
    /**
     * and条件判断
     *
     * @param ctx
     * @return
     */
    boolean do_quantifier_and(ExecutionContext ctx) {
        boolean b0 = true;
        //循环1 条件
        for (Map.Entry<String, Map<String, Object>> entry1 : this.getEntity().getParams().entrySet()) {
            //数据
            Object currentObjectValue = CompareHelper.getCurrentObjectValue(entry1.getKey(), ctx.getCtx());
            //循环2 条件
            boolean b1 = true;
            for (Map.Entry<String, Object> entry2 : entry1.getValue().entrySet()) {
                b1 = b1 && CompareHelper.doCompare(currentObjectValue, entry2);
            }
            if (!b1) {
                b0 = false;
                break;
            }
        }
        return b0;
    }
    /**
     * or条件判断
     *
     * @param ctx
     * @return
     */
    boolean do_quantifier_or(ExecutionContext ctx) {
        boolean b0 = false;
        //循环1，条件
        for (Map.Entry<String, Map<String, Object>> entry1 : this.getEntity().getParams().entrySet()) {
            //数据
            Object currentObjectValue = CompareHelper.getCurrentObjectValue(entry1.getKey(), ctx.getCtx());
            //循环2，条件
            boolean b1 = true;
            for (Map.Entry<String, Object> entry2 : entry1.getValue().entrySet()) {
                b1 = b1 && CompareHelper.doCompare(currentObjectValue, entry2);
            }
            if (b1) {
                b0 = true;
                break;
            }
        }
        return b0;
    }
}
