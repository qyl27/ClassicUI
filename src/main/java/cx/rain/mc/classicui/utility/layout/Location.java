package cx.rain.mc.classicui.utility.layout;

public class Location {

    public static final Location ZERO = new Location(0, 0);

    protected int x;
    protected int y;

    public Location(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void addX(int x) {
        this.x += x;
    }

    public void addY(int y) {
        this.y += y;
    }

    @Override
    public Location clone() {
        return new Location(x, y);
    }
}
