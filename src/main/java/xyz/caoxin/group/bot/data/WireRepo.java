package xyz.caoxin.group.bot.data;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.context.WebServerInitializedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import xyz.caoxin.group.bot.ConfigLoader;

/**
 * @author 曹鑫
 */
@Component
public class WireRepo {
    public static ConfigLoader configLoader;
    public static int springPort;

    @Autowired
    public void autoWire(ConfigLoader configLoader) {
        WireRepo.configLoader = configLoader;
    }

    @EventListener(WebServerInitializedEvent.class)
    public void onApplicationEvent(WebServerInitializedEvent evt) {
        WireRepo.springPort = evt.getWebServer().getPort();
    }
}