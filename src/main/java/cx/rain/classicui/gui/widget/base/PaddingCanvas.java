package cx.rain.classicui.gui.widget.base;

import cx.rain.classicui.utility.Padding;

public abstract class PaddingCanvas extends AbstractCanvas {
    protected Padding canvasPadding = Padding.NONE;

    public Padding getPadding() {
        return canvasPadding;
    }

    public PaddingCanvas setPadding(Padding padding) {
        var prevPadding = getPadding();

        setSize(getWidth() - prevPadding.left() - prevPadding.right(),
                getHeight() - prevPadding.top() - prevPadding.bottom());

        for (var child : getChildren()) {
            child.setLocation(child.getX() - prevPadding.left(), child.getY() - prevPadding.top());
            expandToFit(child, padding);
        }

        canvasPadding = padding;

        return this;
    }
}
