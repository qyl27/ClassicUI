package cx.rain.classicui.utility;

public enum HandleResult {
    HANDLED,
    IGNORED,
    ;

    public static HandleResult of(boolean isHandled) {
        return isHandled ? HANDLED : IGNORED;
    }

    public static boolean toBoolean(HandleResult result) {
        return result == HANDLED;
    }
}
