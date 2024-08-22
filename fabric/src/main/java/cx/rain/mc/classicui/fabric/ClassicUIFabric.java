package cx.rain.mc.classicui.fabric;

import cx.rain.mc.classicui.ClassicUI;
import net.fabricmc.api.ModInitializer;

public class ClassicUIFabric implements ModInitializer {

    private static ClassicUIFabric INSTANCE;
    private final ClassicUI mod;

    public ClassicUIFabric() {
        INSTANCE = this;

        mod = new ClassicUI();
    }

    public static ClassicUIFabric getInstance() {
        return INSTANCE;
    }

    @Override
    public void onInitialize() {
        mod.init();
    }

    public ClassicUI getMod() {
        return mod;
    }
}
