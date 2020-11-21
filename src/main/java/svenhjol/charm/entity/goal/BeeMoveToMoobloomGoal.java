package svenhjol.charm.entity.goal;

import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.passive.BeeEntity;
import net.minecraft.util.math.Box;
import net.minecraft.world.World;
import svenhjol.charm.entity.MoobloomEntity;
import svenhjol.charm.mixin.accessor.BeeEntityAccessor;

import java.util.List;
import java.util.function.Predicate;

public class BeeMoveToMoobloomGoal extends Goal {
    private final BeeEntity bee;
    private final World world;
    private MoobloomEntity moobloom = null;
    private int ticks;
    private int range = 24;
    private int maxTicks = 1200;

    public BeeMoveToMoobloomGoal(BeeEntity bee) {
        this.bee = bee;
        this.world = bee.world;
    }

    @Override
    public boolean canStart() {
        if (bee.hasNectar())
            return true;

        return false;
    }

    @Override
    public void start() {
        ticks = 0;
        bee.resetPollinationTicks();

        Box box = bee.getBoundingBox().expand(range, range / 2.0, range);
        Predicate<MoobloomEntity> selector = entity -> !entity.isPollinated();
        List<MoobloomEntity> entities = world.getEntitiesByClass(MoobloomEntity.class, box, selector);

        if (entities.size() > 0) {
            moobloom = entities.get(world.random.nextInt(entities.size()));
            bee.setCannotEnterHiveTicks(maxTicks);
        }

        super.start();
    }

    @Override
    public void stop() {
        ticks = 0;
        moobloom = null;
        bee.getNavigation().stop();
        bee.getNavigation().resetRangeMultiplier();
    }

    @Override
    public boolean shouldContinue() {
        return moobloom != null && ticks < maxTicks;
    }

    @Override
    public void tick() {
        ticks++;

        if (moobloom == null)
            return;

        if (ticks > maxTicks) {
            moobloom = null;
        } else if (!bee.getNavigation().isFollowingPath()) {
            ((BeeEntityAccessor) bee).invokeStartMovingTo(moobloom.getBlockPos());
            bee.addStatusEffect(new StatusEffectInstance(StatusEffects.GLOWING, 100)); // TEMP
        } else {

            // update bee tracking to take into account moving moobloom
            if (ticks % 50 == 0)
                ((BeeEntityAccessor) bee).invokeStartMovingTo(moobloom.getBlockPos());

            double dist = bee.getPos().distanceTo(moobloom.getPos());
            if (dist < 3) {
                ((BeeEntityAccessor)bee).invokeSetHasNectar(false);
                bee.removeStatusEffect(StatusEffects.GLOWING); // TEMP
                moobloom.pollinate();
                moobloom = null;
            }
        }
    }
}
