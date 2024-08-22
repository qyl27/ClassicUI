package cx.rain.mc.classicui.gui.screen;

import cx.rain.mc.classicui.gui.component.AbstractComposedComponent;
import cx.rain.mc.classicui.gui.component.IComponent;
import cx.rain.mc.classicui.gui.component.IComposedComponent;
import cx.rain.mc.classicui.gui.window.IWindow;
import cx.rain.mc.classicui.gui.window.IWindowHolder;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.events.GuiEventListener;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.naming.OperationNotSupportedException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class AbstractScreen extends Screen implements IWindowHolder {
    protected AbstractScreen(Component title) {
        super(title);
    }

    /// <editor-fold desc="IComponent.">

    @Override
    public AbstractComposedComponent getParent() {
        return null;
    }

    @Override
    public void setParent(@Nullable IComposedComponent parent) {
        throw new RuntimeException(new OperationNotSupportedException());
    }

    @Override
    public void setX(int x) {
        throw new RuntimeException(new OperationNotSupportedException());
    }

    @Override
    public void setY(int y) {
        throw new RuntimeException(new OperationNotSupportedException());
    }

    @Override
    public int getX() {
        return 0;
    }

    @Override
    public int getY() {
        return 0;
    }

    @Override
    public int getWidth() {
        return width;
    }

    @Override
    public int getHeight() {
        return height;
    }

    @Override
    public boolean isHovered() {
        return true;
    }

    /// </editor-fold>

    /// <editor-fold desc="Windows holder.">

    /**
     * Map<IWindow window, Boolean shown>
     */
    private final Map<IWindow, Boolean> windows = new HashMap<>();

    @Nullable
    private IWindow mutexWindow = null;

    @Nullable
    private IWindow focusedWindow = null;

    @Override
    public @NotNull List<IWindow> getWindows() {
        return List.copyOf(windows.keySet());
    }

    @Override
    public void addWindow(@NotNull IWindow window, boolean mutex, boolean show) {
        addChild(window);
        windows.put(window, false);
        window.onOpen();

        if (mutex) {
            if (!hasMutexWindow()) {
                setMutexWindow(window);
            } else {
                throw new IllegalStateException();
            }
        }

        if (show) {
            show(window);
        }
    }

    @Override
    public void closeWindow(@NotNull IWindow window) {
        if (getMutexWindow() == window) {
            setMutexWindow(null);
        }

        if (getFocusedWindow() == window) {
            setFocusedWindow(null);
        }

        removeChild(window);
        windows.remove(window);
        window.onClose();
    }

    @Override
    public void show(@NotNull IWindow window) {
        if (!windows.get(window)) {
            windows.put(window, true);
            window.onShown();
        }
    }

    @Override
    public void hide(@NotNull IWindow window) {
        if (windows.get(window) && getMutexWindow() != window) {
            windows.put(window, false);
            window.onHidden();
        }
    }

    @Override
    public @Nullable IWindow getMutexWindow() {
        return mutexWindow;
    }

    @Override
    public void setMutexWindow(@Nullable IWindow window) {
        if (window != null && hasWindow(window)) {
            setFocusedWindow(window);
            mutexWindow = window;
        } else {
            mutexWindow = null;
        }
    }

    @Override
    public @Nullable IWindow getFocusedWindow() {
        return focusedWindow;
    }

    @Override
    public void setFocusedWindow(@Nullable IWindow window) {
        if (hasMutexWindow()) {
            setFocused(getMutexWindow());
            return;
        }

        setFocused(window);
        for (var w : getWindows()) {
            w.setFocused(w == window);
        }
    }

    @Override
    public void setFocused(@Nullable GuiEventListener focused) {
        super.setFocused(focused);

        if (focused instanceof IWindow window) {
            this.focusedWindow = window;
        } else {
            this.focusedWindow = null;
        }
    }

    private final List<IComponent> children = new ArrayList<>();

    @Override
    public void addChild(@NotNull IComponent child) {
        children.add(child);
        child.setParent(this);
        addRenderableWidget(child);
    }

    @Override
    public void removeChild(@NotNull IComponent child) {
        children.remove(child);
        child.setParent(null);
        removeWidget(child);
    }

    @Override
    public List<IComponent> getChildren() {
        return List.copyOf(children);
    }

    @Override
    public @NotNull List<? extends GuiEventListener> children() {
        return List.copyOf(getChildren());
    }

    @Override
    protected void rebuildWidgets() {
        unInitialize();
        super.rebuildWidgets();
    }

    @Override
    protected void init() {
        super.init();
        initialize();
    }

    @Override
    public void tick() {
        for (var c : getChildren()) {
            c.tick();
        }

        super.tick();
    }

    protected abstract void createChildren();

    @Override
    public final void initialize() {
        createChildren();

        for (var child : getChildren()) {
            child.initialize();
        }
    }

    @Override
    public void unInitialize() {
        for (var child : getChildren()) {
            child.unInitialize();
        }

        closeWindows();
        clearChildren();
    }

    /// </editor-fold>

    @Override
    public void render(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTick) {
        var maskedMouseX = hasMutexWindow() ? -1 : mouseX;
        var maskedMouseY = hasMutexWindow() ? -1 : mouseY;

        renderBackground(guiGraphics);
        guiGraphics.drawCenteredString(minecraft.font, title, this.width / 2, 4, 16777215);

        for (var c : getChildren()) {
            if (!(c instanceof IWindow)) {
                c.render(guiGraphics, maskedMouseX, maskedMouseY, partialTick);
            }
        }

        guiGraphics.pose().pushPose();
        guiGraphics.pose().translate(0, 0, 500);

        if (hasWindow()) {
            drawGrayishBackground(guiGraphics);
        }

        for (var w : getWindows()) {
            if (w != getMutexWindow()) {
                w.render(guiGraphics, maskedMouseX, maskedMouseY, partialTick);
            }
        }

        if (hasMutexWindow()) {
            getMutexWindow().render(guiGraphics, mouseX, mouseY, partialTick);
        }

        guiGraphics.pose().popPose();
    }

    @Override
    public boolean mouseClicked(double mouseX, double mouseY, int button) {
        if (hasMutexWindow()) {
            assert getMutexWindow() != null;
            return getMutexWindow().mouseClicked(mouseX, mouseY, button);
        }

        return IWindowHolder.super.mouseClicked(mouseX, mouseY, button);
    }

    @Override
    public boolean mouseReleased(double mouseX, double mouseY, int button) {
        if (hasMutexWindow()) {
            assert getMutexWindow() != null;
            return getMutexWindow().mouseReleased(mouseX, mouseY, button);
        }

        return IWindowHolder.super.mouseReleased(mouseX, mouseY, button);
    }

    @Override
    public boolean mouseDragged(double mouseX, double mouseY, int button, double dragX, double dragY) {
        if (hasMutexWindow()) {
            assert getMutexWindow() != null;
            return getMutexWindow().mouseDragged(mouseX, mouseY, button, dragX, dragY);
        }

        return IWindowHolder.super.mouseDragged(mouseX, mouseY, button, dragX, dragY);
    }

    @Override
    public boolean mouseScrolled(double mouseX, double mouseY, double delta) {
        if (hasMutexWindow()) {
            assert getMutexWindow() != null;
            return getMutexWindow().mouseScrolled(mouseX, mouseY, delta);
        }

        return IWindowHolder.super.mouseScrolled(mouseX, mouseY, delta);
    }

    @Override
    public boolean keyPressed(int keyCode, int scanCode, int modifiers) {
        if (hasMutexWindow()) {
            assert getMutexWindow() != null;
            return getMutexWindow().keyPressed(keyCode, scanCode, modifiers);
        }

        return IWindowHolder.super.keyPressed(keyCode, scanCode, modifiers);
    }

    @Override
    public boolean keyReleased(int keyCode, int scanCode, int modifiers) {
        if (hasMutexWindow()) {
            assert getMutexWindow() != null;
            return getMutexWindow().keyReleased(keyCode, scanCode, modifiers);
        }

        return IWindowHolder.super.keyReleased(keyCode, scanCode, modifiers);
    }

    @Override
    public boolean charTyped(char codePoint, int modifiers) {
        if (hasMutexWindow()) {
            assert getMutexWindow() != null;
            return getMutexWindow().charTyped(codePoint, modifiers);
        }

        return IWindowHolder.super.charTyped(codePoint, modifiers);
    }

    public static void drawGrayishBackground(GuiGraphics guiGraphics) {
        guiGraphics.pose().pushPose();
        guiGraphics.pose().last().pose().translate(0, 0, -1);
        guiGraphics.fillGradient(0, 0, guiGraphics.guiWidth(), guiGraphics.guiHeight(), -1072689136, -804253680);
        guiGraphics.pose().last().pose().translate(0, 0, 1);
        guiGraphics.pose().popPose();
    }
}
