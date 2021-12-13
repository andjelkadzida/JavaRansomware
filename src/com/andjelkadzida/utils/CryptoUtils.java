package com.andjelkadzida.utils;

import com.andjelkadzida.exceptions.CryptoException;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;

public class CryptoUtils
{
    private static final String ALGORITHM = "AES";
    private static final String TRANSFORMATION = "AES";

    public static void encrypt(String key, File input, File output) throws CryptoException
    {
        crypt(Cipher.ENCRYPT_MODE, key, input, output);
    }

    public static void decrypt(String key, File input, File output) throws CryptoException
    {
        crypt(Cipher.DECRYPT_MODE, key, input, output);
    }

    public static void crypt(int cipherMode, String key, File input, File output) throws CryptoException
    {
        try
        {
            Key secretKey = new SecretKeySpec(key.getBytes(), ALGORITHM);
            Cipher cipher = Cipher.getInstance(TRANSFORMATION);
            cipher.init(cipherMode, secretKey);

            FileInputStream fileInputStream = new FileInputStream(input);
            byte [] inputBytes = new byte[(int) input.length()];
            fileInputStream.read(inputBytes);

            byte [] outputBytes = cipher.doFinal(inputBytes);

            FileOutputStream fileOutputStream = new FileOutputStream(output);
            fileOutputStream.write(outputBytes);

            fileInputStream.close();
            fileOutputStream.close();
        }
        catch (NoSuchPaddingException | NoSuchAlgorithmException | InvalidKeyException | BadPaddingException | IllegalBlockSizeException |IOException ex)
        {
            throw new CryptoException("Error encrypting/decrypting file ", ex);
        }
    }
}
