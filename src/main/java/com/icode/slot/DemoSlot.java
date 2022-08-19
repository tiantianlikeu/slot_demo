package com.icode.slot;

import com.icode.common.annos.Slot;
import com.icode.common.exception.BusinessException;
import com.icode.slotchain.AbstractLinkedProcessorSlot;

/**
 * @Author: tiantianlikeU。
 * @Date: 2022/8/19 15:28
 */
@Slot(order = -1)
public class DemoSlot extends AbstractLinkedProcessorSlot {

    @Override
    public void entry(Object... args) throws Throwable {
        checkXxxx(args);
        fireEntry(args);
    }

    @Override
    public void exit(Object... args) {
        fireExit(args);
    }

    void checkXxxx(Object... args) {
        throw new BusinessException("", "xxx 异常");
    }
}
