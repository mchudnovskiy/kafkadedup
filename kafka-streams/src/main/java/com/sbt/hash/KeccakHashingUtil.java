package com.sbt.hash;

import org.bouncycastle.jcajce.provider.digest.Keccak;
import org.bouncycastle.util.encoders.Hex;

import java.nio.charset.StandardCharsets;

public final class KeccakHashingUtil {

    /**
     * Функиця для хэшрования
     */
    private static final Keccak.Digest256 DIGEST256 = new Keccak.Digest256();

    private KeccakHashingUtil() {

    }

    /**
     * Хэширование входящего сообщения
     * @param message - входящее сообщение
     * @return - рещультат хэш-функции
     */
    public static String getHashOfMessage(String message) {
        byte[] hashResult = DIGEST256.digest(
                message.getBytes(StandardCharsets.UTF_8));
        return new String(Hex.encode(hashResult));
    }
}
