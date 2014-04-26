package NDMSMain;

 
import java.io.BufferedReader;
import java.io.InputStreamReader;
 
public class ExecuteShellComand {
 
	public static String main(String ip) {
 
		ExecuteShellComand obj = new ExecuteShellComand();
 
		String domainName = ip; //"192.168.2.1";
 
		//System.out.println(System.getProperty("os.name"));
	
		//in mac oxs
		String command = "ping -W 1 -c 1 -s 1 " + domainName;
 
		//in windows
		//String command = "ping -n 3 " + domainName;
 
		String output = obj.executeCommand(command);
 
		System.out.println(output);
		output = obj.linuxParse(output);
		
		
		
		return output;
 
	}
 
	private String executeCommand(String command) {
 
		StringBuffer output = new StringBuffer();
 
		Process p;
		try {
			p = Runtime.getRuntime().exec(command);
			p.waitFor();
			BufferedReader reader = 
                            new BufferedReader(new InputStreamReader(p.getInputStream()));
 
                        String line = "";			
			while ((line = reader.readLine())!= null) {
				output.append(line + "\n");
			}
 
		} catch (Exception e) {
			e.printStackTrace();
		}
 
		return output.toString();
 
	}
	private String linuxParse(String output) {
	
		String[] secondLine = output.split("\n");
		if(secondLine[1].startsWith("9"))
		{
			return "up";
			}
		else
			return "down";
			
 
	}
 
}