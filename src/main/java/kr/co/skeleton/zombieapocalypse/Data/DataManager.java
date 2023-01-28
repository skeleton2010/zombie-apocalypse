package kr.co.skeleton.zombieapocalypse.Data;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.ByteBuffer;
import java.util.logging.Level;
import kr.co.skeleton.zombieapocalypse.Main;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

public class DataManager {
    private Main plugin;
    private FileConfiguration dataconfig = null;
    private File configfile = null;

    public DataManager(Main plugin) {
        this.plugin = plugin;
        this.saveDefaultConfig();
    }

    public void reloadConfig() {
        if (this.configfile == null) {
            this.configfile = new File(this.plugin.getDataFolder(), "config.yml");
        }

        this.dataconfig = YamlConfiguration.loadConfiguration(this.configfile);
        InputStream defaultStream = this.plugin.getResource("config.yml");
        if (defaultStream != null) {
            YamlConfiguration defaultConfig = YamlConfiguration.loadConfiguration(new InputStreamReader(defaultStream));
            this.dataconfig.setDefaults(defaultConfig);
        }

    }

    public FileConfiguration getDataconfig() {
        if (this.dataconfig == null) {
            this.reloadConfig();
        }

        return this.dataconfig;
    }

    public void saveConfig() {
        if (this.dataconfig != null && this.configfile != null) {
            try {
                this.getDataconfig().save(this.configfile);
            } catch (IOException var2) {
                this.plugin.getLogger().log(Level.SEVERE, "Could not save config to" + this.configfile, var2);
            }

        }
    }

    public void saveDefaultConfig() {
        if (this.configfile == null) {
            this.configfile = new File(this.plugin.getDataFolder(), "config.yml");
        }

        if (!this.configfile.exists()) {
            this.plugin.saveResource("config.yml", false);
        }
    }
}