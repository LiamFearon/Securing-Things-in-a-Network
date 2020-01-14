package iotsecuringthings;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;

public class FileOperations {
	
	/**
	 * Creates a new text file which is used for storing output from program. This file is used in the encryption section of the code
	 * 
	 * @return file
	 * @throws FileNotFoundException
	 */
	protected static PrintStream createFile() throws FileNotFoundException {
		String PATH = "src" + File.separator + "savedData.txt";
		PrintStream o = new PrintStream(new File(PATH).getAbsoluteFile());
		return o;
	}

	/**
	 * Writes encrypted text to encryptedFileJohn.txt, using FileOutputStream to write byte array of content from the retrieved file from getFile method
	 * 
	 * @param bytes
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	protected void SaveEncryptedFile(byte[] bytes) throws FileNotFoundException, IOException {
		String PATH = "src" + File.separator + "encryptedFileJohn.txt";
		FileOutputStream fosDoc = new FileOutputStream(PATH);
		fosDoc.write(bytes);
		fosDoc.close();
	}

	/**
	 * Writes a decrypted version of text from encryptedFileJohn.txt to a new file
	 * 
	 * @param bytes
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	protected void SaveDecryptedFile(byte[] bytes) throws FileNotFoundException, IOException {
		String PATH = "src" + File.separator + "decryptedFileOtega.txt";
		FileOutputStream fosDoc = new FileOutputStream(new File(PATH).getAbsoluteFile());
		fosDoc.write(bytes);
		fosDoc.close();
	}

	/**
	 * Fetches file to be encrypted, returning an array of characters
	 * 
	 * @return file
	 */
	protected static byte[] getFile() {
		String PATH = "src" + File.separator + "savedData.txt";
		File file = new File(PATH).getAbsoluteFile();

		InputStream is = null;
		try {
			is = new FileInputStream(file);
		} catch (FileNotFoundException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		
		byte[] content = null;
		
		try {
			content = new byte[is.available()];
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		try {
			is.read(content);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return content;
	}
}
