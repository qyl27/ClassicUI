package cx.rain.classicui.gui.widget.base;

import com.mojang.blaze3d.vertex.PoseStack;
import cx.rain.classicui.gui.host.IGuiHost;
import cx.rain.classicui.utility.CheckedWidgetList;
import cx.rain.classicui.utility.Padding;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public abstract class AbstractCanvas extends AbstractWidget {
    protected final List<AbstractWidget> children = new CheckedWidgetList(this);

    public List<AbstractWidget> getChildren() {
        return children;
    }

    public void remove(AbstractWidget widget) {
        getChildren().remove(widget);
    }

    @Override
    public boolean canResize() {
        return true;
    }

    /**
     * The layout rules of the canvas.
     */
    public void layout() {
        for (var child : getChildren()) {
            if (child instanceof AbstractCanvas canvas) {
                canvas.layout();
            }

            expandToFit(child);
        }
    }

    public void expandToFit(AbstractWidget widget) {
        expandToFit(widget, Padding.NONE);
    }

    public void expandToFit(AbstractWidget widget, Padding padding) {
        var expendedWidth = widget.getX() + widget.getWidth() + padding.right();
        var expendedHeight = widget.getY() + widget.getHeight() + padding.bottom();

        setSize(Math.max(getWidth(), expendedWidth), Math.max(getHeight(), expendedHeight));
    }

    @Override
    public AbstractWidget hitChild(int x, int y) {
        var children = getChildren();

        if (!children.isEmpty())  {
            for (var i = children.size() - 1; i >= 0; i--) {
                var child = children.get(i);
                var widgetX = x - child.getX();
                var widgetY = y - child.getY();
                if (child.isInside(widgetX, widgetY)) {
                    return child.hitChild(widgetX, widgetY);
                }
            }
        }

        return this;
    }

    @Override
    public void onShown() {
        super.onShown();

        for (var child : getChildren()) {
            child.onShown();
        }
    }

    @Override
    public void onHidden() {
        super.onHidden();

        for (var child : getChildren()) {
            child.onHidden();
        }
    }

    @Override
    public AbstractWidget tabFocus(boolean backward) {
        return tabFocus(backward, null);
    }

    /**
     * Circle focus in this widget. (Like using tab.)
     * If widget is not focusable, returns null.
     * @param backward If true, direction is backward. (Like using shift + tab.)
     * @param cursor Cursor for cycling.
     * @return The next focused widget, or null if no other widget can focus.
     */
    public AbstractWidget tabFocus(boolean backward, @Nullable AbstractWidget cursor) {
        AbstractWidget result = null;
        if (cursor == null) {
            if (!backward) {
                for (var child : getChildren()) {
                    result = checkTabFocus(false, child);
                }
            } else {
                var children = getChildren();
                for (var i = children.size(); i > 0; i--) {
                    var child = children.get(i - 1);
                    result = checkTabFocus(true, child);
                }
            }
        } else {
            var index = getChildren().indexOf(cursor);

            if (index == -1) {  // qyl27: Widget is not belong to this canvas.
                index = backward ? getChildren().size() - 1 : 0;    // qyl27: Set cursor index by forward or not.
            }

            if (!backward) {
                if (index < children.size() - 1) {
                    for (int i = index + 1; i < children.size(); i++) {
                        var child = children.get(i - 1);
                        result = checkTabFocus(false, child);
                    }
                }
            } else {
                for (int i = index; i > 0; i--) {
                    var child = children.get(i - 1);
                    result = checkTabFocus(true, child);
                }
            }
        }

        return result;
    }

    private AbstractWidget checkTabFocus(boolean backward, AbstractWidget child) {
        if (child.canFocus() || child instanceof AbstractCanvas) {
            return child.tabFocus(backward);
        }

        return null;
    }

    @Override
    public void setHost(IGuiHost host) {
        super.setHost(host);

        for (var child : getChildren()) {
            child.setHost(host);
        }
    }

    @Override
    public void tick() {
        super.tick();

        for (var child : getChildren()) {
            child.tick();
        }
    }

    @Override
    protected void render(PoseStack stack, int x, int y, int mouseX, int mouseY) {
        super.render(stack, x, y, mouseX, mouseY);

        for (var child : getChildren()) {
            child.render(stack, x + child.getX(), y + child.getY(), mouseX, mouseY);
        }
    }
}
