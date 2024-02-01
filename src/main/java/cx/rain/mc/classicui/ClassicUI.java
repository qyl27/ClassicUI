package cx.rain.mc.classicui;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.Properties;

public class ClassicUI {
    public static final String MODID = "classicui";
    public static final String NAME = "ClassicUI";
    public static final String VERSION;

    static {
        var properties = new Properties();
        var version = "";
        try {
            properties.load(ClassicUI.class.getResourceAsStream("/build.properties"));
            version = properties.getProperty("mod_version");
        } catch (IOException ex) {
            version = "Unknown";
        }
        VERSION = version;
    }

    private static ClassicUI INSTANCE = null;

    private final Logger logger = LoggerFactory.getLogger(NAME);

    public ClassicUI() {
        INSTANCE = this;

        logger.info("Loading ClassicUI. Version: " + VERSION);
    }

    public static ClassicUI getInstance() {
        return INSTANCE;
    }

    public void init() {

    }

    public Logger getLogger() {
        return logger;
    }
}
