package austin.lightsabers.item;

import austin.lightsabers.LightsabersSounds;
import austin.lightsabers.enchantments.LightsaberEnchantments;
import austin.lightsabers.utils.util;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.advancement.criterion.Criteria;
import net.minecraft.block.*;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.TntEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.*;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.state.property.Properties;
import net.minecraft.text.Text;
import net.minecraft.util.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.event.GameEvent;

import java.util.function.Consumer;

public class Lightsaber extends LightsaberBase {

    public Lightsaber(int atk) {
        super(LightsaberToolMaterials.KYBER, atk, -2,
                new FabricItemSettings().fireproof().rarity(Rarity.EPIC));

    }

    @Override
    public boolean canRepair(ItemStack stack, ItemStack ingredient) {
        return ingredient.getItem() == LightsaberItems.KYBER_SHARD;
    }

    @Override
    public float getMiningSpeedMultiplier(ItemStack stack, BlockState state) {
        return 12.0f;
    }

    @Override
    public boolean postHit(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        super.postHit(stack, target, attacker);
        //75% chance of setting on fire
        if (attacker.getWorld().getRandom().nextBetween(0, 100) > 75) target.setOnFireFor(5);
        attacker.getWorld().playSound(null, attacker.getBlockPos(), LightsabersSounds.LIGHTSABER_HIT, SoundCategory.PLAYERS, 1,
                attacker.getWorld().getRandom().nextFloat() * .4f + 0.8f);

        return true;
    }

    @Override
    public boolean postMine(ItemStack stack, World world, BlockState state, BlockPos pos, LivingEntity miner) {
        world.playSound(null, pos, LightsabersSounds.LIGHTSABER_HIT, SoundCategory.PLAYERS, 1f, world.random.nextFloat() * 0.4f + 0.8f);
        return super.postMine(stack, world, state, pos, miner);
    }

    @Override
    public UseAction getUseAction(ItemStack stack) {
        return UseAction.BLOCK;
    }

    @Override
    public int getMaxUseTime(ItemStack stack) {
        return 72000;
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        ItemStack itemStack = user.getStackInHand(hand);
        user.setCurrentHand(hand);
        return TypedActionResult.consume(itemStack);
    }

    @Override
    public ActionResult useOnBlock(ItemUsageContext context) {
        PlayerEntity playerEntity = context.getPlayer();
        World world = context.getWorld();
        BlockPos blockPos = context.getBlockPos();
        BlockState blockState = world.getBlockState(blockPos);
        if (!CampfireBlock.canBeLit(blockState) && !CandleBlock.canBeLit(blockState) && !CandleCakeBlock.canBeLit(blockState) &&
        blockState.getBlock() != Blocks.TNT) {
            BlockPos blockPos2 = blockPos.offset(context.getSide());
            if (AbstractFireBlock.canPlaceAt(world, blockPos2, context.getHorizontalPlayerFacing())) {


                world.playSound(null, blockPos2, LightsabersSounds.LIGHTSABER_HIT, SoundCategory.BLOCKS, 1,
                        1f);
                BlockState blockState2 = AbstractFireBlock.getState(world, blockPos2);
                world.setBlockState(blockPos2, blockState2, Block.NOTIFY_ALL | Block.REDRAW_ON_MAIN_THREAD);
                world.emitGameEvent(playerEntity, GameEvent.BLOCK_PLACE, blockPos);
                ItemStack itemStack = context.getStack();
                if (playerEntity instanceof ServerPlayerEntity) {
                    Criteria.PLACED_BLOCK.trigger((ServerPlayerEntity)playerEntity, blockPos2, itemStack);
                    itemStack.damage(1, playerEntity, p -> p.sendToolBreakStatus(context.getHand()));
                }

                return ActionResult.success(world.isClient());
            } else {
                return ActionResult.FAIL;
            }
        } else if (blockState.getBlock() == Blocks.TNT) {
            if (!world.isClient) {
                TntEntity tntEntity = new TntEntity(world, blockPos.getX() + 0.5, blockPos.getY(), blockPos.getZ() + 0.5, context.getPlayer());
                world.spawnEntity(tntEntity);
                world.playSound(null, tntEntity.getX(), tntEntity.getY(), tntEntity.getZ(),
                        SoundEvents.ENTITY_TNT_PRIMED, SoundCategory.BLOCKS, 1.0F, 1.0F);
                world.emitGameEvent(context.getPlayer(), GameEvent.PRIME_FUSE, blockPos);
                world.removeBlock(blockPos, false);
            }

            world.playSound(null, blockPos, LightsabersSounds.LIGHTSABER_HIT, SoundCategory.BLOCKS, 1,
                    1f);
            return ActionResult.success(world.isClient());
        } else {
            //world.playSound(playerEntity, blockPos, SoundEvents.ITEM_FLINTANDSTEEL_USE, SoundCategory.BLOCKS, 1.0F, world.getRandom().nextFloat() * 0.4F + 0.8F);
            world.playSound(null, blockPos, LightsabersSounds.LIGHTSABER_HIT, SoundCategory.BLOCKS, 1,
                    1f);
            world.setBlockState(blockPos, blockState.with(Properties.LIT, true), Block.NOTIFY_ALL | Block.REDRAW_ON_MAIN_THREAD);
            world.emitGameEvent(playerEntity, GameEvent.BLOCK_CHANGE, blockPos);
            if (playerEntity != null) {
                context.getStack().damage(1, playerEntity, p -> p.sendToolBreakStatus(context.getHand()));
            }

            return ActionResult.success(world.isClient());
        }
    }
}
