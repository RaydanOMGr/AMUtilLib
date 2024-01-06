package me.andreasmelone.amutillib.blocks;

import com.jeff_media.customblockdata.CustomBlockData;
import me.andreasmelone.amutillib.blocks.events.OnBlockPlaceRunnable;
import me.andreasmelone.amutillib.items.AMItem;
import org.bukkit.NamespacedKey;
import org.bukkit.block.Block;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;

import java.util.LinkedList;
import java.util.List;

public class AMBlock extends AMItem {
    AMItem item;
    protected AMBlock(AMItem item) {
        super(item.getKey(), item.getName(), item.getLore(), item.getMaterial(), item.getCustomModelData());
        if(!item.getMaterial().isBlock()) throw new IllegalArgumentException("item must be a block");

        this.item = item;
    }

    public AMItem getItem() {
        return item;
    }

    List<OnBlockPlaceRunnable> onBlockPlace = new LinkedList<>();

    public void onBlockPlace(OnBlockPlaceRunnable runnable) {
        onBlockPlace.add(runnable);
    }

    public List<OnBlockPlaceRunnable> getOnBlockPlace() {
        return onBlockPlace;
    }

    public boolean compareTo(Block block) {
        PersistentDataContainer pdc = new CustomBlockData(block, getKey().getNamespace());
        String id  = pdc.getOrDefault(
                new NamespacedKey(getKey().getNamespace(), "block_id"),
                PersistentDataType.STRING,
                ""
        );
        return id.equals(getKey().getKey());
    }
    public static AMBlock from(AMItem item) {
        return new AMBlock(item);
    }
}
