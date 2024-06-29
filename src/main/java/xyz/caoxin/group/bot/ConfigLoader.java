package xyz.caoxin.group.bot;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author 曹鑫
 */
@Data
@Component
@ConfigurationProperties(prefix = "bot")
public class ConfigLoader {

    /**
     * 告警邮箱SMTP账户名(example: 114514@qq.com)
     */
    private String mailUserName;

    /**
     * 告警邮箱SMTP密码
     */
    private String mailPassword;

    /**
     * 告警邮箱SMTP地址(example: smtp.qq.com:465)
     */
    private String mailHost;

    /**
     * 机器人QQ账号
     */
    private String qqId;
    /**
     * 机器人QQ密码
     */
    private String qqPassWord;

    /**
     * 机器人工作目录
     */
    private String botWorkingDir;

}
