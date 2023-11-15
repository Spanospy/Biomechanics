package flaxbeard.cyberware;

import com.google.common.base.Suppliers;
import dev.architectury.registry.registries.RegistrarManager;
import flaxbeard.cyberware.common.block.CWBlocks;
import flaxbeard.cyberware.common.effect.CWEffects;
import flaxbeard.cyberware.common.entity.CWEntities;
import flaxbeard.cyberware.common.item.CWItems;
import flaxbeard.cyberware.common.organ.CWOrgan;
import flaxbeard.cyberware.common.organ.slot.CWOrganSlotType;
import flaxbeard.cyberware.common.organ.type.CWOrganType;
import flaxbeard.cyberware.common.packet.CWPackets;

import java.util.function.Supplier;

public class Cyberware {

	public static final String MODID = "cyberware";
	public static final Supplier<RegistrarManager> MANAGER = Suppliers.memoize(() -> RegistrarManager.get(MODID));

	public static void init(){
		CWOrganSlotType.register();
		CWOrganType.register();
		CWOrgan.register();

		CWEntities.register();
		CWPackets.register();
		CWItems.register();
		CWBlocks.register();
		CWEffects.register();
	}
}
