package cx.rain.mc.classicui.gui.window;

import cx.rain.mc.classicui.gui.component.IComponent;

public interface IWindow extends IComponent {
    default void onOpen() {
    }
    default void onClose() {
    }

    default void onShown() {
    }
    default void onHidden() {
    }

    IWindowHolder getHolder();
}
