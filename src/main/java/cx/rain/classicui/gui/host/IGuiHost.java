package cx.rain.classicui.gui.host;

import cx.rain.classicui.container.IContainerDataHolder;
import cx.rain.classicui.gui.widget.base.AbstractCanvas;
import cx.rain.classicui.gui.widget.base.ITitled;
import cx.rain.classicui.utility.FocusHandler;
import net.minecraft.world.inventory.ContainerData;

public interface IGuiHost extends ITitled, IContainerDataHolder, IFocusableHolder {
    public IGuiHost setContainerData(ContainerData data);

    public AbstractCanvas getRootCanvas();

    public void setRootCanvas(AbstractCanvas rootCanvas);

    default void tabFocus(boolean backward) {
        FocusHandler.tabFocus(this, backward);
    }
}
