package cx.rain.mc.classicui.api.gui.render;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.*;
import cx.rain.mc.classicui.utility.layout.Location;
import cx.rain.mc.classicui.utility.layout.Size;
import cx.rain.mc.classicui.utility.render.Color;
import cx.rain.mc.classicui.utility.render.UV;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.FormattedCharSequence;

import java.util.List;

public class GuiDrawing {
    private final GuiGraphics graphics;

    public GuiDrawing(GuiGraphics graphics) {
        this.graphics = graphics;
    }

    public Font getFont() {
        return graphics.minecraft.font;
    }

    /// <editor-fold desc="Texture">

    public void texture(ResourceLocation texture, Location location, Size size) {
        texture(texture, location, size, new UV(0F, 0F, 1F, 1F));
    }

    public void texture(ResourceLocation texture, Location location, Size size, UV uv) {
        innerTexture(texture, location, size, uv, 0);
    }

    public void texture(ResourceLocation texture, Location location, Size size, UV uv, int zIndex) {
        innerTexture(texture, location, size, uv, zIndex);
    }

    /// </editor-fold>

    /// <editor-fold desc="String">

    public void centeredString(Component component, Location location, Color color) {
        graphics.drawCenteredString(graphics.minecraft.font, component, location.getX(), location.getY(), color.toPackedARGB());
    }

    public void string(Component component, Location location, Color color) {
        string(component, location, color, false);
    }

    public void string(Component component, Location location, Color color, boolean drawShadow) {
        string(component.getVisualOrderText(), location, color, drawShadow);
    }

    public void string(FormattedCharSequence text, Location location, Color color, boolean drawShadow) {
        graphics.drawString(graphics.minecraft.font, text, location.getX(), location.getY(),
                color.toPackedARGB(), !drawShadow);
    }

    // Todo: qyl27: RTL string.

    public void multiLinedString(List<Component> components, Location location, Color color,
                                 int maxLineWidth, boolean drawShadow) {
        var font = graphics.minecraft.font;
        var loc = location.clone();

        for (var component : components) {
            var lines = font.split(component, maxLineWidth);

            for (var line : lines) {
                string(line, loc, color, !drawShadow);
                loc.addY(font.lineHeight);
            }
        }
    }

    /// </editor-fold>

    /// <editor-fold desc="Shape">

    // Todo: qyl27: Draw rectangle, triangle, line, curve.

    /// </editor-fold>

    /// <editor-fold desc="Inner">

    private void innerTexture(ResourceLocation texture, Location location, Size size, UV uv, int zIndex) {
        var xStart = location.getX();
        var yStart = location.getY();
        var xEnd = location.getX() + size.width();
        var yEnd = location.getY() + size.height();
        var uStart = uv.uOffsetF();
        var vStart = uv.vOffsetF();
        var uEnd = uv.uWidthF();
        var vEnd = uv.vHeightF();

        RenderSystem.setShaderTexture(0, texture);
        RenderSystem.setShader(GameRenderer::getPositionTexShader);
        var matrix4f = graphics.pose().last().pose();
        BufferBuilder bufferBuilder = Tesselator.getInstance().getBuilder();
        bufferBuilder.begin(VertexFormat.Mode.QUADS, DefaultVertexFormat.POSITION_TEX);
        bufferBuilder.vertex(matrix4f, xStart, yStart, zIndex).uv(uStart, vStart).endVertex();
        bufferBuilder.vertex(matrix4f, xStart, yEnd, zIndex).uv(uStart, vEnd).endVertex();
        bufferBuilder.vertex(matrix4f, xEnd, yEnd, zIndex).uv(uEnd, vEnd).endVertex();
        bufferBuilder.vertex(matrix4f, xEnd, yStart, zIndex).uv(uEnd, vStart).endVertex();
        BufferUploader.drawWithShader(bufferBuilder.end());
    }

    /// </editor-fold>
}
