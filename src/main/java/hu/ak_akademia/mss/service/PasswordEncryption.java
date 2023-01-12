package hu.ak_akademia.mss.service;

import hu.ak_akademia.mss.model.Client;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class PasswordEncryption {

    public String encrypt(Client client) {
        try {
            var md = MessageDigest.getInstance("MD5");
            byte[] digest = md.digest(client.getPassword().getBytes(StandardCharsets.UTF_8));
            return getBytes(digest).stream() //
                    .map(b -> Integer.toString((b & 0xff) + 0x100, 16)) //
                    .collect(Collectors.joining());
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    private static List<Byte> getBytes(byte[] digest) {
        List<Byte> bytes = new ArrayList<>();
        for (var b : digest) {
            bytes.add(b);
        }
        return bytes;
    }
}
