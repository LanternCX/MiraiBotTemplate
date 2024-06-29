package xyz.caoxin.group.bot;

import lombok.extern.slf4j.Slf4j;
import net.mamoe.mirai.event.EventHandler;
import net.mamoe.mirai.event.SimpleListenerHost;
import net.mamoe.mirai.event.events.GroupMessageEvent;
import org.jetbrains.annotations.NotNull;
import xyz.caoxin.group.bot.command.MiraiCommandExecutor;
import xyz.caoxin.group.bot.constants.InfoConstants;

import java.util.List;

@Slf4j
public class MiraiListener extends SimpleListenerHost {
    private static final String AT_TAG = "@";

    /**
     * 处理群消息
     *
     * @param groupMessageEvent 群消息事件
     */
    @EventHandler
    public void onGroupMessage(@NotNull GroupMessageEvent groupMessageEvent) {
        if (!groupMessageEvent.getMessage().contentToString().contains(AT_TAG + groupMessageEvent.getBot().getId())) {
            return;
        }
        log.info(InfoConstants.BOT_RECEIVE_GROUP_MESSAGE, groupMessageEvent.getMessage());

        List<String> command = List.of(groupMessageEvent.getMessage().contentToString().split(" "));
        MiraiCommandExecutor.execute(groupMessageEvent.getMessage(), groupMessageEvent.getGroup(), command);
    }
}
