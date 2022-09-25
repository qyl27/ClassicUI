package cx.rain.classicui.gui.host;

import cx.rain.classicui.gui.widget.base.AbstractWidget;

public interface IFocusableHolder {
    public AbstractWidget getFocused();

    public void setFocused(AbstractWidget widget);

    public boolean isFocused(AbstractWidget widget);

    public void requestFocus(AbstractWidget widget);

    public void releaseFocus(AbstractWidget widget);

    public AbstractWidget tabFocus();
}
