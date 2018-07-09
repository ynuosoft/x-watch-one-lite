package com.xwatcher.plugin.actions.webhook;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * Created by meng li on 2017/3/2.
 */
@Scope("prototype")
@Qualifier("httpAction")
@Component
public class WebHookAction {
}
