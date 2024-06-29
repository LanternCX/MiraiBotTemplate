package xyz.caoxin.group.bot.command;

import java.util.List;

public class CommandHandler {

    public static String handler(List<String> commandToken) {
        if (commandToken.size() == 1) {
            return null;
        }
        return "你好!";
    }
}
