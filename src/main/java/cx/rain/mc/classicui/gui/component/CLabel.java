package cx.rain.mc.classicui.gui.component;

import cx.rain.mc.classicui.utility.layout.Align;
import cx.rain.mc.classicui.utility.render.Color;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.narration.NarratedElementType;
import net.minecraft.client.gui.narration.NarrationElementOutput;
import net.minecraft.network.chat.Component;
import org.jetbrains.annotations.Nullable;

import java.util.Objects;

public class CLabel extends AbstractComponent {
    protected boolean shadow;
    protected Color color;
    protected Align.Horizontal horizontalAlign;

    public CLabel(int x, int y, int width, int height, Component message, boolean shadow,
                  @Nullable Color color, @Nullable Align.Horizontal horizontalAlign) {
        super(x, y, width, height, message);
        this.shadow = shadow;
        this.color = Objects.requireNonNullElse(color, Color.WHITE);
        this.horizontalAlign = Objects.requireNonNullElse(horizontalAlign, Align.Horizontal.LEFT);
    }

    @Override
    protected void renderWidget(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTick) {
        var x = 0;
        var y = getY();

        var font = getMinecraft().font;
        var message = getMessage();

        switch (horizontalAlign) {
            case LEFT -> x = getX();
            case MIDDLE -> x = -font.width(message) / 2;
            case RIGHT -> x = -font.width(message);
        }

        guiGraphics.drawString(font, message, x, y, color.toPackedARGB(), !shadow);
    }

    @Override
    protected void updateWidgetNarration(NarrationElementOutput narrationElementOutput) {
        narrationElementOutput.add(NarratedElementType.TITLE, getMessage());
    }
}
