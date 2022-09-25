package cx.rain.classicui.gui.widget.text;

import com.mojang.blaze3d.vertex.PoseStack;
import cx.rain.classicui.gui.widget.base.AbstractWidget;
import cx.rain.classicui.utility.ContentAlign;
import cx.rain.classicui.utility.DrawingHelper;
import cx.rain.classicui.utility.HandleResult;
import cx.rain.classicui.utility.InputHandler;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.narration.NarratedElementType;
import net.minecraft.client.gui.narration.NarrationElementOutput;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.Style;
import net.minecraft.network.chat.TextColor;
import org.jetbrains.annotations.NotNull;

/**
 * Single-line GUI widget.
 * For multi-line text, use {@link WText}.
 */
public class WLabel extends AbstractWidget {    // Todo: qyl27: add styled renderer.
    protected Component textComponent;
    protected ContentAlign contentAlign = new ContentAlign(ContentAlign.Horizontal.LEFT, ContentAlign.Vertical.TOP);

    public static final TextColor DEFAULT_TEXT_COLOR = TextColor.parseColor("#404040");

    protected Font font = getMinecraft().font;

    public WLabel(Component text) {
        textComponent = text;
        setLocation(0, 0);
        setSize(font.width(text), font.lineHeight);
    }

    public WLabel(Component text, int x, int y) {
        this(text);
        setLocation(x, y);
        resetTextSize();
    }

    public WLabel(Component text, int x, int y, ContentAlign align) {
        this(text, x, y);
        contentAlign = align;
    }

    public WLabel(Component text, int x, int y, ContentAlign align, int width, int height) {
        this(text, x, y, align);
        setSize(width, height);
    }

    public Component getText() {
        return textComponent;
    }

    public void setText(Component text) {
        textComponent = text;
        resetTextSize();
    }

    public ContentAlign getAlign() {
        return contentAlign;
    }

    public void setAlign(ContentAlign align) {
        contentAlign = align;
    }

    public int getTextWidth() {
        return font.width(textComponent);
    }

    public void resetTextSize() {
        setSize(font.width(textComponent), font.lineHeight);
    }

    public boolean isMultiline() {
        return false;
    }

    @Override
    public boolean canResize() {
        return true;
    }

    @Override
    public void setSize(int w, int h) {
        super.setSize(w, Math.max(8, h));
    }

    @Override
    protected void render(PoseStack stack, int x, int y, int mouseX, int mouseY) {
        super.render(stack, x, y, mouseX, mouseY);

        var xOffset = switch (contentAlign.horizontal()) {
            case LEFT -> 0;
            case MIDDLE -> getWidth() / 2 - getTextWidth() / 2;
            case RIGHT -> getWidth() - getTextWidth();
        };

        var yOffset = switch (contentAlign.vertical()) {
            case TOP -> 0;
            case CENTER -> getHeight() / 2 - font.lineHeight / 2;
            case BOTTOM -> getHeight() - font.lineHeight;
        };

        DrawingHelper.drawString(stack, font, textComponent.getVisualOrderText(), contentAlign,
                x + xOffset, y + yOffset);

        var style = getTextStyleAt(x, y);
        DrawingHelper.drawHoverText(stack, style, x + mouseX, y + mouseY);
    }

    protected InputHandler.Click clickHandler = (x, y, button) -> {
        var style = getTextStyleAt(x, y);
        if (style != null) {
            var screen = getMinecraft().screen;
            if (screen != null) {
                return HandleResult.of(screen.handleComponentClicked(style));
            }
        }

        return HandleResult.IGNORED;
    };

    public void setClickHandler(InputHandler.Click click) {
        clickHandler = click;
    }

    @Override
    public HandleResult onMouseClick(int x, int y, int button) {
        return clickHandler.onClick(x, y, button);
    }

    public Style getTextStyleAt(int x, int y) {
        if (isInside(x, y)) {
            return getMinecraft().font.getSplitter().componentStyleAtWidth(textComponent, x);
        }
        return null;
    }

    @Override
    public void updateNarration(@NotNull NarrationElementOutput output) {
        output.add(NarratedElementType.TITLE, textComponent);
    }
}
