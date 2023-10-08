package com.tardisgallifrey.blockmod.Init;

import com.tardisgallifrey.blockmod.BlockModMain;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import org.jetbrains.annotations.NotNull;

import static com.tardisgallifrey.blockmod.Init.BlockInit.SMILEBLOCK;

//This is the Item Init(ialization) class
//There can be one for Blocks and
// ones for other Entities


public class ItemInit {

    //no constructor in this class

    //Basic DeferredRegister of an Item
    //We defer registry until it's called in
    //minecraft.

    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS,
                    BlockModMain.MOD_ID);


   //For Blocks, using the auto-register of Block Items
    //in the BlockInit class,
    //You need a ModCreativeTab method for each
    //block item you register this way.
    //They will have to have new names, not ModCreativeTab

    //This is just to show the block in Creative Mode
    //It does nothing else.

    public static class ModCreativeTab extends CreativeModeTab {
        private ModCreativeTab(int index, String label) {

            super(index, label);
        }

        @Override
        public @NotNull ItemStack makeIcon() {

            return new ItemStack(SMILEBLOCK.get());
        }

        //This creates an instance of the above
        //creative mode tab
        public static final ModCreativeTab instance =
                new ModCreativeTab(CreativeModeTab
                        .TABS.length, "blockmod");
    }
}
