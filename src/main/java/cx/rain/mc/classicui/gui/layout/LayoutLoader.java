package cx.rain.mc.classicui.gui.layout;

import com.mojang.serialization.Codec;
import cx.rain.mc.classicui.gui.layout.entries.TextureEntry;

import java.util.HashMap;
import java.util.Map;

public class LayoutLoader {
    private static final Map<String, Codec<? extends AbstractLayoutEntry>> CODECS = new HashMap<>();
    public static final Codec<AbstractLayoutEntry> ENTRY_CODEC = Codec.STRING.dispatch(AbstractLayoutEntry::getType, CODECS::get);

    static {
        addCodec(TextureEntry.TYPE, TextureEntry.CODEC);
    }

    public static void addCodec(String type, Codec<? extends AbstractLayoutEntry> codec) {
        CODECS.put(type, codec);
    }


}
