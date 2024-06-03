package net.PeytonPlayz585.shadow;

import java.util.Collection;
import java.util.Iterator;
import java.util.function.Predicate;
import net.lax1dude.eaglercraft.v1_8.opengl.GlStateManager;

public class FuncUtils {

    public static <T> boolean removeIf(Collection<T> collection, Predicate<T> pre) {
        boolean ret = false;
        Iterator<T> itr = collection.iterator();
        while (itr.hasNext()) {
            if (pre.test(itr.next())) {
                itr.remove();
                ret = true;
            }
        }
        return ret;
    }

    public static boolean isInside(int mouseX, int mouseY, double x, double y, double width, double height) {
        return (mouseX > x && mouseX < (x + width)) && (mouseY > y && mouseY < (y + height));
    }

    public static void setColor(int color) {
        float f3 = (float) (color >> 24 & 255) / 255.0F;
        float f = (float) (color >> 16 & 255) / 255.0F;
        float f1 = (float) (color >> 8 & 255) / 255.0F;
        float f2 = (float) (color & 255) / 255.0F;

        GlStateManager.color(f, f1, f2, f3);
    }
}