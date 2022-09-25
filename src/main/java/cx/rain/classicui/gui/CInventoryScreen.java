package cx.rain.classicui.gui;

import com.mojang.blaze3d.vertex.PoseStack;
import cx.rain.classicui.gui.host.SyncingGuiHost;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.AbstractContainerMenu;

public class CInventoryScreen<T extends SyncingGuiHost, U extends AbstractContainerMenu>
        extends AbstractContainerScreen<U> {

    protected SyncingGuiHost guiInfo;

    public CInventoryScreen(T info, U menu, Inventory inventory, Component title) {
        super(menu, inventory, title);
        guiInfo = info;
    }

    @Override
    protected void renderBg(PoseStack poseStack, float partialTick, int mouseX, int mouseY) {

    }
}
