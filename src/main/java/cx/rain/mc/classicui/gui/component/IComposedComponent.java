package cx.rain.mc.classicui.gui.component;

import net.minecraft.client.gui.components.events.ContainerEventHandler;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public interface IComposedComponent extends IComponent, ContainerEventHandler {
    void addChild(@NotNull IComponent child);

    void removeChild(@NotNull IComponent child);

    List<IComponent> getChildren();

    default void clearChildren() {
        for (var c : getChildren()) {
            c.setParent(null);
            removeChild(c);
        }
    }

    @Override
    default boolean mouseClicked(double mouseX, double mouseY, int button) {
        return ContainerEventHandler.super.mouseClicked(mouseX, mouseY, button);
    }

    @Override
    default boolean mouseReleased(double mouseX, double mouseY, int button) {
        return ContainerEventHandler.super.mouseReleased(mouseX, mouseY, button);
    }

    @Override
    default boolean mouseDragged(double mouseX, double mouseY, int button, double dragX, double dragY) {
        return ContainerEventHandler.super.mouseDragged(mouseX, mouseY, button, dragX, dragY);
    }

    @Override
    default boolean mouseScrolled(double mouseX, double mouseY, double delta) {
        return ContainerEventHandler.super.mouseScrolled(mouseX, mouseY, delta);
    }

    @Override
    default boolean keyPressed(int keyCode, int scanCode, int modifiers) {
        return ContainerEventHandler.super.keyPressed(keyCode, scanCode, modifiers);
    }

    @Override
    default boolean keyReleased(int keyCode, int scanCode, int modifiers) {
        return ContainerEventHandler.super.keyReleased(keyCode, scanCode, modifiers);
    }

    @Override
    default boolean charTyped(char codePoint, int modifiers) {
        return ContainerEventHandler.super.charTyped(codePoint, modifiers);
    }
}
