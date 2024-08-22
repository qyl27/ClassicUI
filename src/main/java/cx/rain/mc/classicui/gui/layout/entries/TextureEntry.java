package cx.rain.mc.classicui.gui.layout.entries;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import cx.rain.mc.classicui.gui.layout.AbstractLayoutEntry;
import net.minecraft.resources.ResourceLocation;

public class TextureEntry extends AbstractLayoutEntry {
    public static final String TYPE = "texture";

    public static final Codec<TextureEntry> CODEC = RecordCodecBuilder.create(instance -> instance.group(
            Codec.STRING.fieldOf("name").forGetter(TextureEntry::getName),
            Codec.INT.fieldOf("x").forGetter(TextureEntry::getX),
            Codec.INT.fieldOf("y").forGetter(TextureEntry::getY),
            Codec.INT.fieldOf("width").forGetter(TextureEntry::getWidth),
            Codec.INT.fieldOf("height").forGetter(TextureEntry::getHeight),
            ResourceLocation.CODEC.fieldOf("texture").forGetter(TextureEntry::getTexture),
            Codec.INT.optionalFieldOf("uOffset", 0).forGetter(TextureEntry::getX),
            Codec.INT.optionalFieldOf("vOffset", 0).forGetter(TextureEntry::getY),
            Codec.INT.optionalFieldOf("uLength", null).forGetter(TextureEntry::getWidth),
            Codec.INT.optionalFieldOf("vLength", null).forGetter(TextureEntry::getHeight),
            Codec.INT.optionalFieldOf("textureWidth", 256).forGetter(TextureEntry::getWidth),
            Codec.INT.optionalFieldOf("textureHeight", 256).forGetter(TextureEntry::getHeight)
    ).apply(instance, TextureEntry::new));

    protected ResourceLocation texture;
    protected int uOffset;
    protected int vOffset;
    protected int uLength;
    protected int vLength;
    protected int textureWidth;
    protected int textureHeight;
    
    public TextureEntry(String name, int x, int y, int width, int height, 
                        ResourceLocation texture, int uOffset, int vOffset, 
                        int uLength, int vLength, int textureWidth, int textureHeight) {
        super(name, x, y, width, height);
    }

    @Override
    public String getType() {
        return TYPE;
    }

    public ResourceLocation getTexture() {
        return texture;
    }

    public int getUOffset() {
        return uOffset;
    }

    public int getVOffset() {
        return vOffset;
    }

    public int getULength() {
        return uLength;
    }

    public int getVLength() {
        return vLength;
    }

    public int getTextureWidth() {
        return textureWidth;
    }

    public int getTextureHeight() {
        return textureHeight;
    }
}
