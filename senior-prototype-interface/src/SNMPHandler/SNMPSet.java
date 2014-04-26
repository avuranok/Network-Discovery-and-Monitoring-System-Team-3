package SNMPHandler;

import org.snmp4j.CommunityTarget;
import org.snmp4j.PDU;
import org.snmp4j.Snmp;
import org.snmp4j.TransportMapping;
import org.snmp4j.event.ResponseEvent;
import org.snmp4j.event.ResponseListener;
import org.snmp4j.mp.SnmpConstants;
import org.snmp4j.smi.*;
import org.snmp4j.transport.DefaultUdpTransportMapping;
 
public class SNMPSet {
 
	public static void main(String host,String community,String strOID,int Value)
	{
		//String host,String community,String strOID,int Value
	
	/*String host = "192.168.1.20";
	String community= "public";
	String strOID= "2.17.7.1.4.3.1.2.109";
	int Value = 4;*/
		
		System.out.println(host);
		System.out.println(community);

		System.out.println(strOID);

		System.out.println(Value);


  host= host+"/"+"161";
  Address tHost = GenericAddress.parse(host);
  Snmp snmp;
  try {
    TransportMapping transport = new DefaultUdpTransportMapping();
    snmp = new Snmp(transport);
    transport.listen();
    CommunityTarget target = new CommunityTarget();
    target.setCommunity(new OctetString(community));
    target.setAddress(tHost);
    target.setRetries(2);
    target.setTimeout(5000);
    target.setVersion(SnmpConstants.version1); //Set the correct SNMP version here
    PDU pdu = new PDU();
    //Depending on the MIB attribute type, appropriate casting can be done here
    pdu.add(new VariableBinding(new OID(strOID), new Integer32(Value))); 
    pdu.setType(PDU.SET);
    ResponseListener listener = new ResponseListener() {
      public void onResponse(ResponseEvent event) {
        PDU strResponse;
        String result;
        ((Snmp)event.getSource()).cancel(event.getRequest(), this);
        strResponse = event.getResponse();
        if (strResponse!= null) {
          result = strResponse.getErrorStatusText();
          System.out.println("Set Status is: "+result);
        }
      }};
      snmp.send(pdu, target, null, listener);
      System.out.println("assigned");
      snmp.close();
  } catch (Exception e) {
    e.printStackTrace();
  }
}
}