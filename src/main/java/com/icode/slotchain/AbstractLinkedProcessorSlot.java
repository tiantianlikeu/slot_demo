package com.icode.slotchain;

/**
 * @Author: tiantianlikeU。
 * @Date: 2022/8/16 15:58
 */
public abstract class AbstractLinkedProcessorSlot<T> implements ProcessorSlot<T> {

    private AbstractLinkedProcessorSlot<?> next = null;

    @Override
    public void fireEntry(Object... args)
            throws Throwable {
        if (next != null) {
            next.transformEntry(args);
        }
    }

    @SuppressWarnings("unchecked")
    void transformEntry(Object... args)
            throws Throwable {
        entry(args);
    }

    @Override
    public void fireExit(Object... args) {
        if (next != null) {
            next.exit(args);
        }
    }

    public AbstractLinkedProcessorSlot<?> getNext() {
        return next;
    }

    public void setNext(AbstractLinkedProcessorSlot<?> next) {
        this.next = next;
    }

}
