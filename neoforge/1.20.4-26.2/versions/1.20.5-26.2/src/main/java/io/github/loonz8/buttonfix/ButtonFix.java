package io.github.loonz8.buttonfix;

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
            if (getFocusedWidget(event.getScreen()) instanceof AbstractButton) {
                event.getScreen().clearFocus();
            }
        }

        @SubscribeEvent
        static void onMouseReleased(ScreenEvent.MouseButtonReleased.Post event) {
            if (getFocusedWidget(event.getScreen()) instanceof AbstractSliderButton) {
                event.getScreen().clearFocus();
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