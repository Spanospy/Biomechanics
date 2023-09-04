package flaxbeard.cyberware.utils;

import net.minecraft.commands.CommandSource;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;

import java.util.HashMap;
import java.util.Map;

public class CommandUtils {
    public static void run(String command, Entity entity) {
        command = PlaceHolders.transform(command, entity);

        if (entity.level().isClientSide)
            return;

        entity.level().getServer().getCommands().performPrefixedCommand(
                new CommandSourceStack(
                        CommandSource.NULL, entity.position(), entity.getRotationVector(),
                        (ServerLevel) entity.level(), 4, entity.getName().getString(),
                        entity.getDisplayName(), entity.getServer(), entity
                ),
                command
        );
    }

    public static class PlaceHolders {
        public static Map<String, EntitySupplier> PLACEHOLDERS = new HashMap<>();

        public static String transform(String input, Entity entity) {
            for (Map.Entry<String, EntitySupplier> entry : PLACEHOLDERS.entrySet()) {
                input = input.replace(entry.getKey(), entry.getValue().get(entity));
            }
            return input;
        }

        static {
            register("%max_number%", String.valueOf(1048576));
        }

        public static void register(String placeholder, EntitySupplier supplier) {
            PLACEHOLDERS.put(placeholder, supplier);
        }

        public static void register(String placeholder, String value) {
            PLACEHOLDERS.put(placeholder, entity -> value);
        }

        public interface EntitySupplier {
            String get(Entity entity);
        }
    }
}
