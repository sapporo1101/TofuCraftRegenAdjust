package io.github.sapporo1101.tofucraftregenadjust;

import net.minecraft.world.entity.vehicle.Minecart;
import net.neoforged.fml.common.Mod;

@Mod(TofuCraftRegenAdjust.MODID)
public class TofuCraftRegenAdjust {
    public static final String MODID = "tofucraftregenadjust";

    public void onInitialize() {
        Minecart.getViewScale();
    }
}
