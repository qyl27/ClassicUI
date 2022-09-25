package cx.rain.classicui.gui.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.resources.ResourceLocation;

// Todo: qyl27: styled renderer is for the auto gui generator.
public interface StyledRenderer {
    public static final StyledRenderer VANILLA = new VanillaStyledRenderer();

    public ResourceLocation getItemSlotTexture();

    public default void renderItemSlotBackground(PoseStack stack, int x, int y) {

    }
}
