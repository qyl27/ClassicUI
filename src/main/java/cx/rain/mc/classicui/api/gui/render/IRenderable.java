package cx.rain.mc.classicui.api.gui.render;

import org.jetbrains.annotations.ApiStatus;

public interface IRenderable {
    @ApiStatus.OverrideOnly
    void render(GuiDrawing drawing, int mouseX, int mouseY, float partialTick);
}
