package flaxbeard.cyberware;

import com.google.common.base.Suppliers;
import dev.architectury.registry.registries.Registrar;
import dev.architectury.registry.registries.RegistrarManager;
import flaxbeard.cyberware.common.entity.CWEntities;
import flaxbeard.cyberware.common.organ.Organs;
import flaxbeard.cyberware.common.potion.CWEffects;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.Item;

import java.util.function.Supplier;

public class Cyberware {
	public static final String MODID = "cyberware";
	public static final Supplier<RegistrarManager> MANAGER = Suppliers.memoize(() -> RegistrarManager.get(MODID));
	public static final Registrar<Item> ITEMS = MANAGER.get().get(Registries.ITEM);

	public static void init(){
		CWEffects.init();
		CWEntities.init();
		Organs.register();
	}
}
