package NDMSMain;

import java.io.IOException;
import java.util.ArrayList;

import org.snmp4j.smi.OID;

import SNMPHandler.SNMPManager;
import SNMPHandler.SNMPWalk;


public class CdpDiscovery {
	
	public static String CDP_OID = "1.3.6.1.4.1.9.9.23";
	public static String cdpSnmpCheck = null; //Host Name
	public static String cdpDevicePid = null;  //Product Id
	public static String cdpTemp= null;  //Product Id

	
	public static ArrayList<String> cdpTopologyNodeId = new ArrayList<String>();
	public static ArrayList<String> cdpHostName = new ArrayList<String>();
	public static ArrayList<String> cdpHostInt = new ArrayList<String>();
	public static ArrayList<String> cdpNeigName = new ArrayList<String>();
	public static ArrayList<String> cdpNeigInt = new ArrayList<String>();
	public static ArrayList<String> cdpDeviceModel = new ArrayList<String>();
	
	public static void main() {
		// TODO Auto-generated method stub
		
		frame1 out = new frame1();
		String cdpTestWalk="";  // SMNP WALK OUTPUT
	
		String walkLineOut[]=null; // Output of SNMP WALK will be splitted into it
		String equalSplitter[]=null; // Split walkLineOut as = symbol
	
		
		//To test CDP DISCOVERY
		//String[] demoIpArray = {"192.168.1.5",	"192.168.1.10",	"192.168.1.20",	"192.168.1.35"};
	
		for(int i=0; i<out.ipler.size();i++)
		{
			System.out.println(out.ipler.size());
			
			try {
			//System Name   1.3.6.1.2.1.47.1.1.1.1.13.1
			cdpDevicePid= SNMPManager.main("udp:"+out.ipler.get(i).toString()+"/161","1.0.8802.1.1.2.1.5.4795.1.2.7.0");
			System.out.println("11111 DevicePidDDDDDDDDDDDDDDDDDDDDDDDDDDD: "+ cdpDevicePid);
		
			if(cdpDevicePid.startsWith("no"))
			{
				cdpDevicePid= SNMPManager.main("udp:"+out.ipler.get(i).toString()+"/161","1.3.6.1.2.1.47.1.1.1.1.13.1");
				System.out.println("22222 DevicePidDDDDDDDDDDDDDDDDDDDDDDDDDDD: "+ cdpDevicePid);

				if(cdpDevicePid.startsWith("no"))
				{
					cdpDevicePid="unkownDevice";
					System.out.println("33333 DevicePidDDDDDDDDDDDDDDDDDDDDDDDDDDD: "+ cdpDevicePid);

				}
			}
//			else
//			{
//				cdpDevicePid="unkownDevice";
//				System.out.println("44444 DevicePidDDDDDDDDDDDDDDDDDDDDDDDDDDD: "+ cdpDevicePid);
//
//			}
			
				System.out.println("Device PID :"+cdpDevicePid);
		
			if(cdpDevicePid.trim().startsWith("WS") || cdpDevicePid.trim().equalsIgnoreCase("unkowndevice"))
			{
				cdpSnmpCheck = SNMPManager.main("udp:"+out.ipler.get(i).toString()+"/161","1.3.6.1.4.1.9.9.23.1.3.4.0");

				// To determine node count and Id s
				cdpTopologyNodeId.add(cdpSnmpCheck);
			
			
				
				
				
				System.out.println("DevicePidDDDDDDDDDDDDDDDDDDDDDDDDDDD: "+ cdpDevicePid);
			
		
			cdpTestWalk =  SNMPWalk.main(out.ipler.get(i).toString(),CDP_OID);
			
			
			walkLineOut= cdpTestWalk.split("\n");
			System.out.println("satir 0"+walkLineOut[0].toString());
			System.out.println("satir 1"+walkLineOut[1].toString());
			int ind =0;
			for(int k=0;k<walkLineOut.length;k++)
			{
				System.out.println("Bu for mu");
				System.out.println(walkLineOut.length);
				equalSplitter = null;
				equalSplitter = walkLineOut[k].split("=");
				//System.out.println("===Hostlar");
				//System.out.println("ind 0: "+esit[ind]);
				
				/*
				 * IF check and Parse to get host names of neighbours
				 */
				
				if(equalSplitter[ind].startsWith("1.3.6.1.4.1.9.9.23.1.2.1.1.6"))
				{
					cdpNeigName.add(equalSplitter[1].trim());
					cdpHostName.add(cdpSnmpCheck.trim());
					
					
				}
				/*
				 * Else if to check and parse result of the Neighbour Interfaces
				 */
				else if(equalSplitter[ind].startsWith("1.3.6.1.4.1.9.9.23.1.2.1.1.7"))
				{
					cdpNeigInt.add(equalSplitter[1].trim());
					cdpTemp = equalSplitter[ind].substring(equalSplitter[ind].length()-5,equalSplitter[ind].length()-3).trim();
					cdpHostInt.add(cdpTemp);
					
				}
				else if(equalSplitter[ind].startsWith("1.3.6.1.4.1.9.9.23.1.2.1.1.8")) //Neigbour Device PID
				{
					System.out.println("NEIGBOUR PID ELSE++++++++++++++++");
					if(!cdpDeviceModel.contains(equalSplitter[1]))
					{					
						System.out.println("CONTAINS++++++++++++++++");

						cdpDeviceModel.add(equalSplitter[1].substring(7,equalSplitter[1].length()));
					}
				}
				
			}
			}
			
			else   // CDP DESTEKLEMEYEN CIHAZLAR ICIN COZUM BULUNACAK
			{
				
				
				System.out.println("*********Not a Cisco Device******");
				
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}//
			
		}// End of ModelList Element for Loop
		
		
		
		for (int i=0;i<cdpNeigName.size();i++)
		{
			if(!cdpTopologyNodeId.contains(cdpNeigName.get(i).trim()))
			{
				cdpTopologyNodeId.add(cdpNeigName.get(i));
			}
		}
		
	 //Test to show output of arrayLists - cdp
		
		System.out.println("---HostName---");
		for(int i=0; i<cdpHostName.size();i++)
		{
			System.out.println(cdpHostName.get(i).toString());
		}
		System.out.println("---HostInt---");
		for(int i=0; i<cdpHostInt.size();i++)
		{
			
			System.out.println(cdpHostInt.get(i).toString());
			
		}
		System.out.println("---NeighInt---");
		for(int i=0; i<cdpHostInt.size();i++)
		{
			
			System.out.println(cdpNeigInt.get(i).toString());
			
		}
		System.out.println("---NeighName---");
		for(int i=0; i<cdpNeigName.size();i++)
		{
			System.out.println(cdpNeigName.get(i).toString());
		}
	
		System.out.println("---NodeId---");
		for(int i=0; i<cdpTopologyNodeId.size();i++)
		{
			System.out.println(cdpTopologyNodeId.get(i).toString());
		}
		System.out.println("---DEVICE--");
		for(int i=0; i<cdpDeviceModel.size();i++)
		{
			System.out.println(cdpDeviceModel.get(i).toString());
		}

	}
	
	

}
