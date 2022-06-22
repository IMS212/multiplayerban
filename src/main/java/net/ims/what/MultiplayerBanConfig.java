package net.ims.what;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Properties;

public class MultiplayerBanConfig {
    private static final String COMMENT =
            "This file stores configuration options for MultiplayerBan";


    private int timeBannedInDays;
    private boolean permBan;

    private final Path propertiesPath;

    public MultiplayerBanConfig(Path propertiesPath) {
        timeBannedInDays = 30;
        permBan = false;
        this.propertiesPath = propertiesPath;
    }

    /**
     * Initializes the configuration, loading it if it is present and creating a default config otherwise.
     *
     * @throws IOException file exceptions
     */
    public void initialize() throws IOException {
        load();
        if (!Files.exists(propertiesPath)) {
            save();
        }
    }

    public int getTimeBannedInDays() {
        return timeBannedInDays;
    }

    public boolean isPermBan() {
        return permBan;
    }

    public void load() throws IOException {
        if (!Files.exists(propertiesPath)) {
            return;
        }

        Properties properties = new Properties();
        // NB: This uses ISO-8859-1 with unicode escapes as the encoding
        properties.load(Files.newInputStream(propertiesPath));
        timeBannedInDays = Integer.parseInt(properties.getProperty("timeBannedInDays"));
        permBan = "true".equals(properties.getProperty("permBan"));
    }

    /**
     * Serializes the config into a file. Should be called whenever any config values are modified.
     *
     * @throws IOException file exceptions
     */
    public void save() throws IOException {
        Properties properties = new Properties();
        properties.setProperty("timeBannedInDays", String.valueOf(timeBannedInDays));
        properties.setProperty("permBan", permBan ? "true" : "false");
        // NB: This uses ISO-8859-1 with unicode escapes as the encoding
        properties.store(Files.newOutputStream(propertiesPath), COMMENT);
    }
}
