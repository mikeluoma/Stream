package com.interview;

import org.junit.Assert;
import org.junit.jupiter.api.Test;

public class DumbStreamImplTest {
    @Test
    public void testContinuousMockData() {
        OneKStream ds = new OneKStreamImpl();
        byte[] buffer = new byte[1024];
        int last = 0;
        int read;
        boolean atLeastOneReadReturnedData = false;
        do {
            read = ds.read1k(buffer);
            if (read > 0) {
                atLeastOneReadReturnedData = true;
                for (int i = 0; i < read; i++) {
                    Assert.assertEquals("Byte " + i + " not in sequence", (byte) last++, buffer[i]);
                }
            }
        } while (read >= 0);

        Assert.assertTrue("Should have some data generated", atLeastOneReadReturnedData);
    }
}
