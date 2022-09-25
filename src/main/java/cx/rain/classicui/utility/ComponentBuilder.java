package cx.rain.classicui.utility;

import net.minecraft.network.chat.Component;
import net.minecraft.util.FormattedCharSequence;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

// Todo: qyl27: better implementation.
public final class ComponentBuilder {
    private List<FormattedCharSequence> lines = new ArrayList<>();

    private ComponentBuilder() {

    }

    public static ComponentBuilder builder() {
        return new ComponentBuilder();
    }

    public ComponentBuilder add(Component... components) {
        for (var component : components) {
            lines.add(component.getVisualOrderText());
        }

        return this;
    }

    public ComponentBuilder add(FormattedCharSequence... components) {
        Collections.addAll(lines, components);

        return this;
    }

    public ComponentBuilder clear() {
        lines.clear();
        return this;
    }

    public int lineCount() {
        return lines.size();
    }

    public int componentCount() {
        // Todo: qyl27: change to count components.
        return lineCount();
    }

    public List<FormattedCharSequence> build() {
        return lines;
    }
}
