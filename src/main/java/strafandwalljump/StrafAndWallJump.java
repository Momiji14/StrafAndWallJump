package strafandwalljump;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerToggleSneakEvent;
import org.bukkit.event.player.PlayerToggleSprintEvent;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.util.Vector;

import static strafandwalljump.PlayerData.playerData;

public final class StrafAndWallJump extends JavaPlugin implements Listener {

    public static Plugin plugin;

    @Override
    public void onEnable() {
        plugin = this;
        Bukkit.getPluginManager().registerEvents(this, plugin);
    }

    @Override
    public void onDisable() {

    }

    @EventHandler
    public void onSneakToggle(PlayerToggleSneakEvent event) {
        Player player = event.getPlayer();
        PlayerData playerData = playerData(player);
        if (player.isSneaking()) {
            Vector vector = player.getLocation().getDirection().setY(0).normalize();
            if (player.getLocation().clone().add(vector).getBlock().isSolid()) {
                player.setVelocity(vector.multiply(-1).setY(0.5));
                playerData.setStraf(2);
                playerData.setWallJumped();
            }
        }
    }

    @EventHandler
    public void onSprintToggle(PlayerToggleSprintEvent event) {
        Player player = event.getPlayer();
        PlayerData playerData = playerData(player);
        if (!player.isSprinting() && playerData.getStraf() > 0) {
            Vector vector = player.getLocation().getDirection().setY(0).normalize();
            if (playerData.isWallJumped()) vector.setY(0.5);
            player.setVelocity(vector);
            playerData.setStraf(playerData.getStraf()-1);
        }
    }
}
