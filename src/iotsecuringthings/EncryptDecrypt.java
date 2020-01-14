package iotsecuringthings;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;

public class EncryptDecrypt {
	
	/**
	 * Creates/initialises encryption, also creating a key to encrypt/decrypt the resulting files which this method uses.
	 * 
	 * @throws NoSuchAlgorithmException
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	protected static void encryptDecrypt() throws NoSuchAlgorithmException, FileNotFoundException, IOException {
		FileOperations fileOperations = new FileOperations();												//Declare/initialise methods for encryption/decryption from FileOperations class

		KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");										//Setting AES to be encryption specification to be used
		keyGenerator.init(128); 																			//Setting 128 bits system of encryption
		Key key = (Key) keyGenerator.generateKey();															//Creating keys for successfully encrypting/decrypting data
		System.out.println(key);																			//Printing encryption key used

		byte[] content = FileOperations.getFile();															//Get the saved data file
		System.out.println(content);

		byte[] encrypted = EncryptFile(key, content);														//Encrypt the saved data file
		System.out.println(encrypted);

		byte[] decrypted = DecryptFile(key, encrypted);														//Dencrypt the saved data file
		System.out.println(decrypted);

		fileOperations.SaveEncryptedFile(encrypted);														//Save encrypted data file
		fileOperations.SaveDecryptedFile(decrypted);														//Save decrypted data file
	}

	/**
	 * Encrypts contents, using the provided key
	 * 
	 * @param key
	 * @param content
	 * @return encrypted
	 */
	protected static byte[] EncryptFile(Key key, byte[] content) {
		Cipher cipher;																						// New variable from Cipher built in class
		byte[] encrypted = null;
		try {
			cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
			cipher.init(Cipher.ENCRYPT_MODE, (java.security.Key) key);
			encrypted = cipher.doFinal(content);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return encrypted;
	}

	/**
	 * Decrypts contents using the provided key
	 * 
	 * @param key
	 * @param textCryp
	 * @return decrypted
	 */
	protected static byte[] DecryptFile(Key key, byte[] textCryp) {
		Cipher cipher;
		byte[] decrypted = null;
		try {
			cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
			cipher.init(Cipher.DECRYPT_MODE, (java.security.Key) key);
			decrypted = cipher.doFinal(textCryp);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return decrypted;
	}
}
