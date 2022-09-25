package cx.rain.classicui.gui.widget.base;

public interface IShowable {
    public void show();
    public void hide();

    public boolean shouldRender();

    public void onShown();
    public void onHidden();
}
