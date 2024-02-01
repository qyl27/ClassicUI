package cx.rain.mc.classicui.utility.layout;

public record Padding(int top, int right, int bottom, int left) {
    public static final Padding NONE = new Padding(0);
    public static final Padding PANEL = new Padding(7);

    public Padding {
        if (top < 0 ||
                right < 0 ||
                bottom < 0 ||
                left < 0) {
            throw new IllegalArgumentException("It should not be negative.");
        }
    }

    public Padding(int all) {
        this(all, all, all, all);
    }

    public Padding(int vertical, int horizontal) {
        this(vertical, horizontal, vertical, horizontal);
    }
}
