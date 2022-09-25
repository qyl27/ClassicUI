package cx.rain.classicui.utility;

public class InputHandler {
    @FunctionalInterface
    public interface Click {
        public HandleResult onClick(int x, int y, int button);

        public static final Click NOOP = (x, y, button) -> HandleResult.IGNORED;
    }

    // Todo: qyl27: other input handler interface.
}
