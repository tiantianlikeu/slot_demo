package com.icode.slotchain;

/**
 * @Author: tiantianlikeU。
 * @Date: 2022/8/17 09:42
 */
public class DefaultProcessorSlotChain extends ProcessorSlotChain {

    AbstractLinkedProcessorSlot<?> first = new AbstractLinkedProcessorSlot<Object>() {

        @Override
        public void entry(Object... args)
                throws Throwable {
            super.fireEntry(args);
        }

        @Override
        public void exit(Object... args) {
            super.fireExit(args);
        }

    };

    AbstractLinkedProcessorSlot<?> end = first;

    @Override
    public void addFirst(AbstractLinkedProcessorSlot<?> protocolProcessor) {
        protocolProcessor.setNext(first.getNext());
        first.setNext(protocolProcessor);
        if (end == first) {
            end = protocolProcessor;
        }
    }

    @Override
    public void addLast(AbstractLinkedProcessorSlot<?> protocolProcessor) {
        end.setNext(protocolProcessor);
        end = protocolProcessor;
    }

    @Override
    public void setNext(AbstractLinkedProcessorSlot<?> next) {
        addLast(next);
    }

    @Override
    public AbstractLinkedProcessorSlot<?> getNext() {
        return first.getNext();
    }

    @Override
    public void entry(Object... args)
            throws Throwable {
        first.transformEntry(args);
    }

    @Override
    public void exit(Object... args) {
        first.exit(args);
    }
}
