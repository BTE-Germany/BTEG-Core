package de.btegermany.core.paper.item.head;

import com.destroystokyo.paper.profile.PlayerProfile;
import com.destroystokyo.paper.profile.ProfileProperty;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;

import java.util.Base64;
import java.util.UUID;

public class CustomHeadConverter {

    public static ItemStack toItemStack(CustomHead head) {
        return toItemStack(head.getTexture());
    }

    public static ItemStack toItemStack(String texture) {
        ItemStack head = new ItemStack(Material.PLAYER_HEAD);

        head.editMeta(SkullMeta.class, skullMeta -> {
            final PlayerProfile playerProfile = Bukkit.createProfile(UUID.fromString("4da5d3b2-565d-11ef-b11e-325096b39f47")); // random uuid
            playerProfile.setProperty(new ProfileProperty("textures", Base64.getEncoder().encodeToString(("{\"textures\":{\"SKIN\":{\"url\":\"http://textures.minecraft.net/texture/" + texture + "\"}}}").getBytes())));

            skullMeta.setPlayerProfile(playerProfile);
        });

        return head;
    }

}
