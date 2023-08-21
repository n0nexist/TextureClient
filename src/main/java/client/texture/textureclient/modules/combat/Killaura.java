package client.texture.textureclient.modules.combat;

import client.texture.textureclient.modules.TextureCategory;
import client.texture.textureclient.modules.TextureModule;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.entity.Entity;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;

import java.util.ArrayList;
import java.util.Comparator;

public class Killaura extends TextureModule {

    public static float reach = 7.5f;

    public static int maxTargets = 4;

    public static boolean autoBlock = false;

    public Killaura(){
        super("Killaura", "", TextureCategory.COMBAT, "r");
    }


    @Override
    public void onUpdate(){
        this.suffix = reach+"f | "+maxTargets;

        if (mc.player == null || mc.interactionManager == null){return;}



        for (Entity x : findNearestEntities(maxTargets)){
            if (autoBlock){
                KeyBinding.onKeyPressed(mc.options.useKey.getDefaultKey());
            }
            mc.player.swingHand(Hand.MAIN_HAND);
            mc.interactionManager.attackEntity(mc.player, x);
        }
    }

    public static ArrayList<Entity> findNearestEntities(int N) {
        ArrayList<Entity> nearestEntities = new ArrayList<>();

        if (mc.world != null && mc.player != null) {
            BlockPos playerPos = mc.player.getBlockPos();
            for (Entity entity : mc.world.getEntities()) {
                BlockPos entityPos = entity.getBlockPos();
                double distanceSq = playerPos.getSquaredDistance(entityPos.getX(), entityPos.getY(), entityPos.getZ());
                if (nearestEntities.size() < N) {
                    if (playerPos.getSquaredDistance(entity.getPos())<=reach
                            && entity.getUuid()!=mc.player.getUuid()
                            && (entity instanceof PlayerEntity || entity instanceof MobEntity)){
                        nearestEntities.add(entity);
                    }
                    nearestEntities.sort(Comparator.comparingDouble(e -> playerPos.getSquaredDistance(e.getBlockPos())));
                } else if (distanceSq < playerPos.getSquaredDistance(nearestEntities.get(N - 1).getBlockPos())) {
                    nearestEntities.remove(N - 1);
                    if (playerPos.getSquaredDistance(entity.getPos())<=reach
                            && entity.getUuid()!=mc.player.getUuid()
                            && (entity instanceof PlayerEntity || entity instanceof MobEntity)){
                        nearestEntities.add(entity);
                    }
                    nearestEntities.sort(Comparator.comparingDouble(e -> playerPos.getSquaredDistance(e.getBlockPos())));
                }
            }
        }
        return nearestEntities;
    }



}
