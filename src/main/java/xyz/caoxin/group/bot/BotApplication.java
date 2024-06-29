package xyz.caoxin.group.bot;

import lombok.extern.slf4j.Slf4j;
import net.mamoe.mirai.Bot;
import net.mamoe.mirai.Mirai;
import net.mamoe.mirai.utils.LoggerAdapters;
import net.mamoe.mirai.utils.MiraiLogger;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import xyz.caoxin.group.bot.constants.InfoConstants;
import xyz.caoxin.group.bot.data.WireRepo;
import xyz.caoxin.group.bot.util.bot.MiraiBotUtils;


@SpringBootApplication
@Slf4j
public class BotApplication {
    public static Bot miraiBot;
    public static MiraiLogger miraiLogger;

    public static void main(String[] args) {
        SpringApplication.run(BotApplication.class, args);
        log.info(InfoConstants.APP_START_SUCCESS);

        // Adapt MiraiLogger to Slf4j
        Logger logger = LoggerFactory.getLogger(Mirai.class);
        miraiLogger = LoggerAdapters.asMiraiLogger(logger);

        // Start Mirai Bot
        miraiBot = MiraiBotUtils.doLogin(WireRepo.configLoader.getQqId(), WireRepo.configLoader.getQqPassWord());
        log.info(InfoConstants.BOT_START_SUCCESS, WireRepo.configLoader.getQqId());
        miraiBot.getEventChannel().registerListenerHost(new MiraiListener());

    }
}
