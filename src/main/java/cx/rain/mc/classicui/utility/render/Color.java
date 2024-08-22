package cx.rain.mc.classicui.utility.render;

import net.minecraft.util.FastColor;

public record Color(int red, int green, int blue, int alpha) {
    public static final Color WHITE = new Color(255, 255, 255);
    public static final Color BLACK = new Color(0, 0, 0);

    public Color(int red, int green, int blue) {
        this(red, green, blue, 255);
    }

    public Color(int packedARGB) {
        this(FastColor.ARGB32.red(packedARGB), FastColor.ARGB32.green(packedARGB),
                FastColor.ARGB32.blue(packedARGB), FastColor.ARGB32.alpha(packedARGB));
    }

    public int toPackedARGB() {
        return FastColor.ARGB32.color(alpha, red, green, blue);
    }
}
