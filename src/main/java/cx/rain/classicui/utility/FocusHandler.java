package cx.rain.classicui.utility;

import cx.rain.classicui.gui.host.IGuiHost;
import cx.rain.classicui.gui.widget.base.AbstractCanvas;
import cx.rain.classicui.gui.widget.base.AbstractWidget;

public final class FocusHandler {
    // Todo: qyl27: save focused widget in somewhere?
    public static void tabFocus(IGuiHost host, boolean backward) {
        boolean result;
        AbstractWidget focus = host.getFocused();

        if (focus == null) {
            result = tabFocus(host, backward, host.getRootCanvas(), null);
        } else {
            result = tabFocus(host, backward, focus, null);
        }

        if (!result) {
            // Try again from the beginning
            tabFocus(host, backward, host.getRootCanvas(), null);
        }
    }

    private static boolean tabFocus(IGuiHost host, boolean backward, AbstractWidget widget, AbstractWidget cursor) {
        AbstractWidget next;
        if (widget instanceof AbstractCanvas canvas) {
            next = canvas.tabFocus(backward, cursor);
        } else {
            next = widget.tabFocus(backward);
        }

        if (next != null) {
            host.requestFocus(next);
            return true;
        } else {
            AbstractCanvas parent = widget.getParent();
            if (parent != null) {
                return tabFocus(host, backward, parent, widget);
            }
        }

        return false;
    }
}
