package kr.co.skeleton.zombieapocalypse;

import kr.co.skeleton.zombieapocalypse.Data.DataManager;
import kr.co.skeleton.zombieapocalypse.TabComplete.TabComplete;
import kr.co.skeleton.zombieapocalypse.listeners.events;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeInstance;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.util.Vector;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.Objects;

public final class Main extends JavaPlugin implements Listener {
    public DataManager data;


    @Override
    public void onEnable() {
        System.out.println("[ZombieApocalypse] plugin Enabled");
        Bukkit.getPluginManager().registerEvents(new events(), this);
        Objects.requireNonNull(Bukkit.getPluginCommand("bug")).setTabCompleter(new TabComplete());
        this.data = new DataManager(this);
        junpZombie();
        String configData;
        String configData2;
        configData = this.data.getDataconfig().getString("development");
        configData2 = this.data.getDataconfig().getString("randomSpawn");
        if (configData != null) {
            System.out.println("not null");
            if (configData2 != null) {
                System.out.println("not null");
                if (configData == "false" && configData2 == "true") {
                    return;
                } else if (configData.equals("true") && configData2.equals("true")) {
                    return;
                } else if (configData.equals("true") && configData2.equals("false")) {
                    return;
                } else if (configData.equals("false") && configData2.equals("false")) {
                    System.out.println("개발 기능이 꺼져있을때 랜덤스폰이 꺼져있을 수 없습니다.");
                    getServer().getPluginManager().disablePlugin(this);
                }
            } else {
                System.out.println("null");
            }
        } else {
            System.out.println("null");
        }
    }

    @Override
    public void onDisable() {
        System.out.println("[ZombieApocalypse] plugin Disabled");
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command cmd, @NotNull String label, @NotNull String[] args) {
        Player p = (Player) sender;
        if (label.equalsIgnoreCase("tests")) {
            p.sendMessage("test" + "\n" + "it's error");
        }
        this.data = new DataManager(this);
        if (label.equalsIgnoreCase("text")) {
            TextComponent text = new TextComponent("test");
            TextComponent text2 = new TextComponent("test2");
            TextComponent text3 = new TextComponent("test3");
            TextComponent text4 = new TextComponent("test4");
            text.setBold(true);
            String text5 = text + " " + text2;
            p.sendMessage(text5);
        } else if (label.equalsIgnoreCase("boom")) {
            Location l = p.getLocation();
            Entity BoomZombie = p.getPlayer().getWorld().spawnEntity(l, EntityType.ZOMBIE);
            BoomZombie.addScoreboardTag("boom");
            ((LivingEntity) BoomZombie).getEquipment().setHelmet(new ItemStack(Material.LEATHER_HELMET));
        } else if (label.equalsIgnoreCase("speed")) {
            Location l = p.getLocation();
            Entity SpeedZombie = p.getPlayer().getWorld().spawnEntity(l, EntityType.ZOMBIE);
            LivingEntity livingEntity = (LivingEntity) SpeedZombie;
            livingEntity.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, Integer.MAX_VALUE, 6));
            ((LivingEntity) SpeedZombie).getEquipment().setHelmet(new ItemStack(Material.LEATHER_HELMET));
        } else if (label.equalsIgnoreCase("slow")) {
            Location l = p.getLocation();
            Entity SlowZombie = p.getPlayer().getWorld().spawnEntity(l, EntityType.ZOMBIE);
            LivingEntity livingEntity = (LivingEntity) SlowZombie;
            livingEntity.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, Integer.MAX_VALUE, 1));
            livingEntity.addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, Integer.MAX_VALUE, 3));
            AttributeInstance healthAttribute = livingEntity.getAttribute(Attribute.GENERIC_MAX_HEALTH);
            healthAttribute.setBaseValue(200);
            livingEntity.setHealth(200);
            ((LivingEntity) SlowZombie).getEquipment().setHelmet(new ItemStack(Material.LEATHER_HELMET));
        } else if (label.equalsIgnoreCase("jump")) {
            Location l = p.getLocation();
            Entity JumpZombie = p.getPlayer().getWorld().spawnEntity(l, EntityType.ZOMBIE);
            ((LivingEntity) JumpZombie).getEquipment().setHelmet(new ItemStack(Material.LEATHER_HELMET));
            JumpZombie.addScoreboardTag("jump");
        } else if (label.equalsIgnoreCase("invisible")) {
            Location l = p.getLocation();
            Entity invisibleZombie = p.getPlayer().getWorld().spawnEntity(l, EntityType.ZOMBIE);
            LivingEntity livingEntity = (LivingEntity) invisibleZombie;
            livingEntity.addPotionEffect(new PotionEffect(PotionEffectType.INVISIBILITY, Integer.MAX_VALUE, 1));
            ((LivingEntity) invisibleZombie).getEquipment().setHelmet(new ItemStack(Material.LEATHER_HELMET));
        } else if (label.equalsIgnoreCase("split")) {
            Location l = p.getLocation();
            Entity splitZombie = p.getPlayer().getWorld().spawnEntity(l, EntityType.ZOMBIE);
            splitZombie.addScoreboardTag("split");
            ((LivingEntity) splitZombie).getEquipment().setHelmet(new ItemStack(Material.LEATHER_HELMET));
        } else if (label.equalsIgnoreCase("vaccine")) {
            ItemStack vaccine = new ItemStack(Material.DEBUG_STICK);
            ItemMeta meta = vaccine.getItemMeta();
            meta.setDisplayName(ChatColor.AQUA + "vaccine");
            vaccine.setItemMeta(meta);
            p.getInventory().addItem(vaccine);
        } else if (label.equalsIgnoreCase("disable")) {
            getServer().getPluginManager().disablePlugin(this);
        } else if (label.equalsIgnoreCase("test")) {
            p.setVelocity(new Vector(0.1, 1, 0.1));
        } else if (label.equalsIgnoreCase("wow")) {
            Entity entity = p.getTargetEntity(20);
            EntityType entityType = entity.getType();
            Object[] EntityTags = entity.getScoreboardTags().toArray();
            if (entityType.equals(EntityType.ZOMBIE) && EntityTags != null) {
                for (Object tag : EntityTags) {
                    if (tag.equals("jump")) {
                        Vector velocity = new Vector(0, 2, 0);
                        entity.setVelocity(velocity);
                        Bukkit.broadcastMessage(String.valueOf(velocity));
                        Bukkit.broadcastMessage("1");
                        break;
                    }
                }
            } else {
                System.out.println("error");
                Bukkit.broadcastMessage(String.valueOf(entityType));
            }
        } else if (label.equalsIgnoreCase("target")) {
            Entity entity = p.getTargetEntity(20);
            List<Player> players = (List<Player>) Bukkit.getOnlinePlayers();
            double y = entity.getLocation().getY();
            for (Player player : players) {
                Location playerLocation = player.getLocation();
                double playerY = playerLocation.getY();
                if (entity.getType().equals(EntityType.ZOMBIE)) {
                    for (Object tag : entity.getScoreboardTags().toArray()) {
                        if (tag.equals("jump")) {
                            if (playerY - y >= 2) {
                                if (playerY - y <= 4) {
                                    Vector velocity = new Vector(0, 0.85, 0);
                                    entity.setVelocity(velocity);
                                    break;
                                } else {
                                    System.out.println("");
                                }
                            } else {
                                System.out.println("");
                            }
                        } else {
                            System.out.println("엔티티 태그가 jump가 아닙니다.");
                        }
                    }
                } else {
                    System.out.println("엔티티 타입이 좀비가아닙니다.");
                }
            }
        } else if (label.equalsIgnoreCase("bug")) {
            if (args.length == 1) {
                if (args[0].equalsIgnoreCase("report")) {
                    p.sendMessage("버그를 리포트 하기 위해 명령어를 입력하셨군요." + "\n " + "www.skeleton26.kro.kr/report " + "에서 버그를 리포트하세요." + "\n" + "감사합니다." + "\n" + "-좀아포 개발팀-");
                } else if (args[0].equalsIgnoreCase("fixed")) {

                } else if (args[0].equalsIgnoreCase("fixing")) {

                }
            }
        }
        return true;
    }

    private void junpZombie() {
        Bukkit.getScheduler().scheduleSyncRepeatingTask(this, new Runnable() {
            public void run() {
                for (Player player : Bukkit.getOnlinePlayers()) {
                    Player p = player;
                    Location playerLocation = p.getLocation();
                    double playerX = playerLocation.getX();
                    double playerY = playerLocation.getY();
                    double playerZ = playerLocation.getZ();
                    for (World world : Bukkit.getWorlds()) {
                        List<Entity> entities = world.getEntities();
                        for (Entity entity : entities) {
                            EntityType type = entity.getType();
                            Location entityLocation = entity.getLocation();
                            double entityX = entityLocation.getX();
                            double entityY = entityLocation.getY();
                            double entityZ = entityLocation.getZ();
                            Object[] entityTags = entity.getScoreboardTags().toArray();
                            if (type.equals(EntityType.ZOMBIE) && entityTags != null) {
                                for (Object tag : entityTags) {
                                    if (tag.equals("jump")) {
                                        if (playerY - entityY >= 2) {
                                            if (playerY - entityY <= 4) {
                                                double noMinusPlayerX = playerX * -1;
                                                double noMinusEntityX = entityX * -1;
                                                double noMinusPlayerZ = playerZ * -1;
                                                double noMinusEntityZ = entityZ * -1;
                                                if (playerX <= -1 && entityX <= -1) {
                                                    //east
                                                    if (noMinusPlayerX - noMinusEntityX >= 1) {
                                                        if (noMinusPlayerX - noMinusEntityX <= 6) {
                                                            Vector velocity = new Vector(0, 0.85, 0);
                                                            entity.setVelocity(velocity);
                                                        }
                                                        //wast
                                                    } else {
                                                        if (noMinusEntityX - noMinusPlayerX >= 1) {
                                                            if (noMinusEntityX - noMinusPlayerX <= 6) {
                                                                Vector velocity = new Vector(0, 0.85, 0);
                                                                entity.setVelocity(velocity);
                                                            }
                                                        }
                                                    }
                                                    //east
                                                } else if (playerX - entityX >= 1) {
                                                    if (playerX - entityX <= 6) {
                                                        Vector velocity = new Vector(0, 0.85, 0);
                                                        entity.setVelocity(velocity);
                                                    }
                                                    //wast
                                                } else if (noMinusEntityX - noMinusPlayerX >= 1) {
                                                    if (noMinusEntityX - noMinusPlayerX <= 6) {
                                                        Vector velocity = new Vector(0, 0.85, 0);
                                                        entity.setVelocity(velocity);
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }, 0L, 0L);
    }
}
