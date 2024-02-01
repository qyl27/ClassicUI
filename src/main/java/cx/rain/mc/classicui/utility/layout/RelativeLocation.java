package cx.rain.mc.classicui.utility.layout;

import org.jetbrains.annotations.Nullable;

import java.util.Objects;

public class RelativeLocation extends Location {
    protected Location parent;

    public RelativeLocation(int relativeX, int relativeY, @Nullable Location parent) {
        super(relativeX, relativeY);

        this.parent = Objects.requireNonNullElse(parent, Location.ZERO);
    }

    @Override
    public int getX() {
        return parent.getX() + x;
    }

    @Override
    public int getY() {
        return parent.getY() + y;
    }

    public int getRelativeX() {
        return x;
    }

    public int getRelativeY() {
        return y;
    }

    public Location getParent() {
        return parent;
    }

    public void setParent(Location parent) {
        this.parent = parent;
    }

    @Override
    public RelativeLocation clone() {
        return new RelativeLocation(x, y, parent);
    }
}
