package xyz.caoxin.group.bot.util.common;

import java.io.File;

public class FileUtils {

    /**
     * 判断路径是否是绝对路径
     * @param path 路径
     * @return 是否是绝对路径
     */
    public static boolean isAbsolutePath(String path) {
        File file = new File(path);
        return file.isAbsolute();
    }

    /**
     * 将相对路径转换为绝对路径
     * @param relativePath 相对路径
     * @param baseDir 基准路径
     * @return 绝对路径
     */
    public static String convertToAbsolutePath(String relativePath, String baseDir) {
        File relativeFile = new File(relativePath);
        if (relativeFile.isAbsolute()) {
            return relativeFile.getAbsolutePath();
        } else {
            return new File(baseDir, relativePath).getAbsolutePath();
        }
    }

    /**
     * 检查目录是否存在
     *
     * @param dirPath 要检查的目录路径
     * @return 是否存在目录
     */
    public static boolean isDirectoryExists(String dirPath) {
        File directory = new File(dirPath);
        return directory.exists() && directory.isDirectory();
    }
}
