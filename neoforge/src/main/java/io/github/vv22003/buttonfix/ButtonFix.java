package io.github.vv22003.buttonfix;

import net.minecraft.client.gui.ComponentPath;
import net.minecraft.client.gui.components.AbstractButton;
import net.minecraft.client.gui.components.AbstractSliderButton;
import net.minecraft.client.gui.components.events.GuiEventListener;
import net.minecraft.client.gui.screens.Screen;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.client.event.ScreenEvent;

@Mod("buttonfix")
public class ButtonFix {
    @EventBusSubscriber(modid = "buttonfix", value = Dist.CLIENT)
    public static class ClientEvents {

        @SubscribeEvent
        static void onMouseClicked(ScreenEvent.MouseButtonPressed.Post event) {
            Screen screen = event.getScreen();
            if (getFocusedWidget(screen) instanceof AbstractButton) {
                screen.setFocused(null);
            }
        }

        @SubscribeEvent
        static void onMouseReleased(ScreenEvent.MouseButtonReleased.Post event) {
            Screen screen = event.getScreen();
            if (getFocusedWidget(screen) instanceof AbstractSliderButton) {
                screen.setFocused(null);
            }
        }

        private static GuiEventListener getFocusedWidget(Screen screen) {
            ComponentPath path = screen.getCurrentFocusPath();
            if (path == null) return null;
            while (path instanceof ComponentPath.Path p) {
                path = p.childPath();
            }
            return path.component();
        }
    }
}