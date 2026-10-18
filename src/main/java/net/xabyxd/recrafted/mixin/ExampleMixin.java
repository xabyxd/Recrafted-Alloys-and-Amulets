package net.xabyxd.recrafted.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import net.minecraft.client.gui.GuiMainMenu;
import net.xabyxd.recrafted.utils.LogHelper;

@Mixin(GuiMainMenu.class)
abstract class ExampleMixin {
    
    @Inject(method="initGui()V", at=@At("HEAD"))
    private void init(CallbackInfo info) {
        LogHelper.info("[INFO] This line is printed by the LOGGER from 'ExampleMixin.java'.");
    }
}