package flaxbeard.cyberware;

import com.google.common.base.Suppliers;
import dev.architectury.registry.registries.RegistrarManager;
import flaxbeard.cyberware.common.block.CWBlocks;
import flaxbeard.cyberware.common.data.CWDataReloadListeners;
import flaxbeard.cyberware.common.entity.CWEntities;
import flaxbeard.cyberware.common.item.CWItems;
import flaxbeard.cyberware.common.organ.CWOrgans;
import flaxbeard.cyberware.common.organ.slot.CWOrganSlots;
import flaxbeard.cyberware.common.organ.type.CWOrganTypes;
import flaxbeard.cyberware.common.packet.CWPackets;
import flaxbeard.cyberware.common.potion.CWEffects;

import java.util.function.Supplier;

public class Cyberware {

	public static final String MODID = "cyberware";
	public static final Supplier<RegistrarManager> MANAGER = Suppliers.memoize(() -> RegistrarManager.get(MODID));

	public static void init(){
		CWEffects.register();
		CWEntities.register();
		CWPackets.register();
		CWItems.register();
		CWBlocks.register();
		CWDataReloadListeners.register();

		CWOrganSlots.register();
		CWOrganTypes.register();
		CWOrgans.register();
	}
}
