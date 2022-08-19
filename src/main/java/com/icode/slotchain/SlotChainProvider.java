package com.icode.slotchain;

/**
 * @Author: tiantianlikeU。
 * @Date: 2022/8/17 14:33
 */
public final class SlotChainProvider {

    private static volatile SlotChainBuilder slotChainBuilder = null;

    public static ProcessorSlotChain newSlotChain() {
        if (slotChainBuilder != null) {
            return slotChainBuilder.build();
        }

        if (slotChainBuilder == null) {
            // Should not go through here.
            slotChainBuilder = new DefaultSlotChainBuilder();
        }
        return slotChainBuilder.build();
    }

    private SlotChainProvider() {
    }
}
