package cx.rain.classicui.gui.widget.base;

import cx.rain.classicui.utility.ContentAlign;
import cx.rain.classicui.utility.Location;
import cx.rain.classicui.utility.Size;
import net.minecraft.network.chat.Component;

public interface ITitled {
    public Component getTitle();

    public void setTitle(Component title);

    public boolean isTitleVisible();

    public void setTitleVisible(boolean titleVisible);

    public Location getTitleLocation();

    public Size getTitleSize();

    public ContentAlign getTitleAlign();
}
