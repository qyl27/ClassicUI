package cx.rain.mc.classicui.forge;

import dev.architectury.platform.forge.EventBuses;
import cx.rain.mc.classicui.ClassicUI;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(ClassicUI.MOD_ID)
public class ClassicUIForge {
    public ClassicUIForge() {
		// Submit our event bus to let architectury register our content on the right time
        EventBuses.registerModEventBus(ClassicUI.MOD_ID, FMLJavaModLoadingContext.get().getModEventBus());

        ClassicUI.init();
    }
}