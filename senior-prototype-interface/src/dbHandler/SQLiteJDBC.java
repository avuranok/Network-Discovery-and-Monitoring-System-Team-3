package dbHandler;



import java.awt.List;
import java.sql.*;
import java.util.ArrayList;

public class SQLiteJDBC

{
	Connection c = null;
	Statement stmt = null;
	public void connect(String dbName)
	{ 
		
		try {
			c = DriverManager.getConnection("jdbc:sqlite:"+dbName+".db");
			System.out.println("Opened database successfully--- Connect");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	     	
	}
	
	public void createtable()
	{
		Statement stmt = null;

		try {
			stmt = c.createStatement();
			String sql = "CREATE TABLE DEVICE_INFO " +
	                   "(ID INTEGER PRIMARY KEY  AUTOINCREMENT," +
	                   " IPS           TEXT  , " +
	                   " HOSTNAME           TEXT ,  " + 
	                   " DETAIL          TEXT   , " + 
	                   " UPTIME           TEXT  , " + 
	                   " FLASH          TEXT  , " +
	                   " CPU          TEXT  ) " ;
	                   
	      stmt.executeUpdate(sql);
	      stmt.close();
	      System.out.println("Table created successfully");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	      
	}
	public void createtable2()
	{
		Statement stmt = null;

		try {
			stmt = c.createStatement();
			String sql = "CREATE TABLE device_list " +
	                   "(model TEXT," +
	                   " type  TEXT  ) " ;
	                   
	      stmt.executeUpdate(sql);
	      stmt.close();
	      System.out.println("Table created successfully");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	      
	}
	
	public void insertData2()
	{
		stmt = null;
		
		String sql = "INSERT INTO \"device_list\" VALUES('WS-C2960X-48FPD-L','Switch');"
						+ "INSERT INTO \"device_list\" VALUES('WS-C2960X-48LPD-L','Switch');"
						+ "INSERT INTO \"device_list\" VALUES('WS-C2960X-48TD-L','Switch');"
						+ "INSERT INTO \"device_list\" VALUES('WS-C2960X-24PD-L','Switch');"
						+ "INSERT INTO \"device_list\" VALUES('WS-C2960X-24TD-L','Switch');"
						+ "INSERT INTO \"device_list\" VALUES('WS-C2960X-48FPS-L','Switch');"
						+ "INSERT INTO \"device_list\" VALUES('WS-C2960X-48LPS-L','Switch');"
						+ "INSERT INTO \"device_list\" VALUES('WS-C2960X-48TS-L','Switch');"
						+ "INSERT INTO \"device_list\" VALUES('WS-C2960X-24PS-L','Switch');"
						+ "INSERT INTO \"device_list\" VALUES('WS-C2960X-24PSQ-L','Switch');"
						+ "INSERT INTO \"device_list\" VALUES('WS-C2960X-24TS-L','Switch');"
						+ "INSERT INTO \"device_list\" VALUES('WS-C2960X-48TS-LL','Switch');"
						+ "INSERT INTO \"device_list\" VALUES('WS-C2960X-24TS-LL','Switch');"
						+ "INSERT INTO \"device_list\" VALUES('WS-C2960XR-48FPD-I','Switch');"
						+ "INSERT INTO \"device_list\" VALUES('WS-C2960XR-48LPD-I','Switch');"
						+ "INSERT INTO \"device_list\" VALUES('WS-C2960XR-48TD-I','Switch');"
						+ "INSERT INTO \"device_list\" VALUES('WS-C2960XR-24PD-I','Switch');"
						+ "INSERT INTO \"device_list\" VALUES('WS-C2960XR-24TD-I','Switch');"
						+ "INSERT INTO \"device_list\" VALUES('WS-C2960XR-48FPS-I','Switch');"
						+ "INSERT INTO \"device_list\" VALUES('WS-C2960XR-48LPS-I','Switch');"
						+ "INSERT INTO \"device_list\" VALUES('WS-C2960XR-48TS-I','Switch');"
						+ "INSERT INTO \"device_list\" VALUES('WS-C2960XR-24PS-I','Switch');"
						+ "INSERT INTO \"device_list\" VALUES('WS-C2960XR-24TS-I','Switch');"
						+ "INSERT INTO \"device_list\" VALUES('2960C-8TC-L','Switch');"
						+ "INSERT INTO \"device_list\" VALUES('2960C-8TC-S','Switch');"
						+ "INSERT INTO \"device_list\" VALUES('2960CPD-8TT-L','Switch');"
						+ "INSERT INTO \"device_list\" VALUES('2960C-8PC-L','Switch');"
						+ "INSERT INTO \"device_list\" VALUES('2960CPD-8PT-L','Switch');"
						+ "INSERT INTO \"device_list\" VALUES('2960C-12PC-L','Switch');"
						+ "INSERT INTO \"device_list\" VALUES('2960CG-8TC-L','Switch');"
						+ "INSERT INTO \"device_list\" VALUES('WS-C2960CPD-8PT-L','Switch');"
						+ "INSERT INTO \"device_list\" VALUES('WS-C2960C-8PC-L','Switch');"
						+ "INSERT INTO \"device_list\" VALUES('WS-C2960C-12PC-L','Switch');"
						+ "INSERT INTO \"device_list\" VALUES('WS-C2960CG-8TC-L','Switch');"
						+ "INSERT INTO \"device_list\" VALUES('WS-C2960CPD-8TT-L','Switch');"
						+ "INSERT INTO \"device_list\" VALUES('WS-C2960C-8TC-L','Switch');"
						+ "INSERT INTO \"device_list\" VALUES('WS-C2960C-8TC-S','Switch');"
						+ "INSERT INTO \"device_list\" VALUES('WS-C2960C-8PT-L','Switch');"
						+ "INSERT INTO \"device_list\" VALUES('WS-C2960C-12PT-L','Switch');"
						+ "INSERT INTO \"device_list\" VALUES('WS-C3560CG-8TC-S','Switch');"
						+ "INSERT INTO \"device_list\" VALUES('3560C-8PC-S','Switch');"
						+ "INSERT INTO \"device_list\" VALUES('3560C-12PC-S','Switch');"
						+ "INSERT INTO \"device_list\" VALUES('3560CG-8TC-S','Switch');"
						+ "INSERT INTO \"device_list\" VALUES('3560CG-8PC-S','Switch');"
						+ "INSERT INTO \"device_list\" VALUES('3560CPD-8PT-S','Switch');"
						+ "INSERT INTO \"device_list\" VALUES('WS-C3560CPD-8PT-S','Switch');"
						+ "INSERT INTO \"device_list\" VALUES('WS-C3560C-12PC-S','Switch');"
						+ "INSERT INTO \"device_list\" VALUES('WS-C3560C-8PC-S','Switch');"
						+ "INSERT INTO \"device_list\" VALUES('WS-C3560CG-8PC-S','Switch');"
						+ "INSERT INTO \"device_list\" VALUES('WS-C3750X-48PF-E','Switch');"
						+ "INSERT INTO \"device_list\" VALUES('WS-C3750X-48T-E','Switch');"
						+ "INSERT INTO \"device_list\" VALUES('WS-C3750G-24TS-S1U','Switch');"
						+ "INSERT INTO \"device_list\" VALUES('WS-C3750G-24TS-E1U','Switch');"
						+ "INSERT INTO \"device_list\" VALUES('WS-C3750G-48TS-S','Switch');"
						+ "INSERT INTO \"device_list\" VALUES('WS-C3750G-48TS-E','Switch');"
						+ "INSERT INTO \"device_list\" VALUES('WS-C3750-24TS-S','Switch');"
						+ "INSERT INTO \"device_list\" VALUES('WS-C3750-24TS-E','Switch');"
						+ "INSERT INTO \"device_list\" VALUES('WS-C3750-24FS-S','Switch');"
						+ "INSERT INTO \"device_list\" VALUES('WS-C3750-48TS-S','Switch');"
						+ "INSERT INTO \"device_list\" VALUES('WS-C3750-48TS-E','Switch');"
						+ "INSERT INTO \"device_list\" VALUES('WS-C3750G-12S-S','Switch');"
						+ "INSERT INTO \"device_list\" VALUES('WS-C3750G-12S-E','Switch');"
						+ "INSERT INTO \"device_list\" VALUES('WS-C3750G-12S-SD','Switch');"
						+ "INSERT INTO \"device_list\" VALUES('WS-C3750G-24PS-S','Switch');"
						+ "INSERT INTO \"device_list\" VALUES('WS-C3750G-24PS-E','Switch');"
						+ "INSERT INTO \"device_list\" VALUES('WS-C3750G-48PS-S','Switch');"
						+ "INSERT INTO \"device_list\" VALUES('WS-C3750G-48PS-E','Switch');"
						+ "INSERT INTO \"device_list\" VALUES('WS-C3750-24PS-S','Switch');"
						+ "INSERT INTO \"device_list\" VALUES('WS-C3750-24PS-E','Switch');"
						+ "INSERT INTO \"device_list\" VALUES('WS-C3750-48PS-S','Switch');"
						+ "INSERT INTO \"device_list\" VALUES('WS-C3750-48PS-E','Switch');"
						+ "INSERT INTO \"device_list\" VALUES('WS-C3750G-24WS-S25','Switch');"
						+ "INSERT INTO \"device_list\" VALUES('WS-C3750G-24WS-S50','Switch');"
						+ "INSERT INTO \"device_list\" VALUES('WS-C3750X-24T-L','Switch');"
						+ "INSERT INTO \"device_list\" VALUES('WS-C3750X-48T-L','Switch');"
						+ "INSERT INTO \"device_list\" VALUES('WS-C3750X-48P-L','Switch');"
						+ "INSERT INTO \"device_list\" VALUES('WS-C3750X-48P-L','Switch');"
						+ "INSERT INTO \"device_list\" VALUES('WS-C3750X-48PF-L','Switch');"
						+ "INSERT INTO \"device_list\" VALUES('WS-C3750X-12S-S','Switch');"
						+ "INSERT INTO \"device_list\" VALUES('WS-C3750X-24P-S','Switch');"
						+ "INSERT INTO \"device_list\" VALUES('WS-C3750X-24S-S','Switch');"
						+ "INSERT INTO \"device_list\" VALUES('WS-C3750X-24T-S','Switch');"
						+ "INSERT INTO \"device_list\" VALUES('WS-C3750X-48P-S','Switch');"
						+ "INSERT INTO \"device_list\" VALUES('WS-C3750X-48PF-S','Switch');"
						+ "INSERT INTO \"device_list\" VALUES('WS-C3750X-48T-S','Switch');"
						+ "INSERT INTO \"device_list\" VALUES('WS-C3750X-12S-E','Switch');"
						+ "INSERT INTO \"device_list\" VALUES('WS-C3750X-24P-E','Switch');"
						+ "INSERT INTO \"device_list\" VALUES('WS-C3750X-24S-E','Switch');"
						+ "INSERT INTO \"device_list\" VALUES('WS-C3750X-24T-E','Switch');"
						+ "INSERT INTO \"device_list\" VALUES('WS-C3750X-48P-E','Switch');"
						+ "INSERT INTO \"device_list\" VALUES('C3750G-48TS','Switch');"
						+ "INSERT INTO \"device_list\" VALUES('C3750G-48PS','Switch');"
						+ "INSERT INTO \"device_list\" VALUES('C3750G-24TS','Switch');"
						+ "INSERT INTO \"device_list\" VALUES('C3750G-24WS','Switch');"
						+ "INSERT INTO \"device_list\" VALUES('C3750G-24T','Switch');"
						+ "INSERT INTO \"device_list\" VALUES('C3750G-12S','Switch');"
						+ "INSERT INTO \"device_list\" VALUES('C3750G-12S-SD','Switch');"
						+ "INSERT INTO \"device_list\" VALUES('C3750-24TS','Switch');"
						+ "INSERT INTO \"device_list\" VALUES('C3750-24FS','Switch');"
						+ "INSERT INTO \"device_list\" VALUES('C3750-48TS','Switch');"
						+ "INSERT INTO \"device_list\" VALUES('C3750-24PS','Switch');"
						+ "INSERT INTO \"device_list\" VALUES('C3750-48PS','Switch');"
						+ "INSERT INTO \"device_list\" VALUES('C3750G-24TS-1U','Switch');"
						+ "INSERT INTO \"device_list\" VALUES('C3750G-24PS','Switch');"
						+ "INSERT INTO \"device_list\" VALUES('WS-C3850-48U','Switch');"
						+ "INSERT INTO \"device_list\" VALUES('WS-C3850-24U','Switch');"
						+ "INSERT INTO \"device_list\" VALUES('WS-C3850-48F','Switch');"
						+ "INSERT INTO \"device_list\" VALUES('WS-C3850-24T','Switch');"
						+ "INSERT INTO \"device_list\" VALUES('WS-C3850-48T','Switch');"
						+ "INSERT INTO \"device_list\" VALUES('WS-C3850-24P','Switch');"
						+ "INSERT INTO \"device_list\" VALUES('WS-C3850-48P','Switch');"
						+ "INSERT INTO \"device_list\" VALUES('C3850-48-S-E','Switch');"
						+ "INSERT INTO \"device_list\" VALUES('C3850-24-S-E','Switch');"
						+ "INSERT INTO \"device_list\" VALUES('C3850-48-L-E','Switch');"
						+ "INSERT INTO \"device_list\" VALUES('C3850-NM-4-1G','Switch');"
						+ "INSERT INTO \"device_list\" VALUES('C3850-NM-2-10G','Switch');"
						+ "INSERT INTO \"device_list\" VALUES('C3850-NM-4-10G','Switch');"
						+ "INSERT INTO \"device_list\" VALUES('C3850-24-L-S','Switch');"
						+ "INSERT INTO \"device_list\" VALUES('C3850-48-L-S','Switch');"
						+ "INSERT INTO \"device_list\" VALUES('C3850-24-L-E','Switch');"
						+ "INSERT INTO \"device_list\" VALUES('L-LIC-CT3850-UPG','Switch');"
						+ "INSERT INTO \"device_list\" VALUES('LIC-CT3850-UPG','Switch');"
						+ "INSERT INTO \"device_list\" VALUES('L-C3850-48-S-E','Switch');"
						+ "INSERT INTO \"device_list\" VALUES('L-C3850-24-L-S','Switch');"
						+ "INSERT INTO \"device_list\" VALUES('L-C3850-48-L-S','Switch');"
						+ "INSERT INTO \"device_list\" VALUES('L-C3850-24-L-E','Switch');"
						+ "INSERT INTO \"device_list\" VALUES('L-C3850-48-L-E','Switch');"
						+ "INSERT INTO \"device_list\" VALUES('L-C3850-24-S-E','Switch');"
						+ "INSERT INTO \"device_list\" VALUES('USB-X4GB-E','Switch');"
						+ "INSERT INTO \"device_list\" VALUES('MEM-X45-2GB-E','Switch');"
						+ "INSERT INTO \"device_list\" VALUES('WS-C4510RE-S7+96V+q','Switch');"
						+ "INSERT INTO \"device_list\" VALUES('WS-C4507RES6L+96V+','Switch');"
						+ "INSERT INTO \"device_list\" VALUES('C4503-E','Switch');"
						+ "INSERT INTO \"device_list\" VALUES('C4506-E','Switch');"
						+ "INSERT INTO \"device_list\" VALUES('C4507R+E','Switch');"
						+ "INSERT INTO \"device_list\" VALUES('C4507R-E','Switch');"
						+ "INSERT INTO \"device_list\" VALUES('C4510R+E','Switch');"
						+ "INSERT INTO \"device_list\" VALUES('C4510R-E','Switch');"
						+ "INSERT INTO \"device_list\" VALUES('WS-X45-SUP7-E','Switch');"
						+ "INSERT INTO \"device_list\" VALUES('WS-X4748-RJ45V+E','Switch');"
						+ "INSERT INTO \"device_list\" VALUES('WS-X4712-SFP+E','Switch');"
						+ "INSERT INTO \"device_list\" VALUES('WS-C4507R+E','Switch');"
						+ "INSERT INTO \"device_list\" VALUES('WS-C4510R+E','Switch');"
						+ "INSERT INTO \"device_list\" VALUES('BN-CF2-SBA+K9','Switch');"
						+ "INSERT INTO \"device_list\" VALUES('L-C4500E-LB-IP=','Switch');"
						+ "INSERT INTO \"device_list\" VALUES('L-C4500E-IP-ES=','Switch');"
						+ "INSERT INTO \"device_list\" VALUES('L-C4500E-IP-ES=','Switch');"
						+ "INSERT INTO \"device_list\" VALUES('L-C4500E-LB-ES=','Switch');"
						+ "INSERT INTO \"device_list\" VALUES('C7201','Router');"
						+ "INSERT INTO \"device_list\" VALUES('C7206','Router');"
						+ "INSERT INTO \"device_list\" VALUES('C7204VXR','Router');"
						+ "INSERT INTO \"device_list\" VALUES('ASR 1001','Router');"
						+ "INSERT INTO \"device_list\" VALUES('ASR 1002','Router');"
						+ "INSERT INTO \"device_list\" VALUES('ASR 1002-X','Router');"
						+ "INSERT INTO \"device_list\" VALUES('ASR 1004','Router');"
						+ "INSERT INTO \"device_list\" VALUES('ASR 1006','Router');"
						+ "INSERT INTO \"device_list\" VALUES('ASR 1013','Router');"
						+ "INSERT INTO \"device_list\" VALUES('C892 ISR','Router');"
						+ "INSERT INTO \"device_list\" VALUES('C891 ISR','Router');"
						+ "INSERT INTO \"device_list\" VALUES('C888 ISR','Router');"
						+ "INSERT INTO \"device_list\" VALUES('C887V ISR','Router');"
						+ "INSERT INTO \"device_list\" VALUES('C887VA ISR','Router');"
						+ "INSERT INTO \"device_list\" VALUES('C887VA-W ISR','Router');"
						+ "INSERT INTO \"device_list\" VALUES('C881 ISR','Router');"
						+ "INSERT INTO \"device_list\" VALUES('C880-3G ISR','Router');"
						+ "INSERT INTO \"device_list\" VALUES('C880G ISR','Router');"
						+ "INSERT INTO \"device_list\" VALUES('C867VAE ISR','Router');"
						+ "INSERT INTO \"device_list\" VALUES('C866VAE ISR','Router');"
						+ "INSERT INTO \"device_list\" VALUES('C861 ISR','Router');"
						+ "INSERT INTO \"device_list\" VALUES('C819 ISR','Router');"
						+ "INSERT INTO \"device_list\" VALUES('C812 CiFi','Router');"
						+ "INSERT INTO \"device_list\" VALUES('C892FSP','Router');"
						+ "INSERT INTO \"device_list\" VALUES('C881W ISR','Router');"
						+ "INSERT INTO \"device_list\" VALUES('C1811 ISR','Router');"
						+ "INSERT INTO \"device_list\" VALUES('C1812 ISR','Router');"
						+ "INSERT INTO \"device_list\" VALUES('C1801 ISR','Router');"
						+ "INSERT INTO \"device_list\" VALUES('C1802 ISR','Router');"
						+ "INSERT INTO \"device_list\" VALUES('C1803 ISR','Router');"
						+ "INSERT INTO \"device_list\" VALUES('C1941 ISR','Router');"
						+ "INSERT INTO \"device_list\" VALUES('C1941W ISR','Router');"
						+ "INSERT INTO \"device_list\" VALUES('C1921 ISR','Router');"
						+ "INSERT INTO \"device_list\" VALUES('C1905 ISR','Router');"
						+ "INSERT INTO \"device_list\" VALUES('C2801 ISR','Router');"
						+ "INSERT INTO \"device_list\" VALUES('C2811 ISR','Router');"
						+ "INSERT INTO \"device_list\" VALUES('C2821 ISR','Router');"
						+ "INSERT INTO \"device_list\" VALUES('C2851 ISR','Router');"
						+ "INSERT INTO \"device_list\" VALUES('C2901 ISR','Router');"
						+ "INSERT INTO \"device_list\" VALUES('C2911 ISR','Router');"
						+ "INSERT INTO \"device_list\" VALUES('C2921 ISR','Router');"
						+ "INSERT INTO \"device_list\" VALUES('C2951 ISR','Router');"
						+ "INSERT INTO \"device_list\" VALUES('C3845 ISR','Router');"
						+ "INSERT INTO \"device_list\" VALUES('C3825 ISR','Router');"
						+ "INSERT INTO \"device_list\" VALUES('C3945 ISR','Router');"
						+ "INSERT INTO \"device_list\" VALUES('3945E ISR','Router');"
						+ "INSERT INTO \"device_list\" VALUES('C3925 ISR','Router');"
						+ "INSERT INTO \"device_list\" VALUES('C3925E ISR','Router');"
						+ "INSERT INTO \"device_list\" VALUES('C4400 ISR','Router');"
						+ "INSERT INTO \"device_list\" VALUES('C720VXR','Router');"
						+ "INSERT INTO \"device_list\" VALUES('C1601-R','Router');"
						+ "INSERT INTO \"device_list\" VALUES('C1602-R','Router');"
						+ "INSERT INTO \"device_list\" VALUES('C1603-R','Router');"
						+ "INSERT INTO \"device_list\" VALUES('C1604-R','Router');"
						+ "INSERT INTO \"device_list\" VALUES('C1605-R','Router');"
						+ "INSERT INTO \"device_list\" VALUES('C1701-K9','Router');"
						+ "INSERT INTO \"device_list\" VALUES('C1711-VPN/K9','Router');"
						+ "INSERT INTO \"device_list\" VALUES('C1712-VPN/K9','Router');"
						+ "INSERT INTO \"device_list\" VALUES('C1721','Router');"
						+ "INSERT INTO \"device_list\" VALUES('C1721-ADSL','Router');"
						+ "INSERT INTO \"device_list\" VALUES('C1721-ADSLI','Router');"
						+ "INSERT INTO \"device_list\" VALUES('C1721-SHDSL','Router');"
						+ "INSERT INTO \"device_list\" VALUES('C1721VPN/K9','Router');"
						+ "INSERT INTO \"device_list\" VALUES('C1721-VPN/K9-A','Router');"
						+ "INSERT INTO \"device_list\" VALUES('C1751','Router');"
						+ "INSERT INTO \"device_list\" VALUES('C1751-V','Router');"
						+ "INSERT INTO \"device_list\" VALUES('C1751-VPN/K9','Router');"
						+ "INSERT INTO \"device_list\" VALUES('C1751-VPN/K9A','Router');"
						+ "INSERT INTO \"device_list\" VALUES('C1760','Router');"
						+ "INSERT INTO \"device_list\" VALUES('C1760-ADSL','Router');"
						+ "INSERT INTO \"device_list\" VALUES('C1760-SHDSL','Router');"
						+ "INSERT INTO \"device_list\" VALUES('C1760-V','Router');"
						+ "INSERT INTO \"device_list\" VALUES('C1760-V-CCME','Router');"
						+ "INSERT INTO \"device_list\" VALUES('C1760-V-SRST','Router');"
						+ "INSERT INTO \"device_list\" VALUES('C1760-V3PN/K9','Router');"
						+ "INSERT INTO \"device_list\" VALUES('C1760-VPN/K9','Router');"
						+ "INSERT INTO \"device_list\" VALUES('C1760-VPN/K9-A','Router');"
						+ "INSERT INTO \"device_list\" VALUES('C1721-FRAD-1','Router');"
						+ "INSERT INTO \"device_list\" VALUES('C1801/K9','Router');"
						+ "INSERT INTO \"device_list\" VALUES('C1811/K9','Router');"
						+ "INSERT INTO \"device_list\" VALUES('C1812/K9','Router');"
						+ "INSERT INTO \"device_list\" VALUES('C1841','Router');"
						+ "INSERT INTO \"device_list\" VALUES('C1841-T1','Router');"
						+ "INSERT INTO \"device_list\" VALUES('C1811W-AG-A/K9','Router');"
						+ "INSERT INTO \"device_list\" VALUES('ACS-1800-RM-19','Router');"
						+ "INSERT INTO \"device_list\" VALUES('C1841-SEC/K9','Router');"
						+ "INSERT INTO \"device_list\" VALUES('C1841-HSEC/K9','Router');"
						+ "INSERT INTO \"device_list\" VALUES('C1841-T1SEC/K9','Router');"
						+ "INSERT INTO \"device_list\" VALUES('C1811W-AG-A/K9','Router');"
						+ "INSERT INTO \"device_list\" VALUES('C1841-ADSL','Router');"
						+ "INSERT INTO \"device_list\" VALUES('C1841-ADSL-DG','Router');"
						+ "INSERT INTO \"device_list\" VALUES('C1841-ADSLI','Router');"
						+ "INSERT INTO \"device_list\" VALUES('C2612','Router');"
						+ "INSERT INTO \"device_list\" VALUES('C2612-DC','Router');"
						+ "INSERT INTO \"device_list\" VALUES('C2691','Router');"
						+ "INSERT INTO \"device_list\" VALUES('C2691-RPS','Router');"
						+ "INSERT INTO \"device_list\" VALUES('C2610XM','Router');"
						+ "INSERT INTO \"device_list\" VALUES('C2610XM-DC','Router');"
						+ "INSERT INTO \"device_list\" VALUES('C2611XM','Router');"
						+ "INSERT INTO \"device_list\" VALUES('C2611XM-V-CCME','Router');"
						+ "INSERT INTO \"device_list\" VALUES('C2620XM','Router');"
						+ "INSERT INTO \"device_list\" VALUES('C2620XM-DC','Router');"
						+ "INSERT INTO \"device_list\" VALUES('C2621XM','Router');"
						+ "INSERT INTO \"device_list\" VALUES('C2621XM-DC','Router');"
						+ "INSERT INTO \"device_list\" VALUES('C2651XM-DC','Router');"
						+ "INSERT INTO \"device_list\" VALUES('C2611XM-DC','Router');"
						+ "INSERT INTO \"device_list\" VALUES('C2650XM','Router');"
						+ "INSERT INTO \"device_list\" VALUES('C2650XM-DC','Router');"
						+ "INSERT INTO \"device_list\" VALUES('C2651XM','Router');"
						+ "INSERT INTO \"device_list\" VALUES('C2651XM-V','Router');"
						+ "INSERT INTO \"device_list\" VALUES('C2651XM-V-CCME','Router');"
						+ "INSERT INTO \"device_list\" VALUES('C2651XM-V-SRST','Router');"
						+ "INSERT INTO \"device_list\" VALUES('C2801','Router');"
						+ "INSERT INTO \"device_list\" VALUES('C2801-AC-IP','Router');"
						+ "INSERT INTO \"device_list\" VALUES('C2811','Router');"
						+ "INSERT INTO \"device_list\" VALUES('C2811-AC-IP','Router');"
						+ "INSERT INTO \"device_list\" VALUES('C2811-DC','Router');"
						+ "INSERT INTO \"device_list\" VALUES('C2821','Router');"
						+ "INSERT INTO \"device_list\" VALUES('C2821-AC-IP','Router');"
						+ "INSERT INTO \"device_list\" VALUES('C2821-DC','Router');"
						+ "INSERT INTO \"device_list\" VALUES('C2851','Router');"
						+ "INSERT INTO \"device_list\" VALUES('C2851-AC-IP','Router');"
						+ "INSERT INTO \"device_list\" VALUES('C2851-DC','Router');"
						+ "INSERT INTO \"device_list\" VALUES('C3620','Router');"
						+ "INSERT INTO \"device_list\" VALUES('C3640','Router');"
						+ "INSERT INTO \"device_list\" VALUES('C3620-DC','Router');"
						+ "INSERT INTO \"device_list\" VALUES('C3620-RPS','Router');"
						+ "INSERT INTO \"device_list\" VALUES('C3640-DC','Router');"
						+ "INSERT INTO \"device_list\" VALUES('C3640-RPS','Router');"
						+ "INSERT INTO \"device_list\" VALUES('C3661-AC','Router');"
						+ "INSERT INTO \"device_list\" VALUES('C3661-DC','Router');"
						+ "INSERT INTO \"device_list\" VALUES('C3662-AC','Router');"
						+ "INSERT INTO \"device_list\" VALUES('C3662-DC','Router');"
						+ "INSERT INTO \"device_list\" VALUES('C3725','Router');"
						+ "INSERT INTO \"device_list\" VALUES('C3745','Router');"
						+ "INSERT INTO \"device_list\" VALUES('C3725-V-CCME','Router');"
						+ "INSERT INTO \"device_list\" VALUES('C3725-V-CCME-A','Router');"
						+ "INSERT INTO \"device_list\" VALUES('C3745-V-CCME','Router');"
						+ "INSERT INTO \"device_list\" VALUES('C3745-V-CCME-A','Router');"
						+ "INSERT INTO \"device_list\" VALUES('C7204VXR 225 1FE','Router');"
						+ "INSERT INTO \"device_list\" VALUES('C7204VXR 300 1FE','Router');"
						+ "INSERT INTO \"device_list\" VALUES('C7204VXR 400 1FE','Router');"
						+ "INSERT INTO \"device_list\" VALUES('C7204VXR G1','Router');"
						+ "INSERT INTO \"device_list\" VALUES('C7206VXR 300 1FE','Router');"
						+ "INSERT INTO \"device_list\" VALUES('C7206VXR 400 1FE','Router');"
						+ "INSERT INTO \"device_list\" VALUES('C7206VXR 400 2FE','Router');"
						+ "INSERT INTO \"device_list\" VALUES('C7206VXR G1','Router');"
						+ "INSERT INTO \"device_list\" VALUES('C7206VXR-CH','Router');"
						+ "INSERT INTO \"device_list\" VALUES('J9559A','Switch');"
						+ "INSERT INTO \"device_list\" VALUES('J9560A','Switch');"
						+ "INSERT INTO \"device_list\" VALUES('JG708A','Switch');"
						+ "INSERT INTO \"device_list\" VALUES('J9561A','Switch');"
						+ "INSERT INTO \"device_list\" VALUES('J9661A','Switch');"
						+ "INSERT INTO \"device_list\" VALUES('J9662A','Switch');"
						+ "INSERT INTO \"device_list\" VALUES('J9663A','Switch');"
						+ "INSERT INTO \"device_list\" VALUES('JD986B','Switch');"
						+ "INSERT INTO \"device_list\" VALUES('J9664A','Switch');"
						+ "INSERT INTO \"device_list\" VALUES('J9802A','Switch');"
						+ "INSERT INTO \"device_list\" VALUES('J9803A','Switch');"
						+ "INSERT INTO \"device_list\" VALUES('J9660A','Switch');"
						+ "INSERT INTO \"device_list\" VALUES('J9800A','Switch');"
						+ "INSERT INTO \"device_list\" VALUES('J9801A','Switch');"
						+ "INSERT INTO \"device_list\" VALUES('JE009A','Switch');"
						+ "INSERT INTO \"device_list\" VALUES('JE007A','Switch');"
						+ "INSERT INTO \"device_list\" VALUES('JE008A','Switch');"
						+ "INSERT INTO \"device_list\" VALUES('JE006A','Switch');"
						+ "INSERT INTO \"device_list\" VALUES('JE005A','Switch');"
						+ "INSERT INTO \"device_list\" VALUES('JG348A','Switch');"
						+ "INSERT INTO \"device_list\" VALUES('JG538A','Switch');"
						+ "INSERT INTO \"device_list\" VALUES('JG536A','Switch');"
						+ "INSERT INTO \"device_list\" VALUES('JG540A','Switch');"
						+ "INSERT INTO \"device_list\" VALUES('JG537A','Switch');"
						+ "INSERT INTO \"device_list\" VALUES('JG539A','Switch');"
						+ "INSERT INTO \"device_list\" VALUES('J9726A','Switch');"
						+ "INSERT INTO \"device_list\" VALUES('J9727A','Switch');"
						+ "INSERT INTO \"device_list\" VALUES('J9728A','Switch');"
						+ "INSERT INTO \"device_list\" VALUES('J9729A','Switch');"
						+ "INSERT INTO \"device_list\" VALUES('J9836A','Switch');"
						+ "INSERT INTO \"device_list\" VALUES('J9772A','Switch');"
						+ "INSERT INTO \"device_list\" VALUES('J9773A','Switch');"
						+ "INSERT INTO \"device_list\" VALUES('J9774A','Switch');"
						+ "INSERT INTO \"device_list\" VALUES('J9778A','Switch');"
						+ "INSERT INTO \"device_list\" VALUES('J9779A','Switch');"
						+ "INSERT INTO \"device_list\" VALUES('J9780A','Switch');"
						+ "INSERT INTO \"device_list\" VALUES('J9775A','Switch');"
						+ "INSERT INTO \"device_list\" VALUES('J9776A','Switch');"
						+ "INSERT INTO \"device_list\" VALUES('J9777A','Switch');"
						+ "INSERT INTO \"device_list\" VALUES('J9781A','Switch');"
						+ "INSERT INTO \"device_list\" VALUES('J9782A','Switch');"
						+ "INSERT INTO \"device_list\" VALUES('J9783A','Switch');"
						+ "INSERT INTO \"device_list\" VALUES('J9623A','Switch');"
						+ "INSERT INTO \"device_list\" VALUES('J9624A','Switch');"
						+ "INSERT INTO \"device_list\" VALUES('J9625A','Switch');"
						+ "INSERT INTO \"device_list\" VALUES('J9626A','Switch');"
						+ "INSERT INTO \"device_list\" VALUES('J9627A','Switch');"
						+ "INSERT INTO \"device_list\" VALUES('JG632A','Switch');"
						+ "INSERT INTO \"device_list\" VALUES('JG619A','Switch');"
						+ "INSERT INTO \"device_list\" VALUES('JG608A','Switch');"
						+ "INSERT INTO \"device_list\" VALUES('JC099A','Switch');"
						+ "INSERT INTO \"device_list\" VALUES('JC100A','Switch');"
						+ "INSERT INTO \"device_list\" VALUES('JC103A','Switch');"
						+ "INSERT INTO \"device_list\" VALUES('JC104A','Switch');"
						+ "INSERT INTO \"device_list\" VALUES('JC105A','Switch');"
						+ "INSERT INTO \"device_list\" VALUES('JC101A','Switch');"
						+ "INSERT INTO \"device_list\" VALUES('JG225A','Switch');"
						+ "INSERT INTO \"device_list\" VALUES('JC654A','Switch');"
						+ "INSERT INTO \"device_list\" VALUES('JC655A','Switch');"
						+ "INSERT INTO \"device_list\" VALUES('JF431C','Switch');"
						+ "INSERT INTO \"device_list\" VALUES('JC652A','Switch');"
						+ "INSERT INTO \"device_list\" VALUES('JF430C','Switch');"
						+ "INSERT INTO \"device_list\" VALUES('JC653A','Switch');"
						+ "INSERT INTO \"device_list\" VALUES('JG782A','Switch');"
						+ "INSERT INTO \"device_list\" VALUES('J9264A','Switch');"
						+ "INSERT INTO \"device_list\" VALUES('J9265A','Switch');"
						+ "INSERT INTO \"device_list\" VALUES('J9452A','Switch');"
						+ "INSERT INTO \"device_list\" VALUES('JC106A','Switch');"
						+ "INSERT INTO \"device_list\" VALUES('JC102','Switch');"
						+ "INSERT INTO \"device_list\" VALUES('JG219A','Switch');"
						+ "INSERT INTO \"device_list\" VALUES('JG511A','Router');"
						+ "INSERT INTO \"device_list\" VALUES('JG513A','Router');"
						+ "INSERT INTO \"device_list\" VALUES('JG514A','Router');"
						+ "INSERT INTO \"device_list\" VALUES('JG515A','Router');"
						+ "INSERT INTO \"device_list\" VALUES('JG531A','Router');"
						+ "INSERT INTO \"device_list\" VALUES('JG512A','Router');"
						+ "INSERT INTO \"device_list\" VALUES('JG596A','Router');"
						+ "INSERT INTO \"device_list\" VALUES('JG516A','Router');"
						+ "INSERT INTO \"device_list\" VALUES('JG517A','Router');"
						+ "INSERT INTO \"device_list\" VALUES('JG518A','Router');"
						+ "INSERT INTO \"device_list\" VALUES('JG519A','Router');"
						+ "INSERT INTO \"device_list\" VALUES('JG520A','Router');"
						+ "INSERT INTO \"device_list\" VALUES('JG597A','Router');"
						+ "INSERT INTO \"device_list\" VALUES('JG665A','Router');"
						+ "INSERT INTO \"device_list\" VALUES('JG403A','Router');"
						+ "INSERT INTO \"device_list\" VALUES('JG402A','Router');"
						+ "INSERT INTO \"device_list\" VALUES('JG409A','Router');"
						+ "INSERT INTO \"device_list\" VALUES('JG406A','Router');"
						+ "INSERT INTO \"device_list\" VALUES('JG405A','Router');"
						+ "INSERT INTO \"device_list\" VALUES('JG404A','Router');"
						+ "INSERT INTO \"device_list\" VALUES('JD433A','Router');"
						+ "INSERT INTO \"device_list\" VALUES('JF231A','Router');"
						+ "INSERT INTO \"device_list\" VALUES('JF285A','Router');"
						+ "INSERT INTO \"device_list\" VALUES('JF640A','Router');"
						+ "INSERT INTO \"device_list\" VALUES('JF283A','Router');"
						+ "INSERT INTO \"device_list\" VALUES('JD663B','Router');"
						+ "INSERT INTO \"device_list\" VALUES('JF228A','Router');"
						+ "INSERT INTO \"device_list\" VALUES('JF812A','Router');"
						+ "INSERT INTO \"device_list\" VALUES('JF813A','Router');"
						+ "INSERT INTO \"device_list\" VALUES('JF814A','Router');"
						+ "INSERT INTO \"device_list\" VALUES('JF815A','Router');"
						+ "INSERT INTO \"device_list\" VALUES('JG207A','Router');"
						+ "INSERT INTO \"device_list\" VALUES('JG208A','Router');"
						+ "INSERT INTO \"device_list\" VALUES('JG411A','Router');"
						+ "INSERT INTO \"device_list\" VALUES('JG732A','Router');"
						+ "INSERT INTO \"device_list\" VALUES('JF816A','Router');"
						+ "INSERT INTO \"device_list\" VALUES('JG184A','Router');"
						+ "INSERT INTO \"device_list\" VALUES('JG182A','Router');"
						+ "INSERT INTO \"device_list\" VALUES('JG183A','Router');"
						+ "INSERT INTO \"device_list\" VALUES('JF233A','Router');"
						+ "INSERT INTO \"device_list\" VALUES('JF234A','Router');"
						+ "INSERT INTO \"device_list\" VALUES('JF284A','Router');"
						+ "INSERT INTO \"device_list\" VALUES('JF802A','Router');"
						+ "INSERT INTO \"device_list\" VALUES('JF235A','Router');"
						+ "INSERT INTO \"device_list\" VALUES('JF229A','Router');"
						+ "INSERT INTO \"device_list\" VALUES('JF803A','Router');"
						+ "INSERT INTO \"device_list\" VALUES('JF287A','Router');"
						+ "INSERT INTO \"device_list\" VALUES('JF230A','Router');"
						+ "INSERT INTO \"device_list\" VALUES('JF804A','Router');"
						+ "INSERT INTO \"device_list\" VALUES('JF801A','Router');"
						+ "INSERT INTO \"device_list\" VALUES('JD431A','Router');"
						+ "INSERT INTO \"device_list\" VALUES('JF239A','Router');"
						+ "INSERT INTO \"device_list\" VALUES('JF241A','Router');"
						+ "INSERT INTO \"device_list\" VALUES('JF807A','Router');"
						+ "INSERT INTO \"device_list\" VALUES('JF806A','Router');"
						+ "INSERT INTO \"device_list\" VALUES('JG209A','Router');"
						+ "INSERT INTO \"device_list\" VALUES('JF240A','Router');"
						+ "INSERT INTO \"device_list\" VALUES('JF808A','Router');"
						+ "INSERT INTO \"device_list\" VALUES('JG210A','Router');"
						+ "INSERT INTO \"device_list\" VALUES('JF237A','Router');"
						+ "INSERT INTO \"device_list\" VALUES('JF809A','Router');"
						+ "INSERT INTO \"device_list\" VALUES('JF236A','Router');"
						+ "INSERT INTO \"device_list\" VALUES('JF238A','Router');"
						+ "INSERT INTO \"device_list\" VALUES('JF817A','Router');"
						+ "INSERT INTO \"device_list\" VALUES('JG361A','Router');"
						+ "INSERT INTO \"device_list\" VALUES('JG362A','Router');"
						+ "INSERT INTO \"device_list\" VALUES('JG363A','Router');"
						+ "INSERT INTO \"device_list\" VALUES('JC176A','Router');"
						+ "INSERT INTO \"device_list\" VALUES('JC178B','Router');"
						+ "INSERT INTO \"device_list\" VALUES('JC177B','Router');"
						+ "INSERT INTO \"device_list\" VALUES('JC496A','Router');"
						+ "INSERT INTO \"device_list\" VALUES('WS-C2960-48TT-L','Switch');";

		
		System.out.println("sql:"+sql);
		      try {
		    	 
		    		stmt = c.createStatement();
		    	  
				stmt.executeUpdate(sql);
				
				 stmt.close();
			      c.close();
		      
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		     
	}
	public void insertData(String ip,String hostname,String detail,String uptime,String flash,String cpu)
	{
		stmt = null;
		
		String sql = "INSERT INTO DEVICE_INFO (IPS,HOSTNAME,DETAIL,UPTIME,FLASH,CPU) " +
		            "VALUES ('"
		            			+ip
		            			+"','"
		            			+hostname
		            			+"','"
		            			+detail
		            			+"','"
		            			+uptime
		            			+"','"
		            			+flash
		            			+"','"
		            			+cpu
		            			+"');"; 
		
		System.out.println("sql:"+sql);
		      try {
		    	 
		    		stmt = c.createStatement();
		    	  
				stmt.executeUpdate(sql);
				
				 stmt.close();
			      c.close();
		      
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		     
	}
	
	public List loadData(String filename)
	{
		
	
		String sDriver = "org.sqlite.JDBC";
        String Database = filename;
        String sJdbc = "jdbc:sqlite";
        String sDbUrl = sJdbc + ":" + Database;
        List ip_list = new List();
    	
        try {
        	Class.forName(sDriver);
        	c = DriverManager.getConnection(sDbUrl);
			
			
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	    try {
	      
	   
	      
	      
	      System.out.println("Opened database successfully");

	      stmt = c.createStatement();
	      ResultSet rs = stmt.executeQuery( "SELECT * FROM DEVICE_INFO" );
	      while ( rs.next() ) {
	         int id = rs.getInt("id");
	         String  ips = rs.getString("IPS");
	         
	         //IPS,HOSTNAME,DETAIL,UPTIME,FLASH,CPU
	         String hostname  = rs.getString("HOSTNAME");
	         String detail  = rs.getString("DETAIL");
	         String uptime  = rs.getString("UPTIME");
	         String flash  = rs.getString("FLASH");
	         String cpu  = rs.getString("CPU");
	         
	        
	         System.out.println( "ID = " + id );
	         System.out.println( "IPS = " + ips );
	         System.out.println( "HOSTNAME = " + hostname );
	         ip_list.add(ips);
	         ip_list.add(hostname);
	         ip_list.add(detail);
	         ip_list.add(uptime);
	         ip_list.add(flash);
	         ip_list.add(cpu);
	         
	      }
	      rs.close();
	      stmt.close();
	      c.close();
	    } catch ( Exception e ) {
	      System.err.println( e.getClass().getName() + ": " + e.getMessage() );
	      System.exit(0);
	    }
	    System.out.println("Operation done successfully");
		
	    return ip_list;
	}
	
	public String loadDevice(String model)
	{
		
	
		String sDriver = "org.sqlite.JDBC";
        String Database = "deviceList.db";
        String sJdbc = "jdbc:sqlite";
        String sDbUrl = sJdbc + ":" + Database;
       
    	
        try {
        	Class.forName(sDriver);
        	c = DriverManager.getConnection(sDbUrl);
			
			
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        String  dev_type= null;
	    try {
	    	
	      System.out.println("Opened database successfully -- Load Device");

	      stmt = c.createStatement();
	      ResultSet rs = stmt.executeQuery( "SELECT * FROM device_list where model="+"'"+model+"'");
	      while ( rs.next() ) {
	         dev_type = rs.getString("type");
	         
	         System.out.println("Type="+ dev_type);
	   
	         
	      }
	      rs.close();
	      stmt.close();
	      c.close();
	    } catch ( Exception e ) {
	    	System.out.println("bura catch");
	      System.err.println( e.getClass().getName() + ": " + e.getMessage() );
	      System.exit(0);
	    }
	    System.out.println("Operation done successfully");
		
	   return dev_type;
	    
	}

	
	
  
}