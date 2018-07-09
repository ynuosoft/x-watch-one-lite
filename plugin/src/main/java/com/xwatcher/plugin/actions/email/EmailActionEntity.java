package com.xwatcher.plugin.actions.email;

        import com.xwatcher.core.actions.ActionEntity;

        import java.util.List;

/**
 * Created by meng li on 2017/2/16.
 */
public class EmailActionEntity extends ActionEntity {

    private String smtpHost;
    private int smtpPort;
    private String mailFrom;
    private String mailFromPwd;
    private String mailTo;
    private List<String> mailToCc;
    private String mailSubject;
    private String mailBody;

    public String getSmtpHost() {
        return smtpHost;
    }

    public void setSmtpHost(String smtpHost) {
        this.smtpHost = smtpHost;
    }

    public int getSmtpPort() {
        return smtpPort;
    }

    public void setSmtpPort(int smtpPort) {
        this.smtpPort = smtpPort;
    }

    public String getMailFrom() {
        return mailFrom;
    }

    public void setMailFrom(String mailFrom) {
        this.mailFrom = mailFrom;
    }

    public String getMailFromPwd() {
        return mailFromPwd;
    }

    public void setMailFromPwd(String mailFromPwd) {
        this.mailFromPwd = mailFromPwd;
    }

    public String getMailTo() {
        return mailTo;
    }

    public void setMailTo(String mailTo) {
        this.mailTo = mailTo;
    }

    public List<String> getMailToCc() {
        return mailToCc;
    }

    public void setMailToCc(List<String> mailToCc) {
        this.mailToCc = mailToCc;
    }

    public String getMailSubject() {
        return mailSubject;
    }

    public void setMailSubject(String mailSubject) {
        this.mailSubject = mailSubject;
    }

    public String getMailBody() {
        return mailBody;
    }

    public void setMailBody(String mailBody) {
        this.mailBody = mailBody;
    }
}
