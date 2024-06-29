package xyz.caoxin.group.bot.command;

import com.google.common.util.concurrent.ThreadFactoryBuilder;
import lombok.extern.slf4j.Slf4j;
import net.mamoe.mirai.contact.Group;
import net.mamoe.mirai.message.data.MessageChain;
import net.mamoe.mirai.message.data.MessageChainBuilder;
import net.mamoe.mirai.message.data.QuoteReply;
import xyz.caoxin.group.bot.constants.InfoConstants;
import xyz.caoxin.group.bot.service.Email;
import xyz.caoxin.group.bot.util.bot.MiraiBotUtils;
import xyz.caoxin.group.bot.util.common.ExceptionUtils;

import java.util.HashMap;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.*;

@Slf4j
public class MiraiCommandExecutor {
    public static ThreadPoolExecutor handler;
    public static HashMap<UUID, Future<String>> commandOnHandleList = new HashMap<>();

    /**
     * 并发处理消息并处理异常
     *
     * @param group        群组信息
     * @param commandToken 命令
     */
    public static void execute(MessageChain messageChain, Group group, List<String> commandToken) {
        if (handler == null || handler.isShutdown()) {
            handler = new ThreadPoolExecutor(
                    2,
                    2,
                    1000L,
                    TimeUnit.MILLISECONDS,
                    new LinkedBlockingQueue<>(),
                    new ThreadFactoryBuilder().setNameFormat("miraiBot-command-handler-%d").build()
            );
        }
        UUID uuid = UUID.randomUUID();
        Callable<String> task = () -> {
            try {
                // Mirai消息协议适配
                String msg = CommandHandler.handler(commandToken) == null ? InfoConstants.COMMAND_INVALID : CommandHandler.handler(commandToken);
                MessageChainBuilder chain = new MessageChainBuilder()
                        .append(new QuoteReply(messageChain))
                        .append(msg)
                        .append("\n");
                MiraiBotUtils.sendMessage(group, chain.build());

                commandOnHandleList.remove(uuid);
                if (commandOnHandleList.isEmpty()) {
                    handler.shutdown();
                }
            } catch (Exception e) {
                log.error(e.getLocalizedMessage());
                log.error(ExceptionUtils.getExceptionStackTrace(e));
                Email.sendWarnMessage(InfoConstants.BOT_ERROR_MAIL_SUBJECT,
                        String.format(InfoConstants.BOT_ERROR_MAIL_INFO,
                                Thread.currentThread().getName(),
                                e,
                                ExceptionUtils.getExceptionStackTrace(e)
                        ));
                handler.shutdownNow();
                commandOnHandleList.remove(uuid);
                throw new RuntimeException(e);
            }
            return "Task completed";
        };
        Future<String> future = handler.submit(task);
        commandOnHandleList.put(uuid, future);
    }
}
