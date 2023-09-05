package flaxbeard.cyberware.api;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import flaxbeard.cyberware.utils.CommandUtils;
import net.minecraft.world.entity.player.Player;

import java.util.ArrayList;
import java.util.List;

public interface OrganAction {
    void run(Player player);

    static List<OrganAction> fromJson(JsonArray array) {
        List<OrganAction> actions = new ArrayList<>();

        array.forEach(element -> {
            JsonObject object = element.getAsJsonObject();
            if (object.has("command"))
                actions.add((player) -> CommandUtils.run(object.get("command").getAsString(), player));
        });

        return actions;
    }
}
