package com.chromanyan.chromagadgets.init;

import com.chromanyan.chromagadgets.ChromaGadgets;
import com.chromanyan.chromagadgets.items.*;
import net.minecraft.world.item.Item;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

@SuppressWarnings("unused")
public class ModItems {
    public static final DeferredRegister<Item> ITEMS_REGISTRY = DeferredRegister.create(ForgeRegistries.ITEMS, ChromaGadgets.MODID);

    public static final RegistryObject<Item> SCULKOMETER = ITEMS_REGISTRY.register("sculkometer", ItemSculkometer::new);
    public static final RegistryObject<Item> WHITE_FLAG = ITEMS_REGISTRY.register("white_flag", ItemWhiteFlag::new);
    public static final RegistryObject<Item> SHRIEKER_HORN = ITEMS_REGISTRY.register("shrieker_horn", ItemShriekerHorn::new);
    public static final RegistryObject<Item> ARCANE_REROLL = ITEMS_REGISTRY.register("arcane_reroll", ItemArcaneReroll::new);
    public static final RegistryObject<Item> MOSSY_MIRROR = ITEMS_REGISTRY.register("mossy_mirror", ItemMossyMirror::new);
}
