package cx.rain.classicui.gui.screen;

import com.mojang.blaze3d.vertex.PoseStack;
import cx.rain.classicui.gui.widget.base.IShowable;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.Style;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.Nullable;

public class ScreenBase extends Screen implements IShowable {
    protected Screen parentScreen;

    protected boolean shouldRender;
    protected boolean closed;

    protected ScreenBase(Screen parent, Component title) {
        super(title);

        parentScreen = parent;
    }

    @Override
    protected void init() {
        super.init();


    }

    @Override
    public void onClose() {
        super.onClose();
        closed = true;
    }

    @Override
    public void show() {
        shouldRender = true;
    }

    @Override
    public void hide() {
        shouldRender = false;
    }

    @Override
    public boolean shouldRender() {
        return (!closed) && shouldRender;
    }

    @Override
    public void onShown() {

    }

    @Override
    public void onHidden() {

    }

    @Override
    public void render(PoseStack poseStack, int mouseX, int mouseY, float partialTick) {
        if (!shouldRender()) {
            return;
        }

        super.render(poseStack, mouseX, mouseY, partialTick);
    }

    @Override
    public void tick() {
        super.tick();
    }

    @Override
    public void renderComponentHoverEffect(PoseStack poseStack, @Nullable Style style, int mouseX, int mouseY) {
        super.renderComponentHoverEffect(poseStack, style, mouseX, mouseY);
    }
}
