package flaxbeard.cyberware.mixin;

import flaxbeard.cyberware.common.organ.Organ;
import flaxbeard.cyberware.common.organ.Organs;
import flaxbeard.cyberware.common.organ.cybernetic.IPowerStoring;
import flaxbeard.cyberware.interfaces.CyberwareAbilities;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.nbt.StringTag;
import net.minecraft.nbt.Tag;
import net.minecraft.world.entity.player.Abilities;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.ArrayList;
import java.util.List;

@Mixin(Abilities.class)
public class AbilitiesMixin implements CyberwareAbilities {
    private List<Tag> cyberware = new ArrayList<>();
    private float maxPower = 0;
    private float storedPower = 0;

    @Inject(method = "addSaveData", at = @At("RETURN"))
    private void addSaveData(CompoundTag compoundTag, CallbackInfo ci) {
        ListTag listTag = new ListTag();
        for (Tag tag : cyberware) {
            listTag.add(tag);
        }
        compoundTag.put("cyberware", listTag);
        if (storedPower >= maxPower)
            storedPower = maxPower;
        compoundTag.putFloat("cw_storedPower", storedPower);
    }

    @Inject(method = "loadSaveData", at = @At("RETURN"))
    private void loadSaveData(CompoundTag compoundTag, CallbackInfo ci) {
        if (compoundTag.contains("cyberware")) {
            ListTag listTag = compoundTag.getList("cyberware", 10);
            for (Tag tag : listTag) {
                cyberware.add(tag);
            }
        }else {
            this.addCyberware(Organs.HEART);
        }

        maxPower = this.getMaxPower();

        if (compoundTag.contains("cw_storedPower")) {
            if (compoundTag.getFloat("cw_storedPower") >= maxPower)
                compoundTag.putFloat("cw_storedPower", maxPower);

            storedPower = compoundTag.getFloat("cw_storedPower");
        }else {
            storedPower = 0;
        }
    }

    @Override
    public List<Organ> getCyberwares() {
        List<Organ> organs = new ArrayList<>();
        for (Tag tag : cyberware) {
            organs.add(Organs.getOrgan(tag.getAsString()));
        }
        return organs;
    }

    @Override
    public void addCyberware(Organ organ) {
        cyberware.add(StringTag.valueOf(organ.name));
    }

    @Override
    public void removeCyberware(Organ organ) {
        cyberware.remove(StringTag.valueOf(organ.name));
    }

    @Override
    public boolean hasCyberware(Organ organ) {
        return cyberware.contains(StringTag.valueOf(organ.name));
    }

    @Override
    public float getMaxPower() {
        int maxPower = 0;
        for (Organ organ : this.getCyberwares()) {
            if (organ instanceof IPowerStoring) {
                maxPower += ((IPowerStoring) organ).getMaxPower();
            }
        }
        return maxPower;
    }

    @Override
    public float getStoredPower() {
        int storedPower = 0;
        for (Organ organ : this.getCyberwares()) {
            if (organ instanceof IPowerStoring) {
                storedPower += ((IPowerStoring) organ).getPower();
            }
        }
        return storedPower;
    }

    @Override
    public void addStoredPower(float power) {
        storedPower += power;
        if (power <= 0)
            storedPower = 0;
        if (power >= this.getMaxPower())
            storedPower = this.getMaxPower();
    }

    @Override
    public void subStoredPower(float power) {
        storedPower -= power;
        if (power <= 0)
            storedPower = 0;
        if (power >= this.getMaxPower())
            storedPower = this.getMaxPower();
    }
    
    @Override
    public void setStoredPower(float power) {
        storedPower = Math.max(power, 0);
        if (power >= this.getMaxPower())
            storedPower = this.getMaxPower();
    }
}
