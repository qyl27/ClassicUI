package cx.rain.classicui.utility;

import cx.rain.classicui.gui.widget.base.AbstractCanvas;
import cx.rain.classicui.gui.widget.base.AbstractWidget;

import java.util.AbstractList;
import java.util.ArrayList;
import java.util.List;

public final class CheckedWidgetList extends AbstractList<AbstractWidget> {

    private final AbstractCanvas owner;
    private final List<AbstractWidget> list;

    public CheckedWidgetList(AbstractCanvas parent) {
        owner = parent;
        list = new ArrayList<>();
    }

    private void check(AbstractWidget widget) {
        if (widget == null) {
            throw new NullPointerException("Why the widget is null?");
        }

        // qyl27: checking circular including.
        var depth = 0;
        var parent = owner;
        while (parent != null) {
            if (widget == parent) {
                if (depth == 0) {
                    throw new RuntimeException("Why are you adding widget to it self?");
                } else {
                    throw new RuntimeException("Adding parent recursively!");
                }
            }

            parent = parent.getParent();
            depth += 1;
        }
    }

    @Override
    public AbstractWidget get(int index) {
        return list.get(index);
    }

    @Override
    public int size() {
        return list.size();
    }

    @Override
    public void add(int index, AbstractWidget widget) {
        check(widget);
        list.add(index, widget);
    }

    @Override
    public AbstractWidget set(int index, AbstractWidget widget) {
        check(widget);
        return list.set(index, widget);
    }

    @Override
    public AbstractWidget remove(int index) {
        return list.remove(index);
    }
}
