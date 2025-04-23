package austin.lightsabers.utils;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.advancement.criterion.Criteria;
import net.minecraft.block.*;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.*;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.state.property.Properties;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.Rarity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;
import net.minecraft.world.event.GameEvent;
import org.jetbrains.annotations.Nullable;

public class util {

    public static boolean trySetFireAt(World world, BlockPos targetPos, @Nullable PlayerEntity player,
                                       Direction contextSide, Direction playerFacing, ItemStack usedStack, Hand hand) {
        BlockState blockState = world.getBlockState(targetPos);

        if (!CampfireBlock.canBeLit(blockState) && !CandleBlock.canBeLit(blockState) && !CandleCakeBlock.canBeLit(blockState)) {
            BlockPos offsetPos = targetPos.offset(contextSide);
            if (AbstractFireBlock.canPlaceAt(world, offsetPos, playerFacing)) {
                BlockState fireState = AbstractFireBlock.getState(world, offsetPos);
                world.setBlockState(offsetPos, fireState, Block.NOTIFY_ALL | Block.REDRAW_ON_MAIN_THREAD);
                world.emitGameEvent(player, GameEvent.BLOCK_PLACE, offsetPos);

                if (player instanceof ServerPlayerEntity) {
                    Criteria.PLACED_BLOCK.trigger((ServerPlayerEntity) player, offsetPos, usedStack);
                    usedStack.damage(1, player, p -> p.sendToolBreakStatus(hand));
                }

                return true; // success
            }
        } else {
            world.setBlockState(targetPos, blockState.with(Properties.LIT, true), Block.NOTIFY_ALL | Block.REDRAW_ON_MAIN_THREAD);
            world.emitGameEvent(player, GameEvent.BLOCK_CHANGE, targetPos);

            if (player != null) {
                usedStack.damage(1, player, p -> p.sendToolBreakStatus(hand));
            }

            return true; // success
        }

        return false; // failed
    }

}
