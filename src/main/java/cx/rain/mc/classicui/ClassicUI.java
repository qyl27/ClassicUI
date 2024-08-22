package cx.rain.mc.classicui;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.time.ZonedDateTime;
import java.util.Properties;

public class ClassicUI {
    public static final String MODID = "classicui";
    public static final String NAME = "ClassicUI";
    public static final String VERSION;
    public static final ZonedDateTime BUILD_TIME;

    static {
        var properties = new Properties();
        String version;
        ZonedDateTime buildTime;
        try {
            properties.load(ClassicUI.class.getResourceAsStream("/build_info.properties"));
            version = properties.getProperty("mod_version") + "+mc" + properties.getProperty("minecraft_version");
            buildTime = ZonedDateTime.parse(properties.getProperty("build_time"));
        } catch (Exception ignored) {
            version = "Unknown";
            buildTime = null;
        }
        BUILD_TIME = buildTime;
        VERSION = version;
    }

    private static ClassicUI INSTANCE = null;

    private final Logger logger = LoggerFactory.getLogger(NAME);

    public ClassicUI() {
        INSTANCE = this;

        logger.info("Loading ClassicUI. Ver: {}, Build at: {}", VERSION, BUILD_TIME != null ? BUILD_TIME : "B.C. 3200");
    }

    public static ClassicUI getInstance() {
        return INSTANCE;
    }

    public void init() {

    }

    public void initClient() {

    }

    public Logger getLogger() {
        return logger;
    }
}
