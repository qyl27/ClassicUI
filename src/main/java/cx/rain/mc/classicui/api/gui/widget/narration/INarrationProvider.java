package cx.rain.mc.classicui.api.gui.widget.narration;

import net.minecraft.network.chat.Component;
import org.jetbrains.annotations.ApiStatus;

@ApiStatus.Experimental
public interface INarrationProvider {
    default Component getNarration() {
        return Component.empty();
    }
}
