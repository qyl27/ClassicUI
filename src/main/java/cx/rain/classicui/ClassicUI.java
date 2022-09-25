package cx.rain.classicui;

import cx.rain.classicui.config.ClassicUIConfig;
import cx.rain.classicui.config.ClassicUIConfigClient;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Mod(ClassicUI.MODID)
public class ClassicUI {
    public static final String MODID = "classicui";
    public static final String NAME = "Classic UI";
    public static final String VERSION = "@version@";

    private static ClassicUI INSTANCE = null;

    private Logger logger = LoggerFactory.getLogger(NAME);

    public ClassicUI() {
        INSTANCE = this;

        logger.info("Loading Classic UI. Version: " + VERSION);

        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, ClassicUIConfig.CONFIG, "classic-ui.toml");
        ModLoadingContext.get().registerConfig(ModConfig.Type.CLIENT, ClassicUIConfigClient.CONFIG, "classic-ui-client.toml");
    }

    public static ClassicUI getInstance() {
        return INSTANCE;
    }

    public Logger getLogger() {
        return logger;
    }
}
