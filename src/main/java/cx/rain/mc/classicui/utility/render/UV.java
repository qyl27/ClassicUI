package cx.rain.mc.classicui.utility.render;

public record UV(float uOffsetF, float vOffsetF, float uWidthF, float vHeightF) {
    public UV(int uOffset, int vOffset, int uWidth, int vHeight, int textureWidth, int textureHeight) {
        this((float) uOffset / textureWidth, (float) vOffset / textureHeight,
                (float) uWidth / textureWidth, (float) vHeight / textureHeight);
    }

    public UV(int uOffset, int vOffset, int uWidth, int vHeight) {
        this((float) uOffset / 256, (float) vOffset / 256,
                (float) uWidth / 256, (float) vHeight / 256);
    }

    public UV(int uWidth, int vHeight) {
        this(0, 0, (float) uWidth / 256, (float) vHeight / 256);
    }
}
