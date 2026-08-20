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
public class ButtonFix {
    public static final String MODID = "buttonfix";

    @Mod.EventBusSubscriber(modid = MODID, value = Dist.CLIENT)
    public static class ClientModEvents {
        @SubscribeEvent
        static void onMouseClicked(ScreenEvent.MouseButtonPressed.Post event) {
            GuiEventListener focused = getFocusedWidget(event.getScreen());
            if (focused instanceof AbstractButton) {
                focused.setFocused(false);
            }
        }

        @SubscribeEvent
        static void onMouseReleased(ScreenEvent.MouseButtonReleased.Post event) {
            GuiEventListener focused = getFocusedWidget(event.getScreen());
            if (focused instanceof AbstractSliderButton) {
                focused.setFocused(false);
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