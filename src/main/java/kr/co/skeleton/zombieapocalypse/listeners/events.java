package kr.co.skeleton.zombieapocalypse.listeners;

import com.destroystokyo.paper.event.entity.EntityJumpEvent;
import kr.co.skeleton.zombieapocalypse.randomSpawn.testZombie;
import net.minecraft.server.v1_16_R3.WorldServer;
import org.bukkit.*;
import org.bukkit.block.Block;
import org.bukkit.craftbukkit.v1_16_R3.CraftWorld;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.entity.EntitySpawnEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.inventory.CraftingInventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;
import org.bukkit.util.Vector;

import java.util.ArrayList;
import java.util.List;

public class events implements Listener {

    @EventHandler
    public void onPlayerDeath(PlayerDeathEvent e) {
        Player p = e.getEntity();
        Location l = p.getLocation();
        Entity PlayerZombie = e.getEntity().getWorld().spawnEntity(l, EntityType.ZOMBIE);
        p.setGameMode(GameMode.SPECTATOR);
        String name = p.getName();
        PlayerZombie.addScoreboardTag(name);
        LivingEntity livingEntity = (LivingEntity) PlayerZombie;
        Bukkit.broadcastMessage(name);
        livingEntity.setCustomName(name);
        livingEntity.setCustomNameVisible(true);
        ItemStack skull = new ItemStack(Material.PLAYER_HEAD);
        SkullMeta skullMeta = (SkullMeta) skull.getItemMeta();
        skullMeta.setOwningPlayer(Bukkit.getOfflinePlayer(name));
        skull.setItemMeta(skullMeta);
        livingEntity.getEquipment().setHelmet(skull);
        p.teleport(PlayerZombie.getLocation());
    }

//    @EventHandler
//    public  void onPlayerDeath(PlayerRespawnEvent e) {
//        Player p = e.getPlayer();
//        Location l = p.getLocation();
//        Entity PlayerZombie = e.getPlayer().getWorld().spawnEntity(l, EntityType.ZOMBIE);
//        p.setGameMode(GameMode.SPECTATOR);
//        String name = p.getName();
//        PlayerZombie.addScoreboardTag(name);
//        LivingEntity livingEntity = (LivingEntity) PlayerZombie;
//        Bukkit.broadcastMessage(name);
//        livingEntity.setCustomName(name);
//        livingEntity.setCustomNameVisible(true);
//        ItemStack skull = new ItemStack(Material.PLAYER_HEAD);
//        SkullMeta skullMeta = (SkullMeta) skull.getItemMeta();
//        skullMeta.setOwningPlayer(Bukkit.getOfflinePlayer(name));
//        skull.setItemMeta(skullMeta);
//        livingEntity.getEquipment().setHelmet(skull);
//    }

    @EventHandler
    public void onZombie(EntityDeathEvent e) {
        Entity entity = e.getEntity();
        e.getDrops().clear();
        String entityType = String.valueOf(entity);
        System.out.println(entity);
        Object[] EntityTags = entity.getScoreboardTags().toArray();
        Location TNTLocation = entity.getLocation();
        System.out.println(EntityTags[0]);
        System.out.println(entity.getScoreboardTags());
        if (entityType.equals("CraftZombie")) {
            if (EntityTags[0].equals("boom")) {
                Entity tnt = e.getEntity().getWorld().spawnEntity(TNTLocation, EntityType.PRIMED_TNT);
                TNTPrimed tnt2 = (TNTPrimed) tnt;
                tnt2.setFuseTicks(0);
            } else if (EntityTags[0].equals("split")) {
                Location l = entity.getLocation();
                Entity split1 = e.getEntity().getWorld().spawnEntity(l, EntityType.ZOMBIE);
                Entity split2 = e.getEntity().getWorld().spawnEntity(l, EntityType.ZOMBIE);
                Entity split3 = e.getEntity().getWorld().spawnEntity(l, EntityType.ZOMBIE);
                Entity split4 = e.getEntity().getWorld().spawnEntity(l, EntityType.ZOMBIE);
                split1.addScoreboardTag("split2");
                split2.addScoreboardTag("split2");
                split3.addScoreboardTag("split2");
                split4.addScoreboardTag("split2");
                ((LivingEntity) split1).getEquipment().setHelmet(new ItemStack(Material.LEATHER_HELMET));
                ((LivingEntity) split2).getEquipment().setHelmet(new ItemStack(Material.LEATHER_HELMET));
                ((LivingEntity) split3).getEquipment().setHelmet(new ItemStack(Material.LEATHER_HELMET));
                ((LivingEntity) split4).getEquipment().setHelmet(new ItemStack(Material.LEATHER_HELMET));
            } else if (EntityTags[0].equals("split2")) {
                Location l = entity.getLocation();
                Entity split1 = e.getEntity().getWorld().spawnEntity(l, EntityType.ZOMBIE);
                Entity split2 = e.getEntity().getWorld().spawnEntity(l, EntityType.ZOMBIE);
                Entity split3 = e.getEntity().getWorld().spawnEntity(l, EntityType.ZOMBIE);
                Entity split4 = e.getEntity().getWorld().spawnEntity(l, EntityType.ZOMBIE);
                split1.addScoreboardTag("split3");
                split2.addScoreboardTag("split3");
                split3.addScoreboardTag("split3");
                split4.addScoreboardTag("split3");
                ((LivingEntity) split1).getEquipment().setHelmet(new ItemStack(Material.LEATHER_HELMET));
                ((LivingEntity) split2).getEquipment().setHelmet(new ItemStack(Material.LEATHER_HELMET));
                ((LivingEntity) split3).getEquipment().setHelmet(new ItemStack(Material.LEATHER_HELMET));
                ((LivingEntity) split4).getEquipment().setHelmet(new ItemStack(Material.LEATHER_HELMET));
            } else {
                System.out.println("sec but don`t have boom tag");
            }
            System.out.println(entity.getLocation());
            System.out.println("scuess");
        } else {
            System.out.println("false");
        }
    }


    @EventHandler
    public void onJob(PlayerJoinEvent e) {
        Player p = e.getPlayer();
        p.sendMessage("본서버는 테섭이므로 버그가 존재할 수 있습니다.");
//        TextComponent text = new TextComponent("test");
//        TextComponent text2 = new TextComponent("test2");
//        TextComponent text3 = new TextComponent("test3");
//        TextComponent text4 = new TextComponent("test4");
//        text.setBold(true);
//        p.spigot().sendMessage(text);
//        p.spigot().sendMessage(text2);
//        p.spigot().sendMessage(text3);
//        p.spigot().sendMessage(text4);
    }

    @EventHandler
    public void onvaccine(PlayerInteractEvent e) {
        Player p = e.getPlayer();
        Action a = e.getAction();
        if (a == Action.RIGHT_CLICK_AIR || a == Action.RIGHT_CLICK_BLOCK) {
            if (p.getItemInHand().getType().equals(Material.DEBUG_STICK)) {
                List<String> players = new ArrayList<String>();
                for (Player online : Bukkit.getServer().getOnlinePlayers()) {
                    players.add(online.getName());
                }
                Entity targetEntity = p.getTargetEntity(20);
                if (targetEntity == null) {
                    System.out.println("null");
                } else {
                    EntityType targetEntityType = targetEntity.getType();
                    Object[] EntityTags = targetEntity.getScoreboardTags().toArray();
                    Player player = Bukkit.getPlayer((String) EntityTags[0]);
                    if (targetEntityType.equals(EntityType.ZOMBIE)) {
                        if (players.contains(EntityTags[0])) {
                            if (player != null) {
                                for (World world : Bukkit.getWorlds()) {
                                    for (Entity entity : world.getEntities()) {
                                        if (entity instanceof Zombie) {
                                            if (entity.getCustomName() != null) {
                                                if (entity.getCustomName().equals(EntityTags[0])) {
                                                    ((Zombie) entity).setHealth(0);
                                                    player.setGameMode(GameMode.SURVIVAL);
                                                    player.teleport(entity.getLocation());
                                                    int amount = 1;
                                                    p.getInventory().removeItem(new ItemStack(Material.DEBUG_STICK, amount));
                                                }
                                            } else {
                                                System.out.println("커스텀 이름이 없습니다.");
                                            }
                                        }
                                    }
                                }
                            } else {
                                System.out.println("본플레이어는 온라인이 아닙니다.");
                            }
                        } else {
                            System.out.println("본플레이어는 온라인이 아닙니다.");
                        }
                    } else {
                        System.out.println("타겟이 좀비가 아닙니다.");
                        int amount = 1;
                        p.getInventory().removeItem(new ItemStack(Material.DIAMOND_PICKAXE, amount));
                    }
                }
            }
        }
    }

    @EventHandler
    public void onJump(EntityJumpEvent e) {
        Entity entity = e.getEntity();
        EntityType entityType = entity.getType();
        Object[] EntityTags = entity.getScoreboardTags().toArray();
        if (entityType.equals(EntityType.ZOMBIE) && EntityTags != null) {
            for (Object tag : EntityTags) {
                if (tag.equals("jump")) {
                    e.setCancelled(true);
                    Vector velocity = new Vector(0, 0.85, 0);
                    entity.setVelocity(velocity);
                    break;
                }
            }
        }
    }

//    @EventHandler
//    public void onSpawn(EntitySpawnEvent e) {
//        if (!(e.getEntity() instanceof Monster)) return;
//        EntityType entityType = e.getEntity().getType();
//        if (e.getLocation().getBlock().isLiquid()) return;
//        if (!entityType.equals(EntityType.ZOMBIE)) e.setCancelled(true);
//        if (e.getLocation().getBlock().getType().isAir()) {
//            if ((int) (Math.random() * 50) == 1) {
//                e.setCancelled(true);
//                testZombie pack = new testZombie(e.getLocation());
//                WorldServer world  =((CraftWorld)e.getLocation().getWorld()).getHandle();
//                world.addEntity(pack);
//
//                Bukkit.broadcastMessage("" + e.getLocation());
//                Player player = (Player) Bukkit.getServer().getOnlinePlayers();
//                player.teleport(e.getLocation());
//            }
//        }
//    }

//    @EventHandler
//    public void BlockPlace(BlockPlaceEvent e) {
//        Player p = e.getPlayer();
//        Block block = e.getBlock();
//        Material material = block.getType();
//        Location blockLoc = block.getLocation();
//        if (material.equals(Material.BEDROCK)) {
//            block.setType(Material.AIR);
//            p.sendMessage(ChatColor.RED + "You can't place Bedrock");
//        }
//        p.teleport(blockLoc);
//    }
}
