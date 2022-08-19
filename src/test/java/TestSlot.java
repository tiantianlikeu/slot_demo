import com.icode.slotchain.ProcessorSlotChain;
import com.icode.slotchain.SlotChainProvider;
import org.junit.Test;

/**
 * @Author: tiantianlikeU。
 * @Date: 2022/8/19 15:38
 */
public class TestSlot {

    @Test
    public void TestSlotBuilder() {
        ProcessorSlotChain processorSlotChain = SlotChainProvider.newSlotChain();
        System.out.println(processorSlotChain.getNext().getNext());
    }
}
