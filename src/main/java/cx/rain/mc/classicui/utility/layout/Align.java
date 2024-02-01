package cx.rain.mc.classicui.utility.layout;

public record Align(Horizontal horizontal, Vertical vertical) {
    public static final Align CENTER = new Align(Horizontal.MIDDLE, Vertical.CENTER);

    public enum Horizontal {
        LEFT,
        MIDDLE,
        RIGHT,
        ;
    }

    public enum Vertical {
        TOP,
        CENTER,
        BOTTOM,
        ;
    }
}
