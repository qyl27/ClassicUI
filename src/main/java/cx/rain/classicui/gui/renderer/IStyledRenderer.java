package cx.rain.classicui.gui.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import cx.rain.classicui.config.ClassicUIConfigClient;
import cx.rain.classicui.gui.widget.base.AbstractWidget;
import cx.rain.classicui.gui.widget.slot.WItemSlot;
import cx.rain.classicui.ninepatch.NinePatch;
import cx.rain.classicui.utility.DrawingHelper;

// Todo: qyl27: styled renderer is for the auto gui generator.
public interface IStyledRenderer {
    public static final IStyledRenderer VANILLA = new VanillaStyledRenderer();
    public static final IStyledRenderer DARK = new DarkStyledRenderer();

    public NinePatch getItemSlotTexture();

    public default IStyledRenderer getRenderer() {
        return ClassicUIConfigClient.DARK_MODE.get() ? DARK : VANILLA;
    }

    public default void renderItemSlotBackground(PoseStack stack, int x, int y, AbstractWidget widget) {
        if (widget instanceof WItemSlot slot) {


        } else {
            DrawingHelper.drawSlot(stack, x - 1, y - 1, widget.getWidth() + 2, widget.getHeight() + 2);
        }
    }
}
