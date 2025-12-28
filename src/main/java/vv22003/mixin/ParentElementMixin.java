package vv22003.mixin;

import net.minecraft.client.gui.Click;
import net.minecraft.client.gui.ParentElement;
import net.minecraft.client.gui.widget.PressableWidget;
import net.minecraft.client.gui.widget.SliderWidget;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(ParentElement.class)
public interface ParentElementMixin {

    @Inject(method = "mouseClicked", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/ParentElement;setFocused(Lnet/minecraft/client/gui/Element;)V", shift = At.Shift.AFTER))
    private void fixMouseClicked(Click click, boolean doubled, CallbackInfoReturnable<Boolean> cir) {
        ParentElement self = (ParentElement) this;
        // Prevent buttons from remaining focused after being clicked
        if (self.getFocused() instanceof PressableWidget) {
            self.setFocused(null);
        }
    }

    @Inject(method = "mouseReleased", at = @At("HEAD"))
    private void fixMouseReleased(Click click, CallbackInfoReturnable<Boolean> cir) {
        ParentElement self = (ParentElement) this;
        // Prevent sliders from remaining focused after being dragged
        if (self.getFocused() instanceof SliderWidget) {
            self.setFocused(null);
        }
    }
}