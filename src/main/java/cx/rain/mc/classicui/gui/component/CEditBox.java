package cx.rain.mc.classicui.gui.component;

import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.components.EditBox;
import net.minecraft.network.chat.Component;
import org.jetbrains.annotations.Nullable;

public class CEditBox extends EditBox implements IComponent {
    @Nullable
    private IComposedComponent parent;

    public CEditBox(Font font, int x, int y, int width, int height, Component message) {
        super(font, x, y, width, height, message);
    }

    @Override
    public boolean isHovered() {
        return super.isHovered();
    }

    @Override
    public @Nullable IComposedComponent getParent() {
        return parent;
    }

    @Override
    public void setParent(@Nullable IComposedComponent parent) {
        this.parent = parent;
    }
}
