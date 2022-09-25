package cx.rain.classicui.utility;

public record ContentAlign(Horizontal horizontal, Vertical vertical) {
    public static final ContentAlign CENTER = new ContentAlign(Horizontal.MIDDLE, Vertical.CENTER);

    public enum Horizontal {
        LEFT,
        MIDDLE,
        RIGHT;
    }

    public enum Vertical {
        TOP,
        CENTER,
        BOTTOM;
    }
}
