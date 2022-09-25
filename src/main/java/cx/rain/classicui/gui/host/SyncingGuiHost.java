package cx.rain.classicui.gui.host;

import cx.rain.classicui.gui.widget.base.AbstractCanvas;
import cx.rain.classicui.gui.widget.base.AbstractWidget;
import cx.rain.classicui.utility.ContentAlign;
import cx.rain.classicui.utility.Location;
import cx.rain.classicui.utility.Size;
import net.minecraft.network.chat.Component;
import net.minecraft.world.Container;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class SyncingGuiHost extends AbstractContainerMenu implements IGuiHost {

    protected Player player;
    protected Inventory playerInventory;
    protected Level level;

    protected Container guiInventory;
    protected ContainerData guiData;


    public SyncingGuiHost(MenuType<?> type, int containerId, Inventory inventory) {
        super(type, containerId);

        player = inventory.player;
        playerInventory = inventory;
        level = player.getLevel();
    }

    public SyncingGuiHost(MenuType<?> type, int containerId, Inventory inventory, Container container, ContainerData data) {
        this(type, containerId, inventory);
        guiInventory = container;
        guiData = data;

        if (guiInventory != null) {
            guiInventory.startOpen(player);
        }

        if (guiData != null && guiData.getCount() > 0) {
            addDataSlots(guiData);
        }
    }

    @Override
    public AbstractWidget getFocused() {
        return null;
    }

    @Override
    public void setFocused(AbstractWidget widget) {

    }

    @Override
    public boolean isFocused(AbstractWidget widget) {
        return false;
    }

    @Override
    public void requestFocus(AbstractWidget widget) {

    }

    @Override
    public void releaseFocus(AbstractWidget widget) {

    }

    @Override
    public AbstractWidget tabFocus() {
        return null;
    }

    @Override
    public ContainerData getContainerData() {
        return null;
    }

    @Override
    public IGuiHost setContainerData(ContainerData data) {
        return null;
    }

    @Override
    public AbstractCanvas getRootCanvas() {
        return null;
    }

    @Override
    public void setRootCanvas(AbstractCanvas rootCanvas) {

    }

    @Override
    public Component getTitle() {
        return null;
    }

    @Override
    public void setTitle(Component title) {

    }

    @Override
    public boolean isTitleVisible() {
        return false;
    }

    @Override
    public void setTitleVisible(boolean titleVisible) {

    }

    @Override
    public Location getTitleLocation() {
        return null;
    }

    @Override
    public Size getTitleSize() {
        return null;
    }

    @Override
    public ContentAlign getTitleAlign() {
        return null;
    }

    @Override
    public ItemStack quickMoveStack(Player player, int index) {
        return null;
    }

    @Override
    public boolean stillValid(Player player) {
        return false;
    }
}
