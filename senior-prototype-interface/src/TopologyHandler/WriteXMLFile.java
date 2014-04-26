package TopologyHandler;


import java.io.File;
import java.io.OutputStream;

import javax.swing.text.html.parser.DocumentParser;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import NDMSMain.LldpDiscovery;
import NDMSMain.functions;
import dbHandler.SQLiteJDBC;
import prefuse.data.expression.Function;
 
public class WriteXMLFile {
	
	static String ROUTER_IMAGE = "data/images/router.png";
	static String SWITCH_IMAGE = "data/images/switch.png";
	static String CLOUD_IMAGE = "data/images/Cloud.png";
	static String UNKNOWN_IMAGE = "data/images/accessPoint.png";
	 
	
	public static void main() {
		
		String deviceType = null;
		String deviceModel = null;
		
		SQLiteJDBC sql = new SQLiteJDBC();
		sql.connect("deviceList");
		
		LldpDiscovery ldp= new LldpDiscovery();
	  try {
 
		DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
 
		
		
		// root elements
		
		
		Document doc = docBuilder.newDocument();
		
		
		//doc.setTextContent("<?xml version=\"1.0\" encoding=\"UTF-8\"?><graphml xmlns=\"http://graphml.graphdrawing.org/xmlns\"><graph edgedefault=\"undirected\">");
		Element graphele = doc.createElement("graphml");
		doc.appendChild(graphele);
		
		
		graphele.setAttribute("xmlns", "http://graphml.graphdrawing.org/xmlns");
		
		Element graph = doc.createElement("graph");

		graphele.appendChild(graph);
		
		graph.setAttribute("edgedefault", "undirected");
		
		Element dataSchema = doc.createElement("key");		
		graph.appendChild(dataSchema);
		dataSchema.setAttribute("attr.type","string");
		dataSchema.setAttribute("attr.name","name");
		dataSchema.setAttribute("for","node");
		dataSchema.setAttribute("id","name");
		
		Element dataSchemaKey = doc.createElement("key");
		
		graph.appendChild(dataSchemaKey);
		
		
		dataSchemaKey.setAttribute("attr.type","string");
		dataSchemaKey.setAttribute("attr.name","type");
		dataSchemaKey.setAttribute("for","node");
		dataSchemaKey.setAttribute("id","type");
		
		
		
		for (int i=0; i <ldp.lldpNeigName.size(); i++)
		{
		
			//System.out.println("for for for for fro ");
		Element edge1 = doc.createElement("edge");
		
		edge1.setAttribute("source", ldp.lldpHostName.get(i).trim());
		
		edge1.setAttribute("target", ldp.lldpNeigName.get(i).trim());
		graph.appendChild(edge1);
		
		}
		
		
		
		
		//NODE CREATE
		for (int i=0; i< ldp.topologyNodeId.size(); i++)
		{
			
		Element rootElement = doc.createElement("node");
		
		graphele.appendChild(rootElement);
		graph.appendChild(rootElement);
		
		rootElement.setAttribute("id", ldp.topologyNodeId.get(i).trim());
 
		// staff elements
		Element staff = doc.createElement("data");
		rootElement.appendChild(staff);
 
 
		staff.setAttribute("key", "name");
		staff.appendChild(doc.createTextNode(ldp.topologyNodeId.get(i).trim()));
		rootElement.appendChild(staff);
		
		Element data2 = doc.createElement("data");
		rootElement.appendChild(data2);
		
		

		
		deviceModel = ldp.deviceModel.get(0).trim();
		System.out.println("DEvuce model"+deviceModel);
		deviceType = sql.loadDevice(deviceModel);
		
		//System.out.println("DEvice Type"+ deviceType);
		
		if(deviceType==null)
		{
			deviceType = UNKNOWN_IMAGE;
		}
		else if(deviceType.equalsIgnoreCase("Switch"))
		{
			deviceType = SWITCH_IMAGE;
		}
		else if (deviceType.equalsIgnoreCase("Router"))
		{
			deviceType = ROUTER_IMAGE;
			
		}
		else if (deviceType.equalsIgnoreCase("unkownDevice"))
		{
			deviceType = UNKNOWN_IMAGE;
			
		}
		
		
		
		data2.setAttribute("key", "type");
		data2.appendChild(doc.createTextNode(deviceType));
		rootElement.appendChild(data2);
		
		
		}// END OF NODE CREATE 
		
 		// write the content into xml file
		TransformerFactory transformerFactory = TransformerFactory.newInstance();
		Transformer transformer = transformerFactory.newTransformer();
		DOMSource source = new DOMSource(doc);
		StreamResult result = new StreamResult(new File("topology.xml"));
 
		
		
		// Output to console for testing
		
		// StreamResult result = new StreamResult(System.out);

		transformer.transform(source, result);
 
		System.out.println("File saved!");
		
		DrawTopology.main();
 
	  } catch (ParserConfigurationException pce) {
		pce.printStackTrace();
	  } catch (TransformerException tfe) {
		tfe.printStackTrace();
	  } catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	}
}