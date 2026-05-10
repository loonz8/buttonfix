package io.github.vv22003.buttonfix;

import net.minecraft.client.gui.ComponentPath;
import net.minecraft.client.gui.components.AbstractButton;
import net.minecraft.client.gui.components.AbstractSliderButton;
import net.minecraft.client.gui.components.events.GuiEventListener;
import net.minecraft.client.gui.screens.Screen;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.ScreenEvent;
import net.minecraftforge.eventbus.api.listener.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;

@Mod("buttonfix")
public class ButtonFix {
    @Mod.EventBusSubscriber(modid = "buttonfix", bus = Bus.FORGE, value = Dist.CLIENT)
    public static class ClientEvents {

        @SubscribeEvent
        static void onMouseClicked(ScreenEvent.MouseButtonPressed.Post event) {
            GuiEventListener widget = getFocusedWidget(event.getScreen());
            if (widget instanceof AbstractButton) {
                event.getScreen().setFocused(null);
            }
        }

        @SubscribeEvent
        static void onMouseReleased(ScreenEvent.MouseButtonReleased.Post event) {
            GuiEventListener widget = getFocusedWidget(event.getScreen());
            if (widget instanceof AbstractSliderButton) {
                event.getScreen().setFocused(null);
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