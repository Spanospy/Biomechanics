package flaxbeard.cyberware;

import com.google.common.base.Suppliers;
import dev.architectury.registry.registries.RegistrarManager;
import flaxbeard.cyberware.common.entity.CWEntities;
import flaxbeard.cyberware.common.organ.Organs;
import flaxbeard.cyberware.common.packet.CWPackets;
import flaxbeard.cyberware.common.potion.CWEffects;

import java.util.function.Supplier;

public class Cyberware {
	public static final String MODID = "cyberware";
	public static final Supplier<RegistrarManager> MANAGER = Suppliers.memoize(() -> RegistrarManager.get(MODID));

	public static void init(){
		CWEffects.init();
		CWEntities.init();
		Organs.init();
		CWPackets.init();
	}
}
