package cx.rain.mc.classicui.utility.interact;

public enum HandleResult {
    HANDLED(true, true),
    BLOCK(false, true),
    TRANSPARENT(true, false),
    IGNORE(false, false),
    ;

    private final boolean isHandled;
    private final boolean isTerminated;

    /**
     * An event handle result.
     * @param isHandled Was handled?
     * @param isTerminated Should pass to widget which under it?
     */
    HandleResult(boolean isHandled, boolean isTerminated) {
        this.isHandled = isHandled;
        this.isTerminated = isTerminated;
    }

    public boolean isHandled() {
        return isHandled;
    }

    public boolean isTerminated() {
        return isTerminated;
    }
}
