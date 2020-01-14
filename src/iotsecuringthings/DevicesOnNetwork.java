package iotsecuringthings;

import java.io.*;
import java.net.*;
import java.util.Scanner;

public class DevicesOnNetwork {

    protected void GetData(String BasicIP) throws UnknownHostException, IOException {
        //Scanning time for devices on the network. 
        int ScanTime = 500;
        for (int i = 1; i < 255; i++) {
            String Users = BasicIP + i;
            if (InetAddress.getByName(Users).isReachable(ScanTime)) {
                System.out.println(Users + " is found on the network");
            }
        }
    }
    
    /**
	 * Makes a request to return the IP of the current device, with formatting to ensure the machines IP is not revealed 
	 * 
	 * @return PublicIP
	 * @throws MalformedURLException
	 * @throws IOException
	 */
	protected static String getIPAddress(DevicesOnNetwork devicesOnNetwork) throws MalformedURLException, IOException {
		URL whatismyip = new URL("http://checkip.amazonaws.com"); 											// Get the IP connected to the internet using checkip website
		BufferedReader in = new BufferedReader(new InputStreamReader(whatismyip.openStream()));
		String PublicIP = in.readLine(); 																	//you get the IP as a String
		String IpWithNoFinalPart = PublicIP.replaceAll("(.*\\.)\\d+$", "$1"); 								// Remove the final IP number
		System.out.println("The connected IPs to the network:  " + IpWithNoFinalPart); 						// Print the machine IP
		devicesOnNetwork.GetData(IpWithNoFinalPart);
		return PublicIP;
	}
    
    /**
	 * Uses netstat command to find port information of the connected network. With this we can see what connections are active and their process IDs on the system
	 * 
	 * @throws IOException
	 */
	protected static void getPortInformation() throws IOException {
		String[] the_array_of_cmd = {"netstat", "-o"};
		Process process = Runtime.getRuntime().exec(the_array_of_cmd);
		Scanner sc = new Scanner(process.getInputStream());
		sc.useDelimiter("\\A");
		System.out.println(sc.next());
		sc.close();
	}
    
    /**
	 * Uses the localhost's socket information to produce a section in the SavedData.txt file to identify the system which this method has been ran on
	 * 
	 * @throws UnknownHostException
	 */
	protected static void getSocketInformation() throws UnknownHostException {
		InetAddress IP;
		IP = InetAddress.getLocalHost();
		String HostName = IP.getHostName();
		String PrivateIPAddress = IP.getHostAddress();
		String DomainName = IP.getCanonicalHostName();

		System.out.println("Socket Information");
		System.out.println("Domain Name is:  " + DomainName);
		System.out.println("Private IP Addtess is:  " + PrivateIPAddress);
		System.out.println("Host Name is:  " + HostName);
	}
}
