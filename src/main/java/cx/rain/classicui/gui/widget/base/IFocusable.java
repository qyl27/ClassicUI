package cx.rain.classicui.gui.widget.base;

public interface IFocusable {
    public boolean canFocus();

    public boolean isFocused();

    public void onFocusGained();

    public void onFocusLost();
}
