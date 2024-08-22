package cx.rain.mc.classicui.forge;

import cx.rain.mc.classicui.ClassicUI;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(value = ClassicUI.MODID)
public class ClassicUIForge {
    private final ClassicUI mod;

    public ClassicUIForge() {
        mod = new ClassicUI();

        var bus = FMLJavaModLoadingContext.get().getModEventBus();
        bus.addListener(this::setup);
        bus.addListener(this::setupClient);
    }

    private void setup(FMLCommonSetupEvent event) {
        mod.init();
    }

    private void setupClient(FMLClientSetupEvent event) {
        mod.initClient();
    }
}
