package cx.rain.mc.classicui.api.gui.widget;

import cx.rain.mc.classicui.utility.layout.Location;
import cx.rain.mc.classicui.utility.layout.Size;

public abstract class CAbstractWidget implements ICWidget {
    protected Location location;
    protected Size size;

    public CAbstractWidget(Location location, Size size) {
        this.location = location;
        this.size = size;
    }

    @Override
    public Location getLocation() {
        return location;
    }

    @Override
    public Size getSize() {
        return size;
    }
}
