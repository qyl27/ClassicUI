package cx.rain.classicui.gui.renderer;

import cx.rain.classicui.ClassicUI;
import net.minecraft.resources.ResourceLocation;

public class VanillaStyledRenderer implements StyledRenderer {
    // Todo: qyl27: add nine patch slot texture.
    public static final ResourceLocation ITEM_SLOT = new ResourceLocation(ClassicUI.MODID, "textures/gui/widgets/item_slot.png");

    @Override
    public ResourceLocation getItemSlotTexture() {
        return ITEM_SLOT;
    }
}
