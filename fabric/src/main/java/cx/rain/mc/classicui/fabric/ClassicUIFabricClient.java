package cx.rain.mc.classicui.fabric;

import net.fabricmc.api.ClientModInitializer;

public class ClassicUIFabricClient implements ClientModInitializer {

    @Override
    public void onInitializeClient() {
        ClassicUIFabric.getInstance().getMod().initClient();
    }
}
