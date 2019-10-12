package com.sbt.hash;

import org.junit.Assert;
import org.junit.Test;

public class KeccakHashingUtilTest {
    @Test
    public void getHashOfMessageTest() {
        Assert.assertEquals("21d06c7ac0bff3ba7b621b61728e7b38d9e107eaa595be84c301beb00f827266",
                (KeccakHashingUtil.getHashOfMessage("This is my message")));
    }
}
