package com.virbound.magmania.items;

import com.virbound.magmania.Magmania;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ai.control.LookControl;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.damage.DamageSources;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.FireballEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;
import net.minecraft.world.entity.EntityChangeListener;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class MagmaOrb extends Item {
    public MagmaOrb(Item.Settings settings) {
        super(settings);
    }

    // Tooltip
    @Override
    public void appendTooltip(ItemStack stack, @Nullable World world, List<Text> tooltip, TooltipContext context) {
        tooltip.add(Text.translatable("itemTooltip.magmania.magma_orb").formatted(Formatting.GRAY));
    }

    // Functionality
    @Override
    public void inventoryTick(ItemStack stack, World world, Entity entity, int slot, boolean selected)  {
        if (entity.isPlayer()) {
            entity.setOnFireFor(1);
        }
    }
}
