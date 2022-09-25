package cx.rain.classicui.utility;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.*;
import com.mojang.math.Matrix4f;
import cx.rain.classicui.gui.screen.ScreenBase;
import cx.rain.classicui.gui.widget.text.WLabel;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.network.chat.Style;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.FormattedCharSequence;
import org.jetbrains.annotations.Nullable;

public final class DrawingHelper {
    public static void drawString(PoseStack stack, Font font, FormattedCharSequence text, ContentAlign align, int x, int y) {
        font.draw(stack, text, x, y, WLabel.DEFAULT_TEXT_COLOR.getValue());
    }

    public static void drawHoverText(PoseStack stack, @Nullable Style style, int x, int y) {
        var screen = Minecraft.getInstance().screen;
        if (screen instanceof ScreenBase base) {
            base.renderComponentHoverEffect(stack, style, x, y);
        }
    }

    public static void drawSlot(PoseStack stack, int x, int y) {
        drawSlot(stack, x, y, 18, 18);
    }

    public static void drawSlot(PoseStack stack, int x, int y, int width, int height) {
        // Todo.
    }

    public static void drawRect(PoseStack stack, int x, int y, int width, int height, ResourceLocation texture) {
        drawRect(stack, x, y, width, height, texture, new UV(0, 0), new UV(1, 1), 1);
    }

    public static void drawRect(PoseStack stack, int x, int y, int width, int height, ResourceLocation texture,
                                UV uvStart, UV uvEnd) {
        drawRect(stack, x, y, width, height, texture, uvStart, uvEnd, 1);
    }

    public static void drawRect(PoseStack stack, int x, int y, int width, int height, ResourceLocation texture,
                                UV uvStart, UV uvEnd, float alpha) {
        stack.pushPose();

        var tesselator = Tesselator.getInstance();
        var buffer = tesselator.getBuilder();
        var pose = stack.last().pose();

        RenderSystem.enableBlend();
        RenderSystem.setShaderTexture(0, texture);
        RenderSystem.setShaderColor(1, 1, 1, alpha);
        RenderSystem.setShader(GameRenderer::getPositionTexShader);

        buffer.begin(VertexFormat.Mode.QUADS, DefaultVertexFormat.POSITION_TEX);
        buffer.vertex(pose, x, y + height, 0).uv(uvStart.u(), uvEnd.v()).endVertex();
        buffer.vertex(pose, x + width, y + height, 0).uv(uvEnd.u(), uvEnd.v()).endVertex();
        buffer.vertex(pose, x + width, y, 0).uv(uvEnd.u(), uvStart.v()).endVertex();
        buffer.vertex(pose, x, y, 0).uv(uvStart.u(), uvStart.v()).endVertex();
        BufferUploader.drawWithShader(buffer.end());

        RenderSystem.disableBlend();

        stack.popPose();
    }
}
