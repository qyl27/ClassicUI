package cx.rain.classicui.gui.widget.base;

import cx.rain.classicui.gui.renderer.IStyledRenderer;

public interface IHasStyledRenderer {
    public IStyledRenderer getStyledRenderer();

    public void setStyledRenderer(IStyledRenderer renderer);

    public boolean hasStyledRenderer();
}
