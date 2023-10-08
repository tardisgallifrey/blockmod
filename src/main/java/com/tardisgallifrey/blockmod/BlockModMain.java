package com.tardisgallifrey.blockmod; //our mod java package

//Imports needed for classes used below
import com.tardisgallifrey.blockmod.Init.BlockInit;
import com.tardisgallifrey.blockmod.Init.ItemInit;
import com.tardisgallifrey.blockmod.Init.TileEntityInit;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;


//Class annotator to set this class
//as a Forge Modded class
@Mod(BlockModMain.MOD_ID)
public class BlockModMain {

    //We should declare a logger here.
    //The base one doesn't work right, so we'll do it later.

    //set MOD_ID variable for above.
    //Must be same as what is in TOML and Build.Gradle
    public static final String MOD_ID = "blockmod";

    //Constructor establishes event bus from the
    //Java Mod Loader
    //then adds an event listener, and
    //adds our ITEMS created in ItemInit to the
    //event bus
    public BlockModMain() {
        //establish event bus from Forge Java Mod Loader
        final IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        //Add listener to this class
        modEventBus.addListener(this::setup);

        //register our ITEMS to the event bus
        ItemInit.ITEMS.register(modEventBus);

        //Register blocks to the event bus
        BlockInit.BLOCKS.register(modEventBus);

        //Register Block Entity Types
        TileEntityInit.TILE_ENTITY_TYPES.register(modEventBus);

        //Register our event bus?? to MinecraftForge
        //Event bus class
        MinecraftForge.EVENT_BUS.register(this);


    }

    //call setup method with param of
    //event of type FMLCommonSetupEvent
    private void setup(final FMLCommonSetupEvent event) {

    }
}