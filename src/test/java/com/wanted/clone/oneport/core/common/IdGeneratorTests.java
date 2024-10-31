package com.wanted.clone.oneport.core.common;

import org.junit.jupiter.api.Test;

public class IdGeneratorTests {
    @Test
    public void generateId_Normal_yyyyMMddXXXXXX() {
        String id = IdGenerator.generateId();
    }

}
