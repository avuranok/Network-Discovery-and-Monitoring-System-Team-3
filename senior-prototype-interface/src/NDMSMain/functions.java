package NDMSMain;
import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JOptionPane;

import org.snmp4j.CommunityTarget;
import org.snmp4j.PDU;
import org.snmp4j.Snmp;
import org.snmp4j.TransportMapping;
import org.snmp4j.event.ResponseEvent;
import org.snmp4j.mp.SnmpConstants;
import org.snmp4j.smi.Address;
import org.snmp4j.smi.OID;
import org.snmp4j.smi.OctetString;
import org.snmp4j.smi.UdpAddress;
import org.snmp4j.smi.VariableBinding;
import org.snmp4j.transport.DefaultUdpTransportMapping;

import SNMPHandler.SNMPManager;
import SNMPHandler.SNMPWalk;
import TopologyHandler.WriteXMLFile;




public class functions {
	
	
	public static int hostCount = 0;
	public static String snmpCheck = null;
	public static String devicePid = null;  //Product Id
	public static String LLDP_OID = "1.0.8802.1.1.2.1.4.1.1";
	public static ArrayList<String> vlanIDs = new ArrayList<String>();
	static String detailsOid = null;

	
	public static ArrayList<String> lldpAll = new ArrayList<String>();
	
	
	static SNMPWalk snmpWalk = new SNMPWalk();
	private static final String IP_PATTERN = 
	        "^([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\." +
	        "([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\." +
	        "([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\." +
	        "([01]?\\d\\d?|2[0-4]\\d|25[0-5])$";

	/**
	 * To determine IP adress validation
	 * @param ip
	 * @return boolean- IP Valid or not
	 */
	public static boolean validateIp(final String ip){          

	      Pattern pattern = Pattern.compile(IP_PATTERN);
	      Matcher matcher = pattern.matcher(ip);
	      return matcher.matches();             
	}

	/**
	 * Crating CVS file for testing the LLDP result
	 * @throws IOException 
	 */
//	public static final void createCSV() {
//
//		    try {
//		        FileWriter fos = new FileWriter(
//		                "test.txt");
//		        PrintWriter out = new PrintWriter(fos);
//
//		        for (int i = 0; i < lldpHostInt.size(); i++) {
//
//		            out.write(lldpHostName.get(i)+"/t"+
//		            		lldpHostInt.get(i)+"/t"+
//		            		lldpNeigInt.get(i)+"/t"+
//		            		lldpNeigMac.get(i)+"/t"+
//		            		lldpNeigName.get(i)+"/n");
//		            
//
//		        }
//		        out.close();
//
//		    } catch (Exception e) {
//
//		    }
//		}
	public void showAlert(String s){
		   JOptionPane.showMessageDialog(null, s);
		}
	
	public static final ArrayList<String> getVlans(String input, String walkOid) throws IOException{
		frame1 fra1 = new frame1();

		ArrayList<String> vlanIDs = new ArrayList<String>();

		//String walkOid = "1.3.6.1.4.1.9.5.1.9.3.1.3";
		String walkOutput = null;
		String[] walkLineOut = null;
		String[] equalSplitter = null;
	
		
		
			walkOutput = snmpWalk.main(input, walkOid);
	
		
	
			System.out.println("entered");
			walkLineOut= walkOutput.split("\n");
		
			int ind =0;
			for(int k=0;k<walkLineOut.length;k++)
			{
			
			equalSplitter = walkLineOut[k].split("=");
			System.out.println(equalSplitter[1].trim());
			if(!vlanIDs.contains(equalSplitter[1].trim()))
			{
				vlanIDs.add(equalSplitter[1].trim());
			}
		
		
		}
		
	
		return vlanIDs;
	}
	public int getInterfaceCount(String ip)
	{
		String walkOid = "1.3.6.1.4.1.9.9.68.1.2.2.1";
		String walkOutput=null;
		String[] walkLineOut = null;
		String[] equalSplitter = null;
		int intCount = 0;
		
		walkOutput = snmpWalk.main(ip, walkOid);
		walkLineOut= walkOutput.split("\n");
		
		int ind =0;
		for(int k=0;k<walkLineOut.length;k++)
		{
		
		equalSplitter = walkLineOut[k].split("=");
		if(equalSplitter[0].trim().startsWith("1.3.6.1.4.1.9.9.68.1.2.2.1.1"))
		{
			intCount++;
		}
		
		}
		return intCount;
	}
	
	
	
	public static final String nextIpAddress(final String input) {
	    final String[] tokens = input.split("\\.");
	    if (tokens.length != 4)
	        throw new IllegalArgumentException();
	    for (int i = tokens.length - 1; i >= 0; i--) {
	        final int item = Integer.parseInt(tokens[i]);
	        if (item < 255) {
	            tokens[i] = String.valueOf(item + 1);
	            for (int j = i + 1; j < 4; j++) {
	                tokens[j] = "0";
	            }
	            break;
	        }
	    }
	    return new StringBuilder()
	    .append(tokens[0]).append('.')
	    .append(tokens[1]).append('.')
	    .append(tokens[2]).append('.')
	    .append(tokens[3])
	    .toString();
	}
	public static final String ScanOpeation(String ip, String lastIp)
	{
		Process p = null;
		Runtime rt = Runtime.getRuntime();
		
		InetAddress inet;
		String result = " ";
		
		int up = 0;
		
		
		try {
			
		
				//System.out.println(System.getProperty("user.language"));
				//p = rt.exec("/usr/bin/xterm "+"ping -w 1 -n 1 "+ip);ping -c 1 
				//p = rt.exec("ping -c 1 "+ip);
				//ExecuteShellComand obj = new ExecuteShellComand();
				
				//obj.main(ip);
			
			if(System.getProperty("os.name").startsWith("W") || System.getProperty("os.name").startsWith("w"))
			{
				
				p = rt.exec("cmd.exe /c"+"ping -w 1 -n 1 "+ip);
				String output=null;
				BufferedReader inp = new BufferedReader(new InputStreamReader(p.getInputStream()));
		        
				String line = "";
		        
		        while ((line=inp.readLine())!=null)
		        {
		        	output+="\n"+line;
		        }
		       // System.out.println("///////////////****");
		       // System.out.println(output);
		        System.out.println("***Test Up/Down***");
		        result = parseOutput2(output);
		      

				
				
				
			}
			else{
				
				ExecuteShellComand obj = new ExecuteShellComand();
				result = obj.main(ip);
				
				
				
			}
				
		      
		       
	  
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		
		
		
		
		return result;
		
	}
	

	
	
	
	public static final String parseOutput(final String input) {
		
		final String[] lines = input.split("\n");
		final String[] packetLine = lines[6].split("=");
		final String isSent = packetLine[2].substring(1,2);
		
		return isSent;
		
	
	}
	
	public static final String parseOutput2(final String input) {
		
		

		final String[] lines = input.split("\n");
		final String[] thirdLine ;
		if(lines[3].contains(":"))
		{
			thirdLine = lines[3].split(":");
			
			if(thirdLine[1].startsWith(" b"))
			{
				
				
				return "up";
			}
			else
			{
				return "connotreachable";
			}
			
		}
		else
			return "down";
	
	}
	public static final String arpQuery(String ip)
	{
		Process p = null;
		Runtime rt = Runtime.getRuntime();
		InetAddress inet;
		String result = " ";
		String output="";
		int up = 0;
		try {
		
				//System.out.println(System.getProperty("user.language"));
				p = rt.exec("cmd.exe /c"+"nbtstat -a "+ip);		
				
				BufferedReader inp = new BufferedReader(new InputStreamReader(p.getInputStream()));
		        
				String line = "";
		        
		        while ((line=inp.readLine())!=null)
		        {
		        	output+="\n"+line;
		        }
		   
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
		return output;
		}
	
	public static final  String parseOutputArp(final String input) {
		
		final String[] lines = input.split("\n");
		String subResult="";
		String result="";
		//System.out.println("input::::::" + input);
		if(!input.startsWith("\nNo ARP"))
		{
			final String[] macLine = lines[4].split("-");
			//System.out.println("MAc Line:::" +macLine.toString());
			
			subResult = macLine[0].substring(macLine[0].length()-2,macLine[0].length());
			
			result = subResult+":"+macLine[1]+":"+macLine[2]+":"+macLine[3]+":"+macLine[4]+":"+macLine[5].substring(0,2);
			
			//System.out.println("RESULT:::::" + result.toString());
			
			return result;
		}
		else 
			return "No ARP";
		
		
	}
	public static int iki_ip_arasindaki_fark(String start,String end)
	{
		int [] res=new int[4];
		int [] ip_start = new int[4];
		int [] ip_end = new int[4];
		
		String[] parts_start= start.split("\\.");
		String[] parts_end=end.split("\\.");
		int birinci=0;//birinci oktet
		int ikinci=0;//ikinci oktet
		int ucuncu=0;//ucuncu oktet
		int dorduncu=0;//dorduncu oktet
		int sonuc=0;
		int i=0;
		if(i==0)
		{
			ip_start[i]=Integer.parseInt(parts_start[i]);
			ip_end[i]=Integer.parseInt(parts_end[i]);
			res[i]=ip_end[i]-ip_start[i];
			birinci=res[i]*255*255*255;
					if(birinci==0)
					{
						birinci=birinci+0;//EGER ILK OKTELER AYNI ISE HERHANGI BIR EKLEM ISLEMI YAPMA KI TOPLAM HOST SAYISINI BULMAYA CALISIRKEN DORDUNCU OKTET UZERINDEN BIR TANE DAHA FAZLA SAYI EKLEME ISLEMI YAPMASIN
					}
					else
					{
						birinci=birinci+1;//EGER ILK OKTETLER AYNI ISE 1 ARTTIR KI TOPLAM HOST PROGRAM SAYISINI BULSUN
					}
			
			i++;
		}
		if(i==1)
		{
			ip_start[i]=Integer.parseInt(parts_start[i]);
			ip_end[i]=Integer.parseInt(parts_end[i]);
			res[i]=ip_end[i]-ip_start[i];
			ikinci=res[i]*255*255;
				if(ikinci==0)
				{
					ikinci=ikinci+0;//EGER ILK OKTELER AYNI ISE HERHANGI BIR EKLEM ISLEMI YAPMA KI TOPLAM HOST SAYISINI BULMAYA CALISIRKEN DORDUNCU OKTET UZERINDEN BIR TANE DAHA FAZLA SAYI EKLEME ISLEMI YAPMASIN
				}
				else
				{
					ikinci=ikinci+1;//EGER ILK OKTETLER AYNI ISE 1 ARTTIR KI TOPLAM HOST PROGRAM SAYISINI BULSUN
				}
			i++;
		}
		
		if(i==2)
		{
			ip_start[i]=Integer.parseInt(parts_start[i]);
			ip_end[i]=Integer.parseInt(parts_end[i]);
			res[i]=ip_end[i]-ip_start[i];
			ucuncu=res[i]*255;
			if(ucuncu==0)
			{
				ucuncu=ucuncu+0;//EGER ILK OKTELER AYNI ISE HERHANGI BIR EKLEM ISLEMI YAPMA KI TOPLAM HOST SAYISINI BULMAYA CALISIRKEN DORDUNCU OKTET UZERINDEN BIR TANE DAHA FAZLA SAYI EKLEME ISLEMI YAPMASIN
			}
			else
			{
				ucuncu=ucuncu+1;//EGER ILK OKTETLER AYNI ISE 1 ARTTIR KI TOPLAM HOST PROGRAM SAYISINI BULSUN
			}
			i++;
		}
		
		if(i==3)
		{
			ip_start[i]=Integer.parseInt(parts_start[i]);
			ip_end[i]=Integer.parseInt(parts_end[i]);
			res[i]=ip_end[i]-ip_start[i];
			dorduncu=res[i]+1;
		}
		
		sonuc=birinci+ikinci+ucuncu+dorduncu;
		return sonuc;
				
	}
	
	
	public String snmpGet(String host, String community, String strOID) {
		  String strResponse="";
		  ResponseEvent response;
		  Snmp snmp;
		  try {
		    OctetString community1 = new OctetString(community);
		    host= host+"/"+"161";
		    Address tHost = new UdpAddress(host);
		    TransportMapping transport = new DefaultUdpTransportMapping();
		    transport.listen();
		    CommunityTarget comtarget = new CommunityTarget();
		    comtarget.setCommunity(community1);
		    comtarget.setVersion(SnmpConstants.version1);
		    comtarget.setAddress(tHost);
		    comtarget.setRetries(2);
		    comtarget.setTimeout(5000);
		    PDU pdu = new PDU();
		    pdu.add(new VariableBinding(new OID(strOID)));
		    pdu.setType(PDU.GET); 
		    snmp = new Snmp(transport);
		    response = snmp.get(pdu,comtarget);
		    if(response != null) {
		      if(response.getResponse().getErrorStatusText().equalsIgnoreCase("Success")) {
		        PDU pduresponse=response.getResponse();
		        strResponse=pduresponse.getVariableBindings().firstElement().toString();
		        if(strResponse.contains("=")) {
		          int len = strResponse.indexOf("=");
		          strResponse=strResponse.substring(len+1, strResponse.length());
		        }
		      }
		    } else {
		      System.out.println("Looks like a TimeOut occured ");
		    }
		    snmp.close();
		  } catch(Exception e) {
		     e.printStackTrace();
		  }
		//System.out.println("Response="+strResponse);
		 return strResponse;
		}
	
	
	public static final String parseSnmpOutput(String input) {
		
		final String[] lines = input.split("\n");
		final String[] packetLine = lines[6].split("=");
		final String isSent = packetLine[2].substring(1,2);
		
		return isSent;
		
	
	}
	
public static final String getDeviceType(String input) {
		
	String type = null;
		

	return type;

	}
	
	
	
	
	

}
