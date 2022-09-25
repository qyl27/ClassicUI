package cx.rain.classicui.utility;

import com.mojang.blaze3d.vertex.PoseStack;
import cx.rain.classicui.gui.CInventoryScreen;
import cx.rain.classicui.gui.screen.ScreenBase;
import cx.rain.classicui.gui.widget.WLabel;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Style;
import net.minecraft.util.FormattedCharSequence;
import org.jetbrains.annotations.Nullable;

public final class DrawingHelper {
    public static void drawString(PoseStack stack, Font font, FormattedCharSequence text, ContentAlign align, int x, int y) {
        font.draw(stack, text, x, y, WLabel.DEFAULT_TEXT_COLOR.getValue());
    }

    public static void drawHoverText(PoseStack stack, @Nullable Style style, int x, int y) {
        var screen = Minecraft.getInstance().screen;
        if (screen instanceof ScreenBase base) {
            base.renderComponentHoverEffect(stack, style, x, y);
        }
    }
}
