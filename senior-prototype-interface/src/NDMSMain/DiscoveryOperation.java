package NDMSMain;

import java.util.Collection;
import java.util.Collections;

import TopologyHandler.WriteXMLFile;

public class DiscoveryOperation {

	public static void main() {
		// TODO Auto-generated method stub
		LldpDiscovery ldp = new LldpDiscovery();
		CdpDiscovery cdp = new CdpDiscovery();
		 
		cdp.main();
		ldp.main();
		int count=0;
		
		for(int i=0 ; i<cdp.cdpTopologyNodeId.size();i++)
		{
			if(!ldp.topologyNodeId.contains(cdp.cdpTopologyNodeId.get(i)))
			{
				ldp.topologyNodeId.add(cdp.cdpTopologyNodeId.get(i));
			}
		}
		
		for(int i=0 ; i<cdp.cdpDeviceModel.size();i++)
		{
			if(!ldp.deviceModel.contains(cdp.cdpDeviceModel.get(i)))
			{
				ldp.deviceModel.add(cdp.cdpDeviceModel.get(i));
			}
		}
		
		for(int i=0; i< cdp.cdpHostName.size() ;i++)
		{
			if(!ldp.lldpHostName.contains(cdp.cdpHostName.get(i)))
			{
				System.out.println("LLDP CDP CONTAINS");
				ldp.lldpHostName.add(cdp.cdpHostName.get(i));
				ldp.lldpHostInt.add(cdp.cdpHostInt.get(i));
				ldp.lldpNeigName.add(cdp.cdpNeigName.get(i));
				ldp.lldpNeigInt.add(cdp.cdpNeigInt.get(i));
				
				count= Collections.frequency(cdp.cdpHostName,  cdp.cdpHostName.get(i).toString());
				if(count >1)
				{
					for(int k=i+1 ; k<count+i; k++)
					{
						ldp.lldpHostName.add(cdp.cdpHostName.get(i));
						ldp.lldpHostInt.add(cdp.cdpHostInt.get(i));
						ldp.lldpNeigName.add(cdp.cdpNeigName.get(i));
						ldp.lldpNeigInt.add(cdp.cdpNeigInt.get(i));

					}
					
				}
			}		 
			
		}
		for(int i=0; i< cdp.cdpNeigName.size() ;i++)
		{
			if(!ldp.lldpHostName.contains(cdp.cdpNeigName.get(i)))
			{
				System.out.println("LLDP CDP CONTAINS");
				ldp.lldpHostName.add(cdp.cdpHostName.get(i));
				ldp.lldpHostInt.add(cdp.cdpHostInt.get(i));
				ldp.lldpNeigName.add(cdp.cdpNeigName.get(i));
				ldp.lldpNeigInt.add(cdp.cdpNeigInt.get(i));

				
				count= Collections.frequency(cdp.cdpHostName,  cdp.cdpHostName.get(i).toString());
				if(count >1)
				{
					for(int k=i+1 ; k<count+i; k++)
					{
						ldp.lldpHostName.add(cdp.cdpHostName.get(i));
						ldp.lldpHostInt.add(cdp.cdpHostInt.get(i));
						ldp.lldpNeigName.add(cdp.cdpNeigName.get(i));
						ldp.lldpNeigInt.add(cdp.cdpNeigInt.get(i));

					}
					
				}
			}
		}

		for(int i=0; i<ldp.lldpHostName.size();i++)
		{
			System.out.println("HOST NAME:    "+ ldp.lldpHostName.get(i));
		}
	
		for(int i=0; i<ldp.lldpNeigName.size();i++)
		{
			System.out.println("NEIGH NAME:    "+ ldp.lldpNeigName.get(i));
		}
		for(int i=0; i<ldp.lldpNeigInt.size();i++)
		{
			System.out.println("NEIGH INT:    "+ ldp.lldpNeigInt.get(i));
		}
		for(int i=0; i<ldp.deviceModel.size();i++)
		{
			System.out.println("DEVICE MODEL:     "+ ldp.deviceModel.get(i));
		}
		for(int i=0; i<ldp.topologyNodeId.size();i++)
		{
			System.out.println("CIZIM NODE:    "+ ldp.topologyNodeId.get(i));
		}
		
		WriteXMLFile.main();

	}

}
