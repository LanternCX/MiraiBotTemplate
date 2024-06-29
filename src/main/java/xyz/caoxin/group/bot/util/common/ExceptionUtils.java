package xyz.caoxin.group.bot.util.common;

import java.io.PrintWriter;
import java.io.StringWriter;

/**
 * 异常处理工具类
 */
public class ExceptionUtils {
    /**
     * 获取异常的StackTrace
     *
     * @param exception 异常
     * @return String形式的StackTrace
     */
    public static String getExceptionStackTrace(Exception exception) {
        StringWriter sw = new StringWriter();
        exception.printStackTrace(new PrintWriter(sw));
        return sw.toString();
    }



}
