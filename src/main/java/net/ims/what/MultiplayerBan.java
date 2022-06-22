package net.ims.what;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.loader.api.FabricLoader;

import java.io.IOException;

public class MultiplayerBan {
    public static MultiplayerBanConfig CONFIG;
    public static boolean ready;

    public static void onInitialize() {
        ready = true;
        CONFIG = new MultiplayerBanConfig(FabricLoader.getInstance().getConfigDir().resolve("multiplayerban.properties"));
        try {
            CONFIG.initialize();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
