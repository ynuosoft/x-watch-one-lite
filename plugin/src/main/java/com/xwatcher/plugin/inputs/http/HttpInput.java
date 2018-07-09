package com.xwatcher.plugin.inputs.http;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import com.fasterxml.jackson.databind.node.NullNode;
import com.xwatcher.core.inputs.InputExecutor;

import com.xwatcher.core.watcher.ExecutionContext;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.*;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Map;

/**
 * Created by meng li on 2017/2/27.
 */
@Scope("prototype")
@Qualifier("httpInput")
@Component
public class HttpInput extends InputExecutor<HttpInputEntity> {
    private Logger logger = LoggerFactory.getLogger(HttpInput.class);

    @Override
    protected Class<HttpInputEntity> convert2Entity() {
        return HttpInputEntity.class;
    }
    @Override
    protected JsonNode doInput(ExecutionContext ctx) {
        JsonNode jsonNode = NullNode.instance;
        CloseableHttpClient httpclient = HttpClients.createDefault();
        HttpRequestBase request = getRequest();
        if (this.getEntity().getHttpHeaders() != null && this.getEntity().getHttpHeaders().size() > 0) {
            for (Map.Entry<String, String> entry : this.getEntity().getHttpHeaders().entrySet()) {
                request.addHeader(entry.getKey(), entry.getValue());
            }
        }
        RequestConfig requestConfig = RequestConfig.custom()
                .setConnectionRequestTimeout(this.getEntity().getRequestTimeout())
                .setMaxRedirects(this.getEntity().getMaxRetries())
                .build();
        request.setConfig(requestConfig);
        try {
            CloseableHttpResponse response = httpclient.execute(request);
            if (response.getStatusLine().getStatusCode() == 200) {
                jsonNode = objectMapper.readTree(response.getEntity().getContent());
            }
            response.close();
        } catch (IOException e) {
            logger.error("http input error ", e);
        } finally {
            request.releaseConnection();
        }
        return jsonNode;
    }

    HttpRequestBase getRequest() {
        String httpMethod = this.getEntity().getHttpMethod();
        if (httpMethod.equals(null) || httpMethod.equals("")) {
            return new HttpGet(this.getEntity().getRequestUrl());
        } else if (httpMethod.equals("POST")) {
            HttpPost httpPost = new HttpPost(this.getEntity().getRequestUrl());
            prepareBody(httpPost);
            return httpPost;
        } else if (httpMethod.equals("PUT")) {
            HttpPut httpPut = new HttpPut(this.getEntity().getRequestUrl());
            prepareBody(httpPut);
            return httpPut;
        } else if (httpMethod.equals("DELETE")) {
            return new HttpDelete(this.getEntity().getRequestUrl());
        } else if (httpMethod.equals("HEAD")) {
            return new HttpHead(this.getEntity().getRequestUrl());
        } else {
            return new HttpGet(this.getEntity().getRequestUrl());
        }
    }
    /**
     * 支持json与from表单
     *
     * @return
     */
    void prepareBody(HttpEntityEnclosingRequestBase http) {
        if (this.getEntity().getRequestParams() != null &&
                this.getEntity().getRequestParams().entrySet().size() > 0) {
            try {
                //json方式
                StringEntity stringEntity=new StringEntity(objectMapper.writeValueAsString(this.getEntity().getRequestParams()),
                        ContentType.APPLICATION_JSON);
                //form方式
//                List<NameValuePair> formparams = new ArrayList<NameValuePair>();
//                formparams.add(new BasicNameValuePair("param1", "value1"));
//                formparams.add(new BasicNameValuePair("param2", "value2"));
//                UrlEncodedFormEntity entity = new UrlEncodedFormEntity(formparams, Consts.UTF_8);
//                HttpPost httppost = new HttpPost("http://localhost/handler.do");

                http.setEntity(stringEntity);
            } catch (IOException e) {
                logger.error("prepare body put error", e);
            }
        }
    }
}
