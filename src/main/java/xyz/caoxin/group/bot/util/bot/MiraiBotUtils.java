package xyz.caoxin.group.bot.util.bot;

import net.mamoe.mirai.Bot;
import net.mamoe.mirai.BotFactory;
import net.mamoe.mirai.auth.BotAuthorization;
import net.mamoe.mirai.contact.Group;
import net.mamoe.mirai.message.data.MessageChain;
import net.mamoe.mirai.utils.BotConfiguration;
import xyz.caoxin.group.bot.BotApplication;
import xyz.caoxin.group.bot.data.WireRepo;

import java.io.File;

/**
 * @author 曹鑫
 */
public class MiraiBotUtils {
    private static final String BotWorkingDir = WireRepo.configLoader.getBotWorkingDir();

    /**
     * 机器人登录
     *
     * @param botId     qq账号
     * @param botPasswd qq密码
     * @return 已登录的机器人实例
     */
    public static Bot doLogin(String botId, String botPasswd) {
        Bot bot = BotFactory.INSTANCE.newBot(Long.parseLong(botId), BotAuthorization.byQRCode(), new BotConfiguration() {{
            setWorkingDir(new File(BotWorkingDir));
            fileBasedDeviceInfo(BotWorkingDir + "/device.json");
            redirectBotLogToDirectory(new File(BotWorkingDir + "/logs"));
            setBotLoggerSupplier(bot -> BotApplication.miraiLogger);
            noNetworkLog();
            setProtocol(MiraiProtocol.ANDROID_WATCH);
        }});
        bot.login();
        return bot;
    }

    /**
     * 向群组发送消息
     *
     * @param group    群组
     * @param msgChain 消息链
     */
    public static void sendMessage(Group group, MessageChain msgChain) {
        group.sendMessage(msgChain);
    }
}
