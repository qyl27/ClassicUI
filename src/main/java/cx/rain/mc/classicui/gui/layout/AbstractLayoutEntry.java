package cx.rain.mc.classicui.gui.layout;

public abstract class AbstractLayoutEntry {

    protected String name;
    protected int x;
    protected int y;
    protected int width;
    protected int height;

    public AbstractLayoutEntry(String name, int x, int y, int width, int height) {
        this.name = name;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    public abstract String getType();

    public String getName() {
        return name;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }
}
