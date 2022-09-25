package cx.rain.classicui.gui.widget;

import com.mojang.blaze3d.vertex.PoseStack;
import cx.rain.classicui.utility.ContentAlign;
import cx.rain.classicui.utility.DrawingHelper;
import cx.rain.classicui.utility.HandleResult;
import cx.rain.classicui.utility.InputHandler;
import net.minecraft.network.chat.Component;
import net.minecraft.util.FormattedCharSequence;

import java.util.ArrayList;
import java.util.List;

/**
 * Multi-lined text control.
 * For single-line text, use {@link WLabel}.
 */
public class WText extends WLabel {
    public static final int DEFAULT_LINE_SPACING = 2;

    protected List<FormattedCharSequence> lines;
    protected int lineSpacing = DEFAULT_LINE_SPACING;
    protected boolean lineWrap = false;

    public WText(Component text) {
        super(text);
        wrapLines();
    }

    public WText(Component text, int x, int y) {
        this(text);
        setLocation(x, y);
        resetTextSize();
    }

    public WText(Component text, int x, int y, ContentAlign align) {
        this(text, x, y);
        contentAlign = align;
    }

    public WText(Component text, int x, int y, ContentAlign align, int spacing) {
        this(text, x, y, align);
        lineSpacing = spacing;
    }

    public WText(Component text, int x, int y, ContentAlign align, int spacing, int width, int height) {
        this(text, x, y, align, spacing);
        setSize(width, height);
    }

    public WText(Component text, int x, int y, ContentAlign align, int spacing, int width, int height, boolean autoLineWrap) {
        this(text, x, y, align, spacing, width, height);
        lineWrap = autoLineWrap;
        wrapLines();
    }

    @Override
    public void resetTextSize() {
        var widest = 0;
        for (var line : lines) {
            var lineWidth = font.width(line);
            if (widest < lineWidth) {
                widest = lineWidth;
            }
        }

        setSize(widest, getHeightWithSpacing());
    }

    public int getHeightWithSpacing() {
        if (lines == null) {
            wrapLines();
        }

        return font.lineHeight * lines.size() + lineSpacing * (lines.size() - 1);
    }

    @Override
    public void setSize(int w, int h) {
        super.setSize(w, h);

        wrapLines();
    }

    public void setLineSpacing(int spacing) {
        lineSpacing = spacing;
    }

    public int getLineSpacing() {
        return lineSpacing;
    }

    public void wrapLines() {
        var result = new ArrayList<FormattedCharSequence>();
        var componentLines = textComponent.toFlatList();

        for (var line : componentLines) {
            if (lineWrap) {
                result.addAll(font.split(textComponent, width));
            } else {
                result.add(line.getVisualOrderText());
            }
        }

        lines = result;
    }

    @Override
    public boolean isMultiline() {
        return true;
    }

    @Override
    protected void render(PoseStack stack, int x, int y, int mouseX, int mouseY) {
        if (lines == null) {
            wrapLines();
        }

        var yOffset = switch (contentAlign.vertical()) {
            case TOP -> 0;
            case CENTER -> getHeight() / 2 - getHeightWithSpacing() / 2;
            case BOTTOM -> getHeight() - getHeightWithSpacing();
        };

        for (var line : lines) {
            var xOffset = switch (contentAlign.horizontal()) {
                case LEFT -> 0;
                case MIDDLE -> getWidth() / 2 - font.width(line) / 2;
                case RIGHT -> getWidth() - font.width(line);
            };

            DrawingHelper.drawString(stack, font, line, contentAlign, x + xOffset, y + yOffset);
        }

        var style = getTextStyleAt(mouseX, mouseY);
        DrawingHelper.drawHoverText(stack, style, x + mouseX, y + mouseY);
    }

    protected InputHandler.Click clickHandler = (x, y, button) -> {
        if (button != 0) {
            return HandleResult.IGNORED;
        }

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

    @Override
    public void setText(Component text) {
        super.setText(text);

        wrapLines();
    }
}
