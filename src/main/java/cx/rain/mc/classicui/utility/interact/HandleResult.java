package cx.rain.mc.classicui.utility.interact;

public enum HandleResult {
    HANDLED(true, true),
    BLOCK(false, true),
    TRANSPARENT(true, false),
    IGNORE(false, false),
    ;

    private final boolean isHandled;
    private final boolean isBlocked;

    /**
     * An event handle result.
     * @param isHandled Was handled?
     * @param isBlocked Should pass to widget under it?
     */
    HandleResult(boolean isHandled, boolean isBlocked) {
        this.isHandled = isHandled;
        this.isBlocked = isBlocked;
    }

    public boolean isHandled() {
        return isHandled;
    }

    public boolean isBlocked() {
        return isBlocked;
    }
}
