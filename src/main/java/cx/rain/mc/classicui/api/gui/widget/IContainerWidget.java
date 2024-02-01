package cx.rain.mc.classicui.api.gui.widget;

import java.util.List;

public interface IContainerWidget extends ICWidget {
    void addChild(ICWidget widget);
    void removeChild(ICWidget widget);

    List<ICWidget> getChildren();
    void clearChild();
}
