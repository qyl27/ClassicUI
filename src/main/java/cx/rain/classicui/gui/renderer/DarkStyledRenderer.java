package cx.rain.classicui.gui.renderer;

import cx.rain.classicui.ClassicUI;
import cx.rain.classicui.ninepatch.NinePatch;
import net.minecraft.resources.ResourceLocation;

public class DarkStyledRenderer implements IStyledRenderer {
    // To replace textures for dark mode.
    public static final ResourceLocation RESOURCE_LOCATION_ITEM_SLOT = new ResourceLocation(ClassicUI.MODID, "textures/gui/widgets/item_slot.9.png");

    public static final NinePatch ITEM_SLOT = NinePatch.from(RESOURCE_LOCATION_ITEM_SLOT);

    @Override
    public NinePatch getItemSlotTexture() {
        return ITEM_SLOT;
    }
}
