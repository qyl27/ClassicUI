package cx.rain.classicui.gui.widget.slot;

import com.mojang.blaze3d.vertex.PoseStack;
import cx.rain.classicui.gui.renderer.IStyledRenderer;
import cx.rain.classicui.gui.widget.base.AbstractWidget;
import cx.rain.classicui.gui.widget.base.IHasStyledRenderer;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.Slot;

public class WItemSlot extends AbstractWidget implements IHasStyledRenderer {
    protected Slot itemSlot;
    protected boolean big = false;

    protected IStyledRenderer styledRenderer;

    public WItemSlot(Slot slot) {
        itemSlot = slot;
    }

    public WItemSlot(Slot slot, int x, int y) {
        this(slot);
        setLocation(x, y);
    }

    public WItemSlot(Slot slot, int x, int y, boolean bigSlot) {
        this(slot, x, y);
        big = bigSlot;
        setSize(26, 26);
    }

    public WItemSlot(Slot slot, int x, int y, int width, int height) {
        this(slot, x, y);
        setSize(width, height);
    }

    public boolean isBigSlot() {
        return big;
    }

    @Override
    public boolean canFocus() {
        return true;
    }

    public boolean canPlace() {
        return itemSlot.mayPlace(itemSlot.getItem());
    }

    public boolean canTake(Player player) {
        return itemSlot.mayPickup(player);
    }

    public boolean canModification(Player player) {
        return itemSlot.allowModification(player);
    }

    @Override
    public void render(PoseStack stack, int mouseX, int mouseY) {
        if (hasStyledRenderer()) {
            styledRenderer.renderItemSlotBackground(stack, getAbsoluteX(), getAbsoluteY(), this);
        }
    }

    @Override
    public IStyledRenderer getStyledRenderer() {
        return styledRenderer;
    }

    @Override
    public void setStyledRenderer(IStyledRenderer renderer) {
        styledRenderer = renderer;
    }

    @Override
    public boolean hasStyledRenderer() {
        return styledRenderer != null;
    }
}
