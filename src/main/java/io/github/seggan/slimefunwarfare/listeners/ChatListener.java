package io.github.seggan.slimefunwarfare.listeners;

import io.github.mooy1.infinitylib.common.Scheduler;
import io.github.seggan.slimefunwarfare.items.Radio;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.hover.content.Text;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.inventory.ItemStack;

public class ChatListener implements Listener {

    @EventHandler(ignoreCancelled = true, priority = EventPriority.LOWEST)
    public void onChat(AsyncPlayerChatEvent e) {
        ItemStack held = e.getPlayer().getInventory().getItemInMainHand();
        if (SlimefunItem.getByItem(held) instanceof Radio) {
            e.setCancelled(true);
            Scheduler.run(() -> sync(e, Radio.getKey(held)));
        }
    }

    private void sync(AsyncPlayerChatEvent e, String key) {
        HoverEvent event = new HoverEvent(
            HoverEvent.Action.SHOW_TEXT,
            new Text("This message was sent using a Radio")
        );

        // don't wanna show the key
        if (e.getMessage().equals(key)) return;

        String encrypted = encrypt(e.getMessage(), key);
        for (Player p : Bukkit.getOnlinePlayers()) {
            for (ItemStack item : p.getInventory().getContents()) {
                if (SlimefunItem.getByItem(item) instanceof Radio) {
                    String decrypted = decrypt(encrypted, Radio.getKey(item));
                    p.spigot().sendMessage(new ComponentBuilder()
                        .event(event)
                        .color(ChatColor.BLUE)
                        .append("[Radio Message] ")
                        .color(ChatColor.WHITE)
                        .append("<")
                        .append(e.getPlayer().getDisplayName())
                        .append("> ")
                        .append(decrypted)
                        .create()
                    );
                }
            }
        }
    }

    private static String encrypt(String s, String key) {
        if (key == null) return s;
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < (s.length() / key.length()) + 1; i++) {
            builder.append(key);
        }
        key = builder.toString();

        builder = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            builder.append((char) ((s.charAt(i) ^ key.charAt(i)) + 20));
        }

        return builder.toString();
    }

    private static String decrypt(String s, String key) {
        if (key == null) return s;
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < (s.length() / key.length()) + 1; i++) {
            builder.append(key);
        }
        key = builder.toString();

        builder = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            builder.append((char) ((s.charAt(i) - 20) ^ key.charAt(i)));
        }

        return builder.toString();
    }
}
