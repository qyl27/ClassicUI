package cx.rain.classicui.gui.widget.container;

import cx.rain.classicui.gui.widget.WLabel;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Inventory;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class WPlayerInventory extends WPlainCanvas {
    public static final Component DEFAULT_TEXT = Component.translatable("container.inventory");

    protected Inventory playerInventory;
    protected WLabel inventoryLabel;

    public WPlayerInventory(Inventory inventory) {
        this(inventory, true);
    }

    public WPlayerInventory(Inventory inventory, boolean hasLabel) {
        this(inventory, hasLabel ? createInventoryLabel(inventory) : null);
    }

    public WPlayerInventory(Inventory inventory, @Nullable WLabel label) {
        inventoryLabel = label;
        playerInventory = inventory;

        var y = 0;
        if (label != null) {
            add(label, 0, 0, label.getWidth(), label.getHeight());
            y += label.getHeight();
        }


    }

    public static @NotNull WLabel createInventoryLabel(@NotNull Inventory inventory) {
        var label = new WLabel(inventory.getDisplayName());
        label.setSize(9 * 18, 11);
        return label;
    }
}
