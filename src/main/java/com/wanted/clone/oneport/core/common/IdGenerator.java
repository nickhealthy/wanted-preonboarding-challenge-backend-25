package com.wanted.clone.oneport.core.common;

import java.security.SecureRandom;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class IdGenerator {
    private final static String CHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";

    public static String generateId() {
        String yyyyMMdd = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd"));
        String randomNumber = _secureRandom(10);
        return yyyyMMdd + randomNumber;
    }

    private static String _secureRandom(int genNum) {
        SecureRandom random = new SecureRandom();
        StringBuffer sb = new StringBuffer();

        for (int i = 0; i < genNum; i++) {
            int index = random.nextInt(CHARS.length()); // 무작위로 문자열의 인덱스 반환
            sb.append(CHARS.charAt(index)); // index의 위치한 랜덤값
        }
        return sb.toString();
    }
}
