package com.xwatcher.plugin.actions.email;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.xwatcher.core.actions.ActionExecutor;
import com.xwatcher.core.watcher.ExecutionContext;
import com.xwatcher.plugin.compents.mustache.MustacheHelper;
import org.apache.commons.mail.SimpleEmail;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * Created by meng li on 2017/2/27.
 */
@Scope("prototype")
@Qualifier("emailAction")
@Component
public class EmailAction extends ActionExecutor<EmailActionEntity> {

    private Logger logger = LoggerFactory.getLogger(EmailAction.class);

    @Override
    protected Class<EmailActionEntity> convert2Entity() {
        return  EmailActionEntity.class;
    }
    @Override
    public JsonNode doAction(ExecutionContext executionContext) throws Exception {

        SimpleEmail simpleEmail = new SimpleEmail();

        simpleEmail.setHostName(this.getEntity().getSmtpHost());
        simpleEmail.setSmtpPort(this.getEntity().getSmtpPort());

        simpleEmail.setAuthentication(this.getEntity().getMailFrom(), this.getEntity().getMailFromPwd());
        simpleEmail.setSSLOnConnect(true);
        simpleEmail.setSslSmtpPort(String.valueOf(this.getEntity().getSmtpPort()));
        simpleEmail.setFrom(this.getEntity().getMailFrom(), "lemon li");
        //
        Map map = objectMapper.convertValue(executionContext.getCtx(), Map.class);
        String strSubject = MustacheHelper.getTemplateContent(this.getEntity().getMailSubject(), map);
        String strMsg = MustacheHelper.getTemplateContent(this.getEntity().getMailBody(), map);
        //采用mustache模板替换
        simpleEmail.setSubject(strSubject);
        simpleEmail.setMsg(strMsg);
        simpleEmail.addTo(this.getEntity().getMailTo(), "meng li");
        simpleEmail.send();


        return null;
    }

}


