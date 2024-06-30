package xyz.caoxin.group.bot.constants;

public final class InfoConstants {
    public static final String BOT_ERROR_MAIL_SUBJECT = "机器人报错";

    public static final String COMMAND_INVALID = "命令无效";

    private static final String MIRAI_PREFIX = "MiraiBot-";
    public static final String BOT_START_SUCCESS = MIRAI_PREFIX + "机器人:{}登录成功";
    public static final String BOT_RECEIVE_GROUP_MESSAGE = MIRAI_PREFIX + "收到群消息:{}";
    public static final String BOT_ERROR_MAIL_INFO = MIRAI_PREFIX + "在%s出现了一个错:\n%s\n%s";
    public static final String BOT_WORKING_DIR_CREATED = MIRAI_PREFIX + "机器人运行目录创建成功";
    public static final String BOT_WORKING_DIR_CREATE_FAILED = MIRAI_PREFIX + "机器人运行目录创建失败";

    private static final String SPRINGBOOT_PREFIX = "SpringBoot-";
    public static final String APP_START_SUCCESS = SPRINGBOOT_PREFIX + "启动成功";

    private InfoConstants() {

    }

}
