package cx.rain.mc.classicui.api.gui.widget;

import cx.rain.mc.classicui.api.gui.widget.narration.INarrationProvider;
import cx.rain.mc.classicui.api.gui.render.IRenderable;
import cx.rain.mc.classicui.utility.layout.Location;
import cx.rain.mc.classicui.utility.layout.Size;

public interface ICWidget extends IRenderable, INarrationProvider {
    Location getLocation();

    Size getSize();
}
