package cx.rain.classicui.config;

import net.minecraftforge.common.ForgeConfigSpec;

public class ClassicUIConfigClient {
    public static ForgeConfigSpec CONFIG;

    public static ForgeConfigSpec.BooleanValue DARK_MODE;

    static {
        var builder = new ForgeConfigSpec.Builder();

        builder.comment("Client settings for Classic UI.")
                .push("client");

        DARK_MODE = builder.comment("Is dark mode enabled? ")
                .define("dark_mode", false);

        builder.pop();

        CONFIG = builder.build();
    }
}
