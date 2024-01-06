package me.andreasmelone.amutillib.blocks;

import jdk.nashorn.internal.ir.Block;
import me.andreasmelone.amutillib.blocks.events.OnBlockPlaceRunnable;
import me.andreasmelone.amutillib.items.AMItem;

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
        return block.get().getKey().equals(getItem().getKey());
    }
    public static AMBlock from(AMItem item) {
        return new AMBlock(item);
    }
}
