package kr.co.skeleton.zombieapocalypse.randomSpawn;

import net.minecraft.server.v1_16_R3.*;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.craftbukkit.v1_16_R3.CraftWorld;

import java.util.Arrays;
import java.util.List;


public class testZombie /*extends*/ /*EntityZombie*/ {
//    public testZombie(Location loc) {
//        super(EntityTypes.ZOMBIE, ((CraftWorld)loc.getWorld()).getHandle());
//        this.setPosition(loc.getX(), loc.getY(), locZ());
//
//        this.setHealth(1000000);
//        this.setCustomNameVisible(true);
//        this.setCustomName(new ChatComponentText(ChatColor.translateAlternateColorCodes('&', "&4test")));
//
//        spawnMembers(loc);
//    }
//
//    public void initPathfinder() {
//        super.initPathfinder();
//        this.goalSelector.a(1, new PathfinderGoalNearestAttackableTarget<EntityHuman>(this, EntityHuman.class, true));
//        this.goalSelector.a(1, new PathfinderGoalNearestAttackableTarget<testZombie>(this, testZombie.class, false));
//    }
//
//    private void spawnMembers(Location loc) {
//        List<spwanMembers> pack = Arrays.asList(new spwanMembers(loc, "test1"), new spwanMembers(loc, "test2"), new spwanMembers(loc, "test3"), new spwanMembers(loc, "test4"));
//
//        WorldServer world = ((CraftWorld)loc.getWorld()).getHandle();
//        for (spwanMembers spawn : pack) {
//            world.addEntity(spawn);
//        }
//    }
}
