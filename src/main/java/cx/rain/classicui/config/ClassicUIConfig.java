package cx.rain.classicui.config;

import net.minecraftforge.common.ForgeConfigSpec;

public class ClassicUIConfig {
    public static ForgeConfigSpec CONFIG;

    public static ForgeConfigSpec.BooleanValue DEBUG;

    static {
        var builder = new ForgeConfigSpec.Builder();

        builder.comment("General settings for Classic UI.")
                .push("general");

        DEBUG = builder.comment("Register debug gui.")
                        .define("debug", false);

        builder.pop();

        CONFIG = builder.build();
    }
}
