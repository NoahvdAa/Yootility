package me.noahvdaa.yootility.testplugin;

import me.noahvdaa.yootility.ephemeralcommand.EphemeralCommandManager;
import me.noahvdaa.yootility.testplugin.command.FriendRequestCommand;
import me.noahvdaa.yootility.testplugin.command.MiniMessageCommand;
import org.bukkit.plugin.java.JavaPlugin;

public class YootilityTestPlugin extends JavaPlugin {

    private EphemeralCommandManager ephemeralCommandManager;

    @Override
    public void onEnable() {
        this.ephemeralCommandManager = EphemeralCommandManager.ephemeralCommandManager(this.getCommand("yootilitytestephemeral"));
        this.getCommand("friendrequest").setExecutor(new FriendRequestCommand(this));
        this.getCommand("minimessage").setExecutor(new MiniMessageCommand());
    }

    public EphemeralCommandManager getEphemeralCommandManager() {
        return ephemeralCommandManager;
    }

}
