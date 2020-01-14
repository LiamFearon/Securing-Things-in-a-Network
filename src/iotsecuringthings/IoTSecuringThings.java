package iotsecuringthings;

import java.net.*;
import java.io.*;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.util.Scanner;
import javax.crypto.KeyGenerator;

public class IoTSecuringThings {

	private static void main(String[] args) throws MalformedURLException, IOException, NoSuchAlgorithmException {
		PrintStream o = FileOperations.createFile();														//Declaring new stream of characters to be used by following methods to write to savedData.txt
		DevicesOnNetwork dON = new DevicesOnNetwork();														//Creating new object to contain information about devices on the network


		System.setOut(o);																					//Setting system's output to the PrintStream used for writing output to savedData.txt

		DevicesOnNetwork.getIPAddress(dON);

		DevicesOnNetwork.getPortInformation();

		DevicesOnNetwork.getSocketInformation();

		EncryptDecrypt.encryptDecrypt();
	}
}