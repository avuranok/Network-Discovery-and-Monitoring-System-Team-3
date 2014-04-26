package NDMSMain;

import java.io.IOException;
import java.util.ArrayList;

import SNMPHandler.SNMPManager;
import SNMPHandler.SNMPWalk;
import TopologyHandler.WriteXMLFile;

public class LldpDiscovery {
	
	static SNMPWalk t1 = new SNMPWalk();
	public static String LLDP_OID = "1.0.8802.1.1.2.1.4.1.1";
	public static String snmpCheck = null; //MODEL
	public static String devicePid = null;  //Product Id

	public static ArrayList<String> topologyNodeId = new ArrayList<String>();
	public static ArrayList<String> lldpHostName = new ArrayList<String>();
	public static ArrayList<String> lldpHostInt = new ArrayList<String>();
	public static ArrayList<String> lldpNeigName = new ArrayList<String>();
	public static ArrayList<String> lldpNeigInt = new ArrayList<String>();
	public static ArrayList<String> lldpNeigMac = new ArrayList<String>();
	public static ArrayList<String> deviceModel = new ArrayList<String>();


	public static void main() {
		
		System.out.println("LLDP GELDIIIII");

		frame1 out = new frame1();
		String testWalk="";
	
		String satir[]=null;
		String esit[]=null;
		String hostInt = null;
		for(int i=0; i<out.ipler.size();i++)
		{
			try {
				
				devicePid = SNMPManager.main("udp:"+out.ipler.get(i).toString()+"/161","1.0.8802.1.1.2.1.5.4795.1.2.7.0");
			
			if(!devicePid.startsWith("no"))
			{
				// To determine node count and Id s
			
				snmpCheck= SNMPManager.main("udp:"+out.ipler.get(i).toString()+"/161","1.3.6.1.2.1.1.5.0");
				deviceModel.add(devicePid);
				topologyNodeId.add(snmpCheck);
				
			
		
			testWalk =  t1.main(out.ipler.get(i).toString(),LLDP_OID);
			
			
			satir= testWalk.split("\n");
			//System.out.println("satir 0"+satir[0].toString());
			//System.out.println("satir 1"+satir[1].toString());
			int ind =0;
			for(int k=0;k<satir.length;k++)
			{
				
				esit = null;
				esit = satir[k].split("=");
				//System.out.println("===Hostlar");
				//System.out.println("ind 0: "+esit[ind]);
				if(esit[ind].startsWith("1.0.8802.1.1.2.1.4.1.1.7.0") && !esit[ind].equals("1.0.8802.1.1.2.1.4.1.1.7.0.1.2 "))
				{
					

					hostInt = esit[ind].substring(esit[ind].length()-5, esit[ind].length()-3);
					//System.out.println("===Start-4.1.1.7.0 - genel cnt: "+ arrayCnts +" fa0-"+hostInt);
					if(!hostInt.startsWith("."))
					{
						//System.out.println("===Start-4.1.1.7.0 - .li cnt: " + arrayCnts +"fa0-"+hostInt);
						lldpHostInt.add("fa0-"+hostInt);
						
					}
					else
					{
						//System.out.println("===Start-4.1.1.7.0 - .siz cnt: " + arrayCnts +"fa0-"+hostInt);
						lldpHostInt.add("fa0-"+hostInt.substring(hostInt.length()-1,hostInt.length()));	
						
					}
					
					//System.out.println("=== LLDPNeigh cnt:" + arrayCnts + esit[1]);
					lldpNeigInt.add(esit[1]);
					
					
				}
				else if(esit[ind].startsWith("1.0.8802.1.1.2.1.4.1.1.9.0"))
				{
					//System.out.println("=== NeighNAme cnt:" + arrayCnts + esit[1]);
					if(!esit[1].equals(" "))
					{
						lldpNeigName.add(esit[1]);
						lldpHostName.add(snmpCheck);
					}
					
					
					
				//	lldpHostName.add(out.asd);
				
				}
				else if(esit[ind].startsWith("1.0.8802.1.1.2.1.4.1.1.5.0") && !esit[ind].equals("1.0.8802.1.1.2.1.4.1.1.5.0.1.2 "))
				{
					
					lldpNeigMac.add(esit[1]);
				}
			}
			}
			
			else   // SNMP DESTEKLEMEYEN CIHAZLAR ICIN COZUM BULUNACAK
			{
				
				
				System.out.println("*********There is unknown device here!!!! ******");
				
				
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}//
		}
		
		
		System.out.println("---HostName---");
		for(int i=0; i<lldpHostName.size();i++)
		{
			System.out.println(lldpHostName.get(i).toString());
		}
		System.out.println("---HostInt---");
		for(int i=0; i<lldpHostInt.size();i++)
		{
			
			System.out.println(lldpHostInt.get(i).toString());
			
		}
		System.out.println("---NeighInt---");
		for(int i=0; i<lldpHostInt.size();i++)
		{
			
			System.out.println(lldpNeigInt.get(i).toString());
			
		}
		System.out.println("---NeighName---");
		for(int i=0; i<lldpNeigName.size();i++)
		{
			System.out.println(lldpNeigName.get(i).toString());
		}
		System.out.println("---NeighMac---");
		for(int i=0; i<lldpHostInt.size();i++)
		{
			System.out.println(lldpNeigMac.get(i).toString());
		}
		System.out.println("---NodeId---");
		for(int i=0; i<topologyNodeId.size();i++)
		{
			System.out.println(topologyNodeId.get(i).toString());
		}
		
		for (int i=0;i<lldpNeigName.size();i++)
		{
			if(!topologyNodeId.contains(lldpNeigName.get(i).trim()))
			{
				topologyNodeId.add(lldpNeigName.get(i));
			}
		}
		
	
		System.out.println("===LLDPNODE ID");
		for(int i=0; i<topologyNodeId.size();i++)
		{
			System.out.println(topologyNodeId.get(i).toString());
		}	
	
		

		
	//	createCSV();
		
		//WriteXMLFile.main();
		
		
		/*lldpHostName.clear();
		lldpHostInt.clear();
		lldpNeigInt.clear();
		lldpNeigMac.clear();
		lldpNeigName.clear();
		topologyNodeId.clear();
		deviceModel.clear();*/
		
		 
	}

}
