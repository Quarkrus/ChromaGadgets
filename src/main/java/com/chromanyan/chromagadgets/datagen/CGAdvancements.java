package com.chromanyan.chromagadgets.datagen;

import com.chromanyan.chromagadgets.ChromaGadgets;
import com.chromanyan.chromagadgets.init.ModItems;
import com.chromanyan.chromagadgets.triggers.DegravelerVeinTrigger;
import net.minecraft.advancements.Advancement;
import net.minecraft.advancements.CriterionTriggerInstance;
import net.minecraft.advancements.FrameType;
import net.minecraft.advancements.critereon.EntityPredicate;
import net.minecraft.advancements.critereon.InventoryChangeTrigger;
import net.minecraft.advancements.critereon.ItemPredicate;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.advancements.AdvancementProvider;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.ItemLike;
import net.minecraftforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.NotNull;

import java.util.function.Consumer;

public class CGAdvancements extends AdvancementProvider {
    public CGAdvancements(DataGenerator generatorIn, ExistingFileHelper fileHelperIn) {
        super(generatorIn, fileHelperIn);
    }

    private Advancement.Builder displayedHasItemBase(ItemLike itemLike, FrameType frameType) {
        return Advancement.Builder.advancement()
                .addCriterion("has_item", hasItem(itemLike))
                .display(
                        itemLike,
                        Component.translatable("advancement.chromagadgets." + itemLike + ".title"),
                        Component.translatable("advancement.chromagadgets." + itemLike + ".description"),
                        null,
                        frameType,
                        true,
                        true,
                        false
                );
    }

    @SuppressWarnings("unused")
    private Advancement displayedHasItem(ItemLike itemLike, Consumer<Advancement> consumer, ExistingFileHelper fileHelper, FrameType frameType, ResourceLocation parent) {
        return displayedHasItemBase(itemLike, frameType)
                .parent(parent)
                .save(consumer, new ResourceLocation(ChromaGadgets.MODID, itemLike.toString()), fileHelper);
    }

    @Override
    @SuppressWarnings("unused") // most of these are considered unused by intellij
    protected void registerAdvancements(@NotNull Consumer<Advancement> consumer, @NotNull ExistingFileHelper fileHelper) {
        CompoundTag charged = new CompoundTag();
        charged.putBoolean("charged", true);

        Advancement chargedMossyMirror = Advancement.Builder.advancement()
                .addCriterion("charged", hasItemWithTag(ModItems.MOSSY_MIRROR.get(), charged))
                .display(
                        ModItems.MOSSY_MIRROR.get(),
                        Component.translatable("advancement.chromagadgets.charged_mossy_mirror.title"),
                        Component.translatable("advancement.chromagadgets.charged_mossy_mirror.description"),
                        null,
                        FrameType.GOAL,
                        true,
                        true,
                        false
                )
                .parent(new ResourceLocation("adventure/avoid_vibration"))
                .save(consumer, new ResourceLocation(ChromaGadgets.MODID, "charged_mossy_mirror"), fileHelper);

        Advancement degraveler5 = Advancement.Builder.advancement()
                .addCriterion("vein_5", new DegravelerVeinTrigger.TriggerInstance(EntityPredicate.Composite.ANY, 5))
                .display(
                        ModItems.DEGRAVELER.get(),
                        Component.translatable("advancement.chromagadgets.degraveler_5.title"),
                        Component.translatable("advancement.chromagadgets.degraveler_5.description"),
                        null,
                        FrameType.TASK,
                        true,
                        true,
                        false
                )
                .parent(new ResourceLocation("story/iron_tools"))
                .save(consumer, new ResourceLocation(ChromaGadgets.MODID, "degraveler_5"), fileHelper);

        Advancement degraveler250 = Advancement.Builder.advancement()
                .addCriterion("vein_250", new DegravelerVeinTrigger.TriggerInstance(EntityPredicate.Composite.ANY, 250))
                .display(
                        ModItems.DEGRAVELER.get(),
                        Component.translatable("advancement.chromagadgets.degraveler_250.title"),
                        Component.translatable("advancement.chromagadgets.degraveler_250.description"),
                        null,
                        FrameType.CHALLENGE,
                        true,
                        true,
                        false
                )
                .parent(degraveler5)
                .save(consumer, new ResourceLocation(ChromaGadgets.MODID, "degraveler_250"), fileHelper);
    }

    private CriterionTriggerInstance hasItem(ItemLike item) {
        return InventoryChangeTrigger.TriggerInstance.hasItems(ItemPredicate.Builder.item().of(item).build());
    }

    private CriterionTriggerInstance hasItemWithTag(ItemLike item, CompoundTag tag) {
        return InventoryChangeTrigger.TriggerInstance.hasItems(ItemPredicate.Builder.item().of(item).hasNbt(tag).build());
    }
}
