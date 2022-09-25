package cx.rain.classicui.gui.widget.base;

import com.mojang.blaze3d.vertex.PoseStack;
import cx.rain.classicui.ClassicUI;
import cx.rain.classicui.utility.ComponentBuilder;
import cx.rain.classicui.utility.HandleResult;
import cx.rain.classicui.gui.host.IGuiHost;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.narration.NarrationElementOutput;
import net.minecraft.client.gui.narration.NarrationSupplier;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public abstract class AbstractWidget implements IShowable, IFocusable, NarrationSupplier {

    // <editor-fold desc="Minecraft.">

    public Minecraft getMinecraft() {
        return Minecraft.getInstance();
    }

    // </editor-fold>

    // <editor-fold desc="Host.">

    @Nullable
    protected IGuiHost host;

    @Nullable
    public IGuiHost getHost() {
        return host;
    }

    public boolean hasHost() {
        return getHost() != null;
    }

    public void setHost(IGuiHost guiHost) {
        if (guiHost != null) {
            host = guiHost;
        } else {
            ClassicUI.getInstance().getLogger().warn("Trying set null host for " + this);
        }
    }

    // </editor-fold>

    // <editor-fold desc="Parent.">

    @Nullable
    protected AbstractCanvas parent;

    @Nullable
    public AbstractCanvas getParent() {
        return parent;
    }

    public boolean hasParent() {
        return getParent() != null;
    }

    public void setParent(@Nullable AbstractCanvas canvas) {
        parent = canvas;
    }

    // </editor-fold>

    // <editor-fold desc="Location.">

    // qyl27: The x and y value is relative to parent.
    protected int xLoc = 0;
    protected int yLoc = 0;

    public void setLocation(int x, int y) {
        xLoc = x;
        yLoc = y;
    }

    public int getX() {
        return xLoc;
    }

    public int getY() {
        return yLoc;
    }

    public int getAbsoluteX() {
        if (hasParent()) {
            assert getParent() != null;
            return getX() + getParent().getAbsoluteX();
        }
        return getX();
    }

    public int getAbsoluteY() {
        if (hasParent()) {
            assert getParent() != null;
            return getY() + getParent().getAbsoluteY();
        }
        return getY();
    }

    // </editor-fold>

    // <editor-fold desc="Sizing.">

    protected int width = 0;
    protected int height = 0;

    public boolean canResize() {
        return false;
    }

    /**
     * Set size of the widget.
     * @param w Width of the widget.
     * @param h Height of the widget.
     */
    public void setSize(int w, int h) {
        if (!canResize()) {
            throw new IllegalStateException("You can't resize me.");
        }

        width = w;
        height = h;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    // </editor-fold>

    // <editor-fold desc="Input.">

    /**
     * Mouse click handler.
     * @param x Current mouse x.
     * @param y Current mouse y.
     * @param button Mouse button id clicked.
     * @return Handle result.
     */
    public HandleResult onMouseClick(int x, int y, int button) {
        return HandleResult.IGNORED;
    }

    /**
     * Mouse scroll handler.
     * @param x Current mouse x.
     * @param y Current mouse y.
     * @param amount Scroll amount in y.
     * @return Handle result.
     */
    public HandleResult onMouseScroll(int x, int y, double amount) {
        return HandleResult.IGNORED;
    }

    /**
     * Mouse scroll handler.
     * Invoked tick by tick.
     * @param x Current mouse x.
     * @param y Current mouse y.
     * @return Handle result.
     */
    public HandleResult onMouseMove(int x, int y) {
        return HandleResult.IGNORED;
    }

    /**
     * Mouse drag handler.
     * @param x Current mouse x.
     * @param y Current mouse y.
     * @param xDelta Drag amount in x.
     * @param yDelta Drag amount in y.
     * @return Handle result.
     */
    public HandleResult onMouseDrag(int x, int y, double xDelta, double yDelta) {
        return HandleResult.IGNORED;
    }

    /**
     * Keyboard press handler.
     * @param charCode Keyboard char code.
     * @param keyCode GLFW scan code of the key.
     * @param modifiers Modifiers like shift.
     * @return Handle result.
     */
    public HandleResult onKeyPressed(int charCode, int keyCode, int modifiers) {
        return HandleResult.IGNORED;
    }

    /**
     * Keyboard press handler.
     * @param charCode Keyboard char code.
     * @param keyCode GLFW scan code of the key.
     * @param modifiers Modifiers like shift.
     * @return Handle result.
     */
    public HandleResult onKeyReleased(int charCode, int keyCode, int modifiers) {
        return HandleResult.IGNORED;
    }

    public HandleResult onKeyTyped(char typed) {
        return HandleResult.IGNORED;
    }

    public boolean isInside(int x, int y) {
        return shouldRender() && x >= getX() && y >= getY() && x < getWidth() && y < getHeight();
    }

    // </editor-fold>

    // <editor-fold desc="Focusing.">

    public boolean canFocus() {
        return false;
    }

    /**
     * Circle focus in this widget. (Like using tab.)
     * If widget is not focusable, returns null.
     * @param backward If true, direction is backward. (Like using shift + tab.)
     * @return The next focused widget, or null if no other widget can focus.
     */
    public AbstractWidget tabFocus(boolean backward) {
        return canFocus() ? (isFocused() ? null : this) : null;
    }

    public boolean isFocused() {
        if (!hasHost()) {
            return false;
        }

        assert getHost() != null;
        return getHost().isFocused(this);
    }

    public void requestFocus() {
        if (hasHost()) {
            assert getHost() != null;
            if (canFocus()) {
                getHost().requestFocus(this);
            }
        } else {
            ClassicUI.getInstance().getLogger().warn("Requested to focus a no-host widget. It should not be happened.");
        }
    }

    public void releaseFocus() {
        if (hasHost()) {
            assert getHost() != null;
            getHost().releaseFocus(this);
        }
    }

    @Override
    public void onFocusGained() {
    }

    @Override
    public void onFocusLost() {
    }

    /**
     * Find the most specific child node at this location. Return itself in non-canvas widgets.
     * @param x X location.
     * @param y Y location.
     * @return The most specific child in this location.
     */
    public AbstractWidget hitChild(int x, int y) {
        return this;
    }

    // </editor-fold>

    // <editor-fold desc="Hiding.">

    protected boolean visible;

    @Override
    public void hide() {
        visible = false;
    }

    @Override
    public void show() {
        visible = true;
    }

    @Override
    public boolean shouldRender() {
        return visible;
    }

    @Override
    public void onShown() {
    }

    @Override
    public void onHidden() {
        releaseFocus();
    }

    // </editor-fold>

    // <editor-fold desc="Rendering.">

    /**
     * Render the widget, this method is invoked by ClassicUI.
     * @param stack PoseStack.
     * @param mouseX Mouse cursor X.
     * @param mouseY Mouse cursor Y.
     */
    public void render(PoseStack stack, int mouseX, int mouseY) {
        if (!shouldRender()) {
            return;
        }

        render(stack, getAbsoluteX(), getAbsoluteY(), mouseX, mouseY);
    }

    /**
     * Render the widget.
     * Use getX() and getY() to get widget relative location.
     *
     * @param stack PoseStack.
     * @param x Absolute X location.
     * @param y Absolute Y location.
     * @param mouseX Mouse cursor X.
     * @param mouseY Mouse cursor Y.
     */
    protected void render(PoseStack stack, int x, int y, int mouseX, int mouseY) {
    }

    /**
     * Rendering a tooltip.
     * @param stack PoseStack.
     * @param tooltipX Tooltip X (relative to widget xLoc).
     * @param tooltipY Tooltip Y (relative to widget yLoc).
     */
    public void renderTooltip(PoseStack stack, int tooltipX, int tooltipY) {
        if (!shouldRender()) {
            return;
        }

        var componentBuilder = ComponentBuilder.builder();
        addTooltip(componentBuilder);

        var screen = getMinecraft().screen;
        if (screen != null) {
            screen.renderTooltip(stack, componentBuilder.build(), getX() + tooltipX, getY() + tooltipY);
        }
    }

    public void addTooltip(ComponentBuilder builder) {
    }

    // </editor-fold>

    // <editor-fold desc="Ticking.">

    public void tick() {
    }

    // </editor-fold>

    // <editor-fold desc="Narrations.">

    // Todo: qyl27: This part need more validation.

    public boolean isNarratable() {
        return true;
    }

    @Override
    public void updateNarration(@NotNull NarrationElementOutput output) {
    }

    // </editor-fold>
}
