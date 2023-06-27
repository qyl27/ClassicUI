package cx.rain.mc.classicui.fabric;

import cx.rain.mc.classicui.ClassicUI;
import net.fabricmc.api.ModInitializer;

public class ClassicUIFabric implements ModInitializer {
    @Override
    public void onInitialize() {
        ClassicUI.init();
    }
}