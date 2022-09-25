package cx.rain.classicui.utility;

import cx.rain.classicui.ClassicUI;
import cx.rain.classicui.config.ClassicUIConfig;

public class DebugLoggerHelper {
    public static void info(String message, Object... args) {
        if (ClassicUIConfig.DEBUG.get()) {
            ClassicUI.getInstance().getLogger().info(message, args);
        }
    }

    public static void warn(String message, Object... args) {
        if (ClassicUIConfig.DEBUG.get()) {
            ClassicUI.getInstance().getLogger().warn(message, args);
        }
    }

    public static void error(String message, Object... args) {
        if (ClassicUIConfig.DEBUG.get()) {
            ClassicUI.getInstance().getLogger().error(message, args);
        }
    }
}
