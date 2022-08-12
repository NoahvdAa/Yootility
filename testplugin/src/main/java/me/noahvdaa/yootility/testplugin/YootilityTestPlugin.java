package me.noahvdaa.yootility.testplugin;

import me.noahvdaa.yootility.ephemeralcommands.EphemeralCommandManager;
import org.bukkit.plugin.java.JavaPlugin;

public class YootilityTestPlugin extends JavaPlugin {

    private EphemeralCommandManager ephemeralCommandManager;

    @Override
    public void onEnable() {
        this.ephemeralCommandManager = EphemeralCommandManager.ephemeralCommandManager(this.getCommand("yootilitytestephemeral"));
        this.getCommand("friendrequest").setExecutor(new FriendRequestCommand(this));
    }

    public EphemeralCommandManager getEphemeralCommandManager() {
        return ephemeralCommandManager;
    }

}
