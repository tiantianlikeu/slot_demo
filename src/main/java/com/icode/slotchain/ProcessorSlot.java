package com.icode.slotchain;

/**
 * @Author: tiantianlikeUă€‚
 * @Date: 2022/8/16 15:58
 */
public interface ProcessorSlot<T> {

    void entry(Object... args) throws Throwable;

    void fireEntry(Object... args) throws Throwable;

    void exit(Object... args);

    void fireExit(Object... args);
}
