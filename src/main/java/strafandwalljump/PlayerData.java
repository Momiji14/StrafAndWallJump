package strafandwalljump;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.HashMap;

import static strafandwalljump.StrafAndWallJump.plugin;

public class PlayerData {

    private static final HashMap<Player, PlayerData> PlayerDataList = new HashMap<>();

    private final Player player;
    private int Straf = 2;
    private boolean isWallJumped;

    public static PlayerData playerData(Player player) {
        if (!PlayerDataList.containsKey(player)) {
            PlayerDataList.put(player, new PlayerData(player));
        }
        return PlayerDataList.get(player);
    }

    public PlayerData(Player player) {
        this.player = player;
    }

    public void setStraf(int straf) {
        Straf = straf;
    }

    public int getStraf() {
        return Straf;
    }

    public void setWallJumped() {
        isWallJumped = true;
        Bukkit.getScheduler().runTaskLaterAsynchronously(plugin, () -> isWallJumped = true, 10);
    }

    public boolean isWallJumped() {
        return isWallJumped;
    }
}
