package SNMPHandler;
import java.io.IOException;

import org.snmp4j.CommunityTarget;
import org.snmp4j.PDU;
import org.snmp4j.Snmp;
import org.snmp4j.Target;
import org.snmp4j.TransportMapping;
import org.snmp4j.event.ResponseEvent;
import org.snmp4j.mp.SnmpConstants;
import org.snmp4j.smi.Address;
import org.snmp4j.smi.GenericAddress;
import org.snmp4j.smi.OID;
import org.snmp4j.smi.OctetString;
import org.snmp4j.smi.VariableBinding;
import org.snmp4j.transport.DefaultUdpTransportMapping;

public class SNMPManager {

	Snmp snmp = null;
	String address = null;

	/**
	 * Constructor
	 * 
	 * @param add
	 */
	public SNMPManager(String add) {
		address = add;
	}

	public static String main(String ip, String oids) throws IOException {
		/**
		 * Port 161 is used for Read and Other operations Port 162 is used for
		 * the trap generation
		 */
		SNMPManager client = new SNMPManager(ip);
		client.start();
		/**
		 * OID - .1.3.6.1.2.1.1.1.0 => SysDec 
		 * OID - .1.3.6.1.2.1.1.5.0 => SysName ÇAlýþýyor
		 * OID - .1.3.6.1.2.1.1.3.0 => Uptime
		 * OID -1.3.6.1.2.1.11.15.0 => Count of Get, 
		 * OID - 1.3.6.1.6.3.1.1.6.1.0 ==> one of the set operation.--Kullanma kafan karýþmasýn.
		 * 
		 * 
		 * => MIB explorer will be usefull here, as discussed in previous
		 * article
		 */
		
		String sysDescr = client.getAsString(new OID(oids));
		return sysDescr;
	}

	/**
	 * Start the Snmp session. If you forget the listen() method you will not
	 * get any answers because the communication is asynchronous and the
	 * listen() method listens for answers.
	 * 
	 * @throws IOException
	 */
	public void start() throws IOException {
		TransportMapping transport = new DefaultUdpTransportMapping();
		snmp = new Snmp(transport);
		// Do not forget this line!
		transport.listen();
	}

	/**
	 * Method which takes a single OID and returns the response from the agent
	 * as a String.
	 * 
	 * @param oid
	 * @return
	 * @throws IOException
	 */
	public String getAsString(OID oid) {
		ResponseEvent event;
		 
		try {
			event = get(new OID[] { oid });
			if(event.getResponse()!=null)
				return event.getResponse().get(0).getVariable().toString();
			else
				return "SNMP does not supported";
						
		} catch (IOException e) {
			
			
			// TODO Auto-generated catch block
			e.printStackTrace();
			
			return "SNMP does not supported";
		}
		
	}

	/**
	 * This method is capable of handling multiple OIDs
	 * 
	 * @param oids
	 * @return
	 * @throws IOException
	 */
	public ResponseEvent get(OID oids[]) throws IOException {
		PDU pdu = new PDU();
		for (OID oid : oids) {
			pdu.add(new VariableBinding(oid));
		}
		pdu.setType(PDU.GET);
		ResponseEvent event = snmp.send(pdu, getTarget(), null);
		if (event != null) {
			return event;
		}
		throw new RuntimeException("GET timed out");
	}

	/**
	 * This method returns a Target, which contains information about where the
	 * data should be fetched and how.
	 * 
	 * @return
	 */
	private Target getTarget() {
		Address targetAddress = GenericAddress.parse(address);
		CommunityTarget target = new CommunityTarget();
		target.setCommunity(new OctetString("public"));
		target.setAddress(targetAddress);
		target.setRetries(2);
		target.setTimeout(1500);
		target.setVersion(SnmpConstants.version2c);
		return target;
	}

}