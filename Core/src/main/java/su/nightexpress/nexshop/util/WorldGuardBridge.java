package su.nightexpress.nexshop.util;

import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.lang.reflect.Method;

public final class WorldGuardBridge {

    private static final String FLAGS_CLASS = "su.nightexpress.orbisshop.integration.claim.WorldGuardFlags";

    private WorldGuardBridge() {}

    public static void setupFlag() {
        invokeVoid("setupFlag");
    }

    public static boolean checkFlag(@NotNull Player player, @NotNull Location location) {
        try {
            Class<?> clazz = Class.forName(FLAGS_CLASS);
            Method method = clazz.getMethod("checkFlag", Player.class, Location.class);
            Object result = method.invoke(null, player, location);
            return result instanceof Boolean bool ? bool : true;
        }
        catch (ReflectiveOperationException ignored) {
            return true;
        }
    }

    private static void invokeVoid(@NotNull String methodName) {
        try {
            Class<?> clazz = Class.forName(FLAGS_CLASS);
            Method method = clazz.getMethod(methodName);
            method.invoke(null);
        }
        catch (ReflectiveOperationException ignored) {}
    }
}
