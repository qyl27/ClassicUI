package cx.rain.classicui.utility;

import com.google.common.collect.ImmutableList;
import net.minecraft.util.Tuple;

import java.util.List;

public class SizeLocationHelper {
    public static List<Tuple<Location, Size>> split(Location startLoc, Size startSize, Location contentLoc, Size contentSize) {
        var builder = ImmutableList.<Tuple<Location, Size>>builder();

        // Todo.
//        for (var i = 0; i < 3; i++) {
//            for (var j = 0; j < 3; j++) {
//                var x = switch (i) {
//                    case 0 -> ;
//                    case 1 -> ;
//                    case 2 -> ;
//                };
//
//                var y = switch (i) {
//                    case 0 -> ;
//                    case 1 -> ;
//                    case 2 -> ;
//                };
//
//                var w = ;
//                var h = ;
//
//                var loc = new Location(x, y);
//                var size = new Size(w, h);
//                builder.add(new Tuple<>(loc, size));
//            }
//        }

        return builder.build();
    }

    public static int halfTheDiff(int a, int b) {
        return (a - b) / 2;
    }
}
