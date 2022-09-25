package cx.rain.classicui.utility;

public record Location(int x, int y) {
    public static final Location ZERO = new Location(0, 0);
}
