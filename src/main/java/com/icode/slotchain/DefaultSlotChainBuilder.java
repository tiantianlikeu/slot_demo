package com.icode.slotchain;

import com.icode.common.annos.Slot;
import com.icode.util.classes.ClassUtil;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * 卡 代码实现类收集
 *
 * @Author: tiantianlikeU 。
 * @Date: 2022 /8/17 10:42
 */
public class DefaultSlotChainBuilder implements SlotChainBuilder {

    @Override
    public ProcessorSlotChain build() {
        ProcessorSlotChain chain = new DefaultProcessorSlotChain();
        String packageName = "com.icode";
        List<Class<?>> annoClasses = ClassUtil.getAnnoClasses(Slot.class, packageName);

        // 优先级排序
        Collections.sort(annoClasses, new Comparator<Class<?>>() {
            @Override
            public int compare(Class<?> o1, Class<?> o2) {
                Slot slot1 = o1.getAnnotation(Slot.class);
                int order1 = slot1 == null ? 0 : slot1.order();
                Slot slot2 = o2.getAnnotation(Slot.class);
                int order2 = slot2 == null ? 0 : slot2.order();
                return Integer.compare(order1, order2);
            }
        });

        for (Class<?> slot : annoClasses) {
            ProcessorSlot slotByCast = createInstance(slot);
            if (slotByCast != null) {
                chain.addLast((AbstractLinkedProcessorSlot<?>) slotByCast);
            }
        }
        return chain;
    }


    /**
     * 类型强转
     * @param clazz
     * @return
     */
    private ProcessorSlot createInstance(Class<?> clazz) {
        ProcessorSlot instance = null;
        try {
            ProcessorSlot slotByCast = ProcessorSlot.class.cast(clazz.newInstance());
            instance = slotByCast;
        } catch (Throwable e) {
            e.printStackTrace();
        }
        return instance;
    }
}
