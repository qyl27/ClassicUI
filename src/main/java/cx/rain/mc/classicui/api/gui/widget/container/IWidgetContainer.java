package cx.rain.mc.classicui.api.gui.widget.container;

import cx.rain.mc.classicui.api.gui.widget.ICWidget;

import java.util.List;

public interface IWidgetContainer {
    void addChild(ICWidget widget);
    void removeChild(ICWidget widget);

    List<ICWidget> getChildren();
    void clearChild();
}
