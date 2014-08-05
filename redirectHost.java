/**
 * redirectHost.java
 *
 * PURPOSE:			The redirect host. It will return the IP address of one of two servers in 
 *					round robin fashion to a client. If one server is down then
 *					the IP address of the active server will be returned. If both servers are down
 *					then the IP address 0.0.0.0 will be returned to the client.
 *
 * PLATFORM:		Linux
 *
 */

import java.net.*;
import java.io.*;

public class redirectHost {

    public static void main(String[] args) {

		final int NUM_SERVERS = 2; 
		String out = "";
		
		ServerSocket sock = null;    // server's master socket
		Socket cliSock = null;       // socket to the client

		String server1 = "falcon.cs.umanitoba.ca";
		String server2 = "osprey.cs.umanitoba.ca";
		
		String command1 = "ping -c 1 falcon";
		String command2 = "ping -c 1 osprey";

		InetAddress addr1 = null;
		InetAddress addr2 = null;

		try{
			addr1 = InetAddress.getByName(server1); //ip addresses of servers
			addr2 = InetAddress.getByName(server2);
		}
		catch(Exception e){
			
		}
		
		String command[] = {command1, command2};
		String ipAddress[] = {addr1.getHostAddress(), addr2.getHostAddress()};
		int pos = 0;

		System.out.println("\nRedirect Host Starting\nViewing Ping Results:\n");
		
		try {
			InetAddress addr = InetAddress.getLocalHost();
			sock = new ServerSocket(3111, 3, addr); // create server socket:
			                                      // on port 3111, with
		  	                                          // backlog of 3, on
			                                      // on this machine
		} catch (Exception e) {
			System.out.println("Creation of ServerSocket failed.");
			System.exit(1);
		}
		
		while(true){

			try {
				cliSock = sock.accept(); //continuously accept connections from clients
			} catch (Exception e) {
				System.out.println("Accept failed.");
				System.exit(1);
			}

			boolean serverUp = false;
			int serverDownCounter = 0;
			
			while(serverDownCounter < NUM_SERVERS && serverUp == false){

				try{
					//do a ping call to the server
					Runtime runtime = Runtime.getRuntime();
					Process process = runtime.exec(command[pos]);
			

					BufferedReader br = new BufferedReader(new InputStreamReader(process.getInputStream()));

					while((out = br.readLine()) != null){
			
						System.out.println(out);
				
						if(out.indexOf("1 received") != -1){
							serverUp = true;
						}
					}
				}
				catch(Exception e){
					System.out.println("Error in ping command");
				}
		
				//if ping successful
				if(serverUp){
		
					try{
			
						PrintWriter writer = new PrintWriter(cliSock.getOutputStream(), true);
						writer.println(ipAddress[pos]);
					}
					catch(Exception e){
		
					}
		
				}
				else{
				
					serverDownCounter ++;
					
					//if both servers are down
					if(serverDownCounter == NUM_SERVERS){
						try{
			
							PrintWriter writer = new PrintWriter(cliSock.getOutputStream(), true);
							writer.println("0.0.0.0");
						}
						catch(Exception e){
		
						}
					}
				}
		
				//use pos to do round robin
				pos ++;
				if(pos == NUM_SERVERS){
					pos = 0;
				}
			
			}
		
			try{
				cliSock.close();
			}
			catch(Exception e){
		
			}
		}
    }
}
