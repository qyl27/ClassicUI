package cx.rain.mc.classicui.api.gui.widget;

import cx.rain.mc.classicui.api.gui.render.GuiDrawing;
import cx.rain.mc.classicui.utility.layout.Align;
import cx.rain.mc.classicui.utility.layout.Location;
import cx.rain.mc.classicui.utility.layout.RelativeLocation;
import cx.rain.mc.classicui.utility.layout.Size;
import cx.rain.mc.classicui.utility.render.Color;
import net.minecraft.network.chat.Component;
import org.jetbrains.annotations.Nullable;

import java.util.Objects;

public class CLabel extends CAbstractWidget {
    protected final Component content;
    protected final Color color;
    protected final Align.Horizontal horizontalAlign;

    public CLabel(Location location, Size size, Component content) {
        this(location, size, content, Color.WHITE, Align.Horizontal.LEFT);
    }

    public CLabel(Location location, Size size, Component content,
                  @Nullable Color color) {
        this(location, size, content, color, Align.Horizontal.LEFT);
    }

    public CLabel(Location location, Size size, Component content,
                  @Nullable Color color, @Nullable Align.Horizontal horizontalAlign) {
        super(location, size);

        this.content = content;
        this.color = Objects.requireNonNullElse(color, Color.WHITE);
        this.horizontalAlign = Objects.requireNonNullElse(horizontalAlign, Align.Horizontal.LEFT);
    }

    @Override
    public void render(GuiDrawing drawing, int mouseX, int mouseY, float partialTick) {
        var loc = new RelativeLocation(0, 0, location);

        switch (horizontalAlign) {
            case LEFT -> {}
            case MIDDLE -> loc.setY(-drawing.getFont().width(content) / 2);
            case RIGHT -> loc.setY(-drawing.getFont().width(content));
        }

        drawing.string(content, loc, color);
    }

    @Override
    public Component getNarration() {
        return content;
    }
}
