package cx.rain.mc.classicui.utility.render;

import net.minecraft.util.FastColor;

public record Color(int red, int green, int blue, int alpha) {
    public static final Color WHITE = new Color(255, 255, 255, 255);

    public Color(int red, int green, int blue) {
        this(red, green, blue, 255);
    }

    public Color(int packedColor) {
        this(FastColor.ARGB32.red(packedColor), FastColor.ARGB32.green(packedColor),
                FastColor.ARGB32.blue(packedColor), FastColor.ARGB32.alpha(packedColor));
    }

    public int toPackedARGB() {
        return FastColor.ARGB32.color(alpha, red, green, blue);
    }
}
