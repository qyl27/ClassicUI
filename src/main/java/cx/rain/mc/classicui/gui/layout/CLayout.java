package cx.rain.mc.classicui.gui.layout;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import org.jetbrains.annotations.Nullable;

import java.util.Map;

public record CLayout(int width, int height, Map<String, AbstractLayoutEntry> entries,
                      @Nullable AbstractLayoutEntry background) {
    public static final Codec<CLayout> CODEC = RecordCodecBuilder.create(instance -> instance.group(
            Codec.INT.optionalFieldOf("width", 256).forGetter(CLayout::width),
            Codec.INT.optionalFieldOf("height", 256).forGetter(CLayout::height),
            Codec.unboundedMap(Codec.STRING, LayoutLoader.ENTRY_CODEC).fieldOf("entries").forGetter(CLayout::entries),
            LayoutLoader.ENTRY_CODEC.optionalFieldOf("background", null).forGetter(CLayout::background)
    ).apply(instance, CLayout::new));
}
