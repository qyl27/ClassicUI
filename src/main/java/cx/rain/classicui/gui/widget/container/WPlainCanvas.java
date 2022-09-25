package cx.rain.classicui.gui.widget.container;

import cx.rain.classicui.gui.widget.base.AbstractWidget;
import cx.rain.classicui.gui.widget.base.PaddingCanvas;
import cx.rain.classicui.utility.Padding;

public class WPlainCanvas extends PaddingCanvas {
    public void add(AbstractWidget widget, int x, int y) {
        add(widget, x, y, 27, 27);
    }

    public void add(AbstractWidget widget, int x, int y, int width, int height) {
        getChildren().add(widget);
        widget.setParent(this);
        widget.setLocation(getPadding().left() + x, getPadding().top() + y);
        if (widget.canResize()) {
            widget.setSize(width, height);
        }
        expandToFit(widget, getPadding());
    }

//    @Override
//    public WPlainCanvas setPadding(Padding padding) {
//        super.setPadding(padding);
//        return this;
//    }
}
