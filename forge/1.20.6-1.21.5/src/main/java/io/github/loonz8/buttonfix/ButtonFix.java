package io.github.loonz8.buttonfix;

import net.minecraft.client.gui.ComponentPath;
import net.minecraft.client.gui.components.AbstractButton;
import net.minecraft.client.gui.components.AbstractSliderButton;
import net.minecraft.client.gui.components.events.GuiEventListener;
import net.minecraft.client.gui.screens.Screen;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.ScreenEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod(ButtonFix.MODID)
public final class ButtonFix {
    public static final String MODID = "buttonfix";

    @Mod.EventBusSubscriber(modid = MODID, value = Dist.CLIENT)
    public static class ClientModEvents {
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