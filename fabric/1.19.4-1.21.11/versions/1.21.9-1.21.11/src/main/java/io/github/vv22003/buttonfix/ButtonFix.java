package io.github.vv22003.buttonfix;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.screen.v1.ScreenEvents;
import net.fabricmc.fabric.api.client.screen.v1.ScreenMouseEvents;
import net.minecraft.client.gui.ComponentPath;
import net.minecraft.client.gui.components.AbstractButton;
import net.minecraft.client.gui.components.AbstractSliderButton;
import net.minecraft.client.gui.components.events.GuiEventListener;
import net.minecraft.client.gui.screens.Screen;

public class ButtonFix implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        ScreenEvents.BEFORE_INIT.register((client, screen, scaledWidth, scaledHeight) -> registerScreenMouseEvents(screen));
    }

    private static void registerScreenMouseEvents(Screen screen) {
        ScreenMouseEvents.afterMouseClick(screen).register((screenInstance, context, consumed) -> {
            if (getFocusedWidget(screenInstance) instanceof AbstractButton) {
                screenInstance.clearFocus();
            }
            return false;
        });

        ScreenMouseEvents.afterMouseRelease(screen).register((screenInstance, context, consumed) -> {
            if (getFocusedWidget(screenInstance) instanceof AbstractSliderButton) {
                screenInstance.clearFocus();
            }
            return false;
        });
    }

    private static GuiEventListener getFocusedWidget(Screen screen) {
        ComponentPath path = screen.getCurrentFocusPath();
        while (path instanceof ComponentPath.Path p) {
            path = p.childPath();
        }
        return path != null ? path.component() : null;
    }
}