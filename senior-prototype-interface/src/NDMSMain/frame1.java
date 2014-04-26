package NDMSMain;


import java.awt.AWTException;
import java.awt.BorderLayout;
import java.awt.Cursor;
import java.awt.EventQueue;
import java.awt.List;
import java.awt.Point;
import java.awt.Robot;
import java.awt.Toolkit;

import javax.management.MBeanServer;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.text.BadLocationException;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;
import javax.swing.Box;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.InputVerifier;
import javax.swing.JComponent;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JMenu;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPopupMenu;
import javax.swing.JTextField;
import javax.swing.JFormattedTextField;
import javax.swing.JComboBox;
import javax.swing.JTextPane;
import javax.swing.ListSelectionModel;
import javax.swing.SwingUtilities;
import javax.swing.SwingWorker;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JList;
import javax.swing.AbstractListModel;
import javax.swing.JTabbedPane;
import javax.swing.JInternalFrame;
import javax.swing.JLayeredPane;
import javax.swing.JTextArea;
import javax.swing.JButton;
import javax.swing.DefaultComboBoxModel;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.InputEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JProgressBar;

import org.snmp4j.smi.OID;

import SNMPHandler.SNMPManager;
import SNMPHandler.SNMPWalk;

import java.awt.FileDialog;

import dbHandler.SQLiteJDBC;

import javax.swing.JScrollPane;




public class frame1 extends JFrame {
	public static ArrayList<String> ipler = new ArrayList<String>();
	final DefaultListModel model ;
	final JList jlist ;
	
	ConfigFrame1 cf2 = new ConfigFrame1(this);
	
	
	String detailsOid ;
	
	 String ip="";
	 String lastIp=""; 
	 public static String getSelectedIp = null;

	frame1 fr;
	SQLiteJDBC sql ;
	  JProgressBar progressBar;
	  
	  boolean flag = false;
	  boolean flag_load = false;
	  boolean flag_scan=false;
	  boolean textareaFlag=false;
	
	  SQLiteJDBC sq = new SQLiteJDBC();
	  List iplist;
	
	
	  FileDialog fd;
	private JPanel contentPane;
	JButton btnScan;
	String reachable;
	
	functions f = new functions();
	Thread thr = new Thread();
	Thread th;
	private JTextField textField = null;
	private JTextField textField_1 = null;
	public JTextPane textArea_2;
	public JTabbedPane tabbedPane;
	JLabel lblNewLabel_1;
	double progressbarRatedefault;
	int castedRate;
	String all_info ="";
	JPopupMenu popup;
	
	ArrayList<String> vlanIDs = new ArrayList<String>();


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {

		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frame1 frame = new frame1();
					frame.setVisible(true);
					
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public frame1() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 882, 633);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JMenuBar menuBar = new JMenuBar();
		menuBar.setBounds(0, 0, 758, 21);
		menuBar.setToolTipText("");
		contentPane.add(menuBar);
		
		progressBar = new JProgressBar();
		progressBar.setBounds(211, 82, 537, 33);
		contentPane.add(progressBar);
		
		JMenu mnNewMenu = new JMenu("New menu");
		menuBar.add(mnNewMenu);
		
		JMenuItem NewMenuItem = new JMenuItem("New menu item");
		mnNewMenu.add(NewMenuItem);
		
		JLabel lblStartingIp = new JLabel("First IP Address:");
		lblStartingIp.setBounds(10, 32, 86, 14);
		contentPane.add(lblStartingIp);
		
		JLabel lblNewLabel = new JLabel("Last IP Address:");
		lblNewLabel.setBounds(10, 57, 86, 14);
		contentPane.add(lblNewLabel);
		
		JLabel lblScanOption = new JLabel("Scan Option");
		lblScanOption.setBounds(302, 32, 86, 14);
		contentPane.add(lblScanOption);
		
		
		
		
		JComboBox comboBox = new JComboBox();
		comboBox.setBounds(375, 32, 103, 21);
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"Intance Scan", "Quick Scan", "Port Scan", "Ping Scan"}));
		contentPane.add(comboBox);
		
		JPanel panel = new JPanel();
		panel.setBounds(20, 82, 181, 501);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblHosts = new JLabel("HOSTS");
		lblHosts.setFont(new Font("Stencil Std", Font.BOLD, 16));
		lblHosts.setForeground(Color.BLACK);
		lblHosts.setBounds(10, 0, 67, 14);
		panel.add(lblHosts);
		
		
		 model = new DefaultListModel();
		jlist = new JList(model);
		jlist.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		
		jlist.setBounds(0, 21, 161, 439);
		panel.add(jlist);
		
		jlist.addMouseListener(new java.awt.event.MouseAdapter(){
		 public void mouseClicked(java.awt.event.MouseEvent mouseEvent){
           if (!jlist.getCellBounds(jlist.getSelectedIndex(), jlist.getSelectedIndex()).contains(mouseEvent.getPoint())){
                	jlist.removeSelectionInterval(jlist.getSelectedIndex(), jlist.getSelectedIndex());
                }
                	tabbedPane.setSelectedIndex(2);
				
				
				/**
				 * OID - .1.3.6.1.2.1.1.1.0                   ==> SysDec -Like " Cisco IOS Software, C2960 Software (C2960-LANBASE-M), Version 12.2(25)FX"
				 * OID - .1.3.6.1.2.1.1.5.0                   ==> SysName ÇAlýþýyor
				 * OID - .1.3.6.1.2.1.1.3.0                   ==> Uptime Çalýþýyor
				 * OID -1.3.6.1.2.1.11.15.0 				  ==> Count of Get, 
				 * OID - 1.3.6.1.6.3.1.1.6.1.0   			  ==> one of the set operation.--Kullanma kafan karýþmasýn.
				 * OID - 1.3.6.1.4.1.9.9.13.1.4.1.2.1004	  ==> FanStatus
				 * OID - 1.3.6.1.4.1.9.9.13.1.5.1.2.1003      ==> PowerSupplyStatus
				 * OID - 1.3.6.1.4.1.9.2.1.61				  ==> Company Info
				 * OID - 1.3.6.1.2.1.16.19.6.0 				  ==> flash:C2960vsvs
				 * OID - 1.3.6.1.4.1.9.9.23.1.2.1.1.5.10001.1 ==> Ayrýntý Detay En üstteki gibi.
				 * OID - 1.3.6.1.4.1.9.9.23.1.2.1.1.6.10001.1 ==> Type
				 * OID - 1.3.6.1.4.1.9.9.23.1.2.1.1.7.10001.1 ==>  FastEthernet0/1
				 * OID - 1.3.6.1.4.1.9.9.23.1.2.1.1.8.10001.1 ==>  Model
				 * OID - 1.3.6.1.4.1.9.2.1.56.0 ==>CPU usage 5 second avg
				 * OID - 1.0.8802.1.1.2.1.5.4795.1.2.7.0  ==> Model of the Device
				 */
				
				if(flag_load==false)
				{
					textArea_2.setText(null);
					try {
						//SNMPAgent as = new SNMPAgent("192.168.1.10");1.3.6.1.2.1.47.1.1.1.1.13-1.0.8802.1.1.2.1.5.4795.1.2.7.0
						detailsOid = SNMPManager.main("udp:"+jlist.getSelectedValue()+"/161","1.0.8802.1.1.2.1.5.4795.1.2.7.0");//
						if(detailsOid.startsWith("no"))
						{
							detailsOid = SNMPManager.main("udp:"+jlist.getSelectedValue()+"/161","1.3.6.1.2.1.47.1.1.1.1.13.1");
						
							if(detailsOid.startsWith("no"))
							{
								detailsOid="UNKOWN DEVICE TYPE";
							}
						}
						
						
						StyledDocument doc = textArea_2.getStyledDocument();
						SimpleAttributeSet keyWord = new SimpleAttributeSet();
						StyleConstants.setForeground(keyWord, Color.BLACK);
						StyleConstants.setBold(keyWord, true);
						
						if(!detailsOid.equals("SNMP does not supported"))
						{
						String hostNameOid = SNMPManager.main("udp:"+jlist.getSelectedValue()+"/161","1.3.6.1.2.1.1.5.0");
						
					//	String asd3 = SNMPManager.main("udp:"+jlist.getSelectedValue()+"/161","1.3.6.1.4.1.9.9.13.1.4.1.2.1004");
						
					//	String asd4 = SNMPManager.main("udp:"+jlist.getSelectedValue()+"/161","1.3.6.1.4.1.9.9.13.1.5.1.2.1003");
						
						String upTimeOid = SNMPManager.main("udp:"+jlist.getSelectedValue()+"/161","1.3.6.1.2.1.1.3.0");
						
						String asd6 = SNMPManager.main("udp:"+jlist.getSelectedValue()+"/161","1.3.6.1.4.1.9.9.23.1.2.1.1.8.10001.1");
						
						//String model = SNMPManager.main("udp:"+jlist.getSelectedValue()+"/161",".1.3.6.1.4.1.9.9.23.1.2.1.1.8.1.4");
						
						//String firmware = SNMPManager.main("udp:"+jlist.getSelectedValue()+"/161","1.3.6.1.4.1.9.9.23.1.2.1.1.5.10001.1");
						
						String cpuin5scOid = SNMPManager.main("udp:"+jlist.getSelectedValue()+"/161","1.3.6.1.4.1.9.2.1.56.0"); //cpu'ya cevrildi.
						
					   // String testWalk = SNMPWalk.main(jlist.getSelectedValue().toString());
						
						try
						{
						    doc.insertString(0, "HostName :", keyWord );
						    doc.insertString(doc.getLength(), hostNameOid, null);
						    doc.insertString(doc.getLength(),"\n Details : ", keyWord);
						    doc.insertString(doc.getLength(), detailsOid, null);
						    doc.insertString(doc.getLength(),"\n Cpu Usage in 5 second (avg): ", keyWord);
						    doc.insertString(doc.getLength(), "%"+cpuin5scOid, null);
						    doc.insertString(doc.getLength(),"\n Up Time :", keyWord);
						    doc.insertString(doc.getLength(), upTimeOid, null);
						    doc.insertString(doc.getLength(),"\n Flash :", keyWord);
						  //  doc.insertString(doc.getLength(), testWalk+"\n", null);
				
					
						    all_info=textArea_2.getText();
						   
						   
						    
						    if(hostNameOid.equalsIgnoreCase("switch"))
						    {
						    	
						    	lblNewLabel_1.setVisible(true);
						    }
						}
						catch(Exception e) 
						{ 
							System.out.println(e); 
						}
					}
						else
						{
							
							 InetAddress ip1 =  InetAddress.getByName(jlist.getSelectedValue().toString());
							 
							 if(!ip1.isLoopbackAddress())
							 {
							 String hostname = ip1.getHostName();
							 String CanonicalHostName = ip1.getCanonicalHostName();
							 String nbtstat = f.arpQuery(jlist.getSelectedValue().toString());
							 try {
								doc.insertString(0, "\nNAme :", keyWord );
								doc.insertString(doc.getLength(), hostname, null);
								doc.insertString(doc.getLength(), "\nCanonicalHostName :", keyWord );
								doc.insertString(doc.getLength(), CanonicalHostName, null);
								doc.insertString(doc.getLength(), nbtstat, null);
								
							} catch (BadLocationException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							 }
							 else{
								 try {
									 
									doc.insertString(0, "\nIt is LoopBack Address", keyWord );
									
								} catch (BadLocationException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
								 
							 }

                            
							
							
						}
						//textArea_2.append(asd2);
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
					
						
				}
				else
				{
					for(int k=0;k<iplist.getItemCount();k++)
					{
						textArea_2.setText("");
						if(iplist.getItem(k).equalsIgnoreCase(jlist.getSelectedValue()+""))
						{
							StyledDocument doc = textArea_2.getStyledDocument();
							SimpleAttributeSet keyWord = new SimpleAttributeSet();
							StyleConstants.setForeground(keyWord, Color.BLACK);
							StyleConstants.setBold(keyWord, true);
							
						
						    try {
						    	doc.insertString(0, "HostName :", keyWord );
							    doc.insertString(doc.getLength(), iplist.getItem(k+1) , null);
							    doc.insertString(doc.getLength(),"\n Details : ", keyWord);
							    doc.insertString(doc.getLength(),iplist.getItem(k+2) , null);
							    doc.insertString(doc.getLength(),"\n Uptime ", keyWord);
							    doc.insertString(doc.getLength(), iplist.getItem(k+3) , null);
							    doc.insertString(doc.getLength(),"\n Flash:", keyWord);
							    doc.insertString(doc.getLength(), iplist.getItem(k+4) , null);
							    doc.insertString(doc.getLength(),"\nLast Cpu usage  :", keyWord);
								doc.insertString(doc.getLength(),"%"+iplist.getItem(k+5)+"\n", null);
							} catch (BadLocationException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						
							
							
						}
					}
					
					
				}
            }
		 public void mousePressed(MouseEvent e)
         {
              if ( SwingUtilities.isRightMouseButton(e) )
              {
                    try
                    {
                         Robot robot = new java.awt.Robot();
                         robot.mousePress(InputEvent.BUTTON1_MASK);
                         robot.mouseRelease(InputEvent.BUTTON1_MASK);
                    }
                    catch (AWTException ae) { System.out.println(ae); }
              }
         }
			public void mouseReleased(MouseEvent e) {
			    if(SwingUtilities.isRightMouseButton(e)){
			    	showPopUp(e);
		
			       System.out.println(jlist.getSelectedValue().toString());
			      
			    }
			}
			
			public void showPopUp(MouseEvent e) {
				int selectedId;
				String selectedPID;

				// Right mouse click
				
					// get the coordinates of the mouse click
					Point p = e.getPoint();

					popup = new JPopupMenu();
					
					JMenuItem configureMenu = new JMenuItem("Configure");
					JMenuItem deleteMenu = new JMenuItem("Delete");
				
					popup.add(configureMenu);
					popup.add(deleteMenu);
			
				
				
				popup.show(e.getComponent(), e.getX(), e.getY());
					
					
					configureMenu.addActionListener(new ActionListener() {
						@Override
						public void actionPerformed(ActionEvent e) {
							String getVlanOid="1.3.6.1.4.1.9.5.1.9.3.1.3";
							 getSelectedIp = jlist.getSelectedValue().toString();
							 
							Boolean vlanOidFlag = false;
							System.out.println("DetailsOid:"+detailsOid);
							try {
								if(detailsOid.startsWith("UNKNOWN") || detailsOid.startsWith("WS-C2950") )
								{
									
									f.showAlert("This is an OLD DEVICE and probably E.O.S. Cannot configure or doesn't support SNMP");
								}
								else if(detailsOid.startsWith("J"))
								{
									getVlanOid="1.3.6.1.4.1.11.2.14.11.5.1.7.1.15.1.1.1";
									vlanOidFlag = true;

								}
								else
								{
									vlanOidFlag = true;
								}
								if(vlanOidFlag)
								{
								vlanIDs = f.getVlans(jlist.getSelectedValue().toString(),getVlanOid);
								for(int i=0;i<vlanIDs.size();i++)
									cf2.comboBoxVlans.addItem(vlanIDs.get(i));
								int intCount = f.getInterfaceCount(getSelectedIp);
								for(int i=0;i<intCount-2;i++)
									cf2.comboBoxIntCount.addItem(i+1);
								
									cf2.textField_config_ip.setText(getSelectedIp);
									cf2.setVisible(true);
								}
							} catch (IOException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
							
							
						}
					});

					deleteMenu.addActionListener(new ActionListener() {
						@Override
						public void actionPerformed(ActionEvent e) {
							System.out.println(((JMenuItem) e.getSource()).getText()
									.toString());
						}
					});

		

					

				}

			
           
        });
		

		JButton btnSave = new JButton("Save");
		btnSave.addActionListener(new ActionListener() {
		
			public void actionPerformed(ActionEvent e) {
				
				if(flag_scan)
				{
				DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH-mm-ss");
				Date date = new Date();
				

				sql = new SQLiteJDBC();
				sql.connect(dateFormat.format(date));
				sql.createtable();
				
				for(int i=1;i<model.getSize();i++)
				{
					String s = model.get(i).toString();
					
					
					
					String asd;//Hostname
					try {
						
						asd = SNMPManager.main("udp:"+s+"/161","1.3.6.1.2.1.1.1.0");
						if(!asd.equals("SNMP does not supported"))
						{
						String asd2 = SNMPManager.main("udp:"+s+"/161","1.3.6.1.2.1.1.5.0");//Details
						
						
						String asd5 = SNMPManager.main("udp:"+s+"/161","1.3.6.1.2.1.1.3.0");//Uptime
						
						String asd6 = SNMPManager.main("udp:"+s+"/161","1.3.6.1.2.1.16.19.6.0");//Flash
						
						
						String interfaces = SNMPManager.main("udp:"+s+"/161","1.3.6.1.4.1.9.2.1.56.0"); //cpu'ya cevrildi.
						
					
					
						sql.connect(dateFormat.format(date));
						sql.insertData(s,asd,asd2,asd5,asd6,interfaces);
					
						
						
						}
						
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					
				
					
					
					
				
				}
				
				}
				else
				{
					JOptionPane.showMessageDialog(null, "Please scan a network", "Save Error", JOptionPane.PLAIN_MESSAGE);
				}
			}
		});
		btnSave.setBounds(0, 472, 74, 23);
		panel.add(btnSave);
		
		JButton btnLoad = new JButton("Load");
		btnLoad.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			
				
				iplist = new List();
				sql = new SQLiteJDBC();
				fd = new FileDialog(fr, "sett text",FileDialog.LOAD);
				fd.setDirectory("C:\\");
				fd.setFile("*.db");
				fd.setVisible(true);
				String filename = null;
				 filename = fd.getDirectory()+fd.getFile();
				
				if (filename == null)
				  System.out.println("You cancelled the choice");
				else
				  System.out.println("You chose " + filename);
				
				if(!filename.contains("null"))
				{
					model.clear();
					iplist = sql.loadData(filename);
					for(int i=0;i<iplist.getItemCount();i+=6)
					{
						model.addElement(iplist.getItem(i));
						
					}
					flag_load=true;
				}
				else
				{
					
					JOptionPane.showMessageDialog(null, "Please select a *.db file", "Loading Error", JOptionPane.PLAIN_MESSAGE);
					
				}
				
				
			}
		});
		btnLoad.setBounds(86, 472, 74, 23);
		panel.add(btnLoad);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(211, 149, 645, 434);
		contentPane.add(panel_1);
		panel_1.setLayout(null);

		
		
		
		tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(154, 11, 481, 412);
		panel_1.add(tabbedPane);
		JPanel output = new JPanel();
		tabbedPane.add("Output",output);
		output.setLayout(null);
		
		final JTextArea textArea = new JTextArea();
		textArea.setBounds(0, 0, 466, 373);
		output.add(textArea);
		
		JPanel ports = new JPanel();
		tabbedPane.add("Ports/Hosts",ports);
		ports.setLayout(null);
		
		JTextArea textArea_1 = new JTextArea();
		textArea_1.setBounds(0, 0, 436, 301);
		ports.add(textArea_1);
		
		final JPanel hostdeta = new JPanel();
		tabbedPane.add("Host Details",hostdeta);
		hostdeta.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 0, 466, 373);
		hostdeta.add(scrollPane);
		
		textArea_2 = new JTextPane();
		textArea_2.setEditable(false);
		scrollPane.setViewportView(textArea_2);
		
		JPanel topology = new JPanel();
		tabbedPane.add("Topology",topology);
		
		JButton butTopology = new JButton("Topology start");
		butTopology.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				//f.lldprun();
			DiscoveryOperation.main();
			
			try {
					//DrawTopology.main();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			
			}
		});
		topology.add(butTopology);
		
		lblNewLabel_1 = new JLabel("New label");
		ImageIcon iconLogo = new ImageIcon("Images/1388110436_switch.png");
		// In init() method write this code
		lblNewLabel_1.setIcon(iconLogo);
		lblNewLabel_1.setBounds(10, 128, 134, 163);
		panel_1.add(lblNewLabel_1);
		
		lblNewLabel_1.setVisible(false);
		
	
	
		progressBar.setVisible(false);
		 
		
		btnScan = new JButton("Scan");
		btnScan.setBounds(536, 32, 89, 23);
		btnScan.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
	
				int ip_range = f.iki_ip_arasindaki_fark(textField.getText(), textField_1.getText());
				
				
//				sql= new SQLiteJDBC();
//				sql.connect("deviceList");
//				sql.createtable2();
//				sql.insertData2();
				
				final double progressbarRate = 100.0 / ip_range;
				progressbarRatedefault=0;
				castedRate=0;
				if(ip_range>=1)
				{
				
			
				
				model.clear();
				flag_scan=true;
					
					th  = new Thread(new Runnable() {
			         public void run()
			         {
			              // Insert some method call here.
			        	 progressBar.setVisible(true);
			        	 progressBar.setIndeterminate(false);
			        	 progressBar.setValue(100);
			        	 progressBar.setStringPainted(true);
						 progressBar.setString("0");
						 
						 ip = textField.getText();
			        	 lastIp = textField_1.getText();
			        	 ipler.clear(); 
			        	 
			        	 String lastIpCheck = " "; 
							while(!(lastIpCheck.equalsIgnoreCase(f.nextIpAddress(lastIp)))) 
							{
								progressbarRatedefault += progressbarRate;
								castedRate =(int)progressbarRatedefault;
								progressBar.setValue(castedRate);
								 progressBar.setString("%"+castedRate+"");
								reachable = f.ScanOpeation(ip, lastIp);
								if(reachable.equalsIgnoreCase("up"))
					        	{
									
					        		model.addElement(ip);
					        		ipler.add(ip);
					        		
					       
					        	}
							 
							 	lastIpCheck	= f.nextIpAddress(ip);
						        
					        	ip = lastIpCheck;
							}
							progressBar.setValue(100);
			        	 flag = true;
			        	 progressBar.setVisible(false);
			         }
			});
					
					th.start();
				if(flag)
				{
					th = null;
					flag = false;
				}
				
				}
				//TextFieldCheck'in Else
				else
				{
					//Not sure the grammer for "Start ip is greater then last ip"
					JOptionPane.showMessageDialog(null, "Start ip is greater then last ip", "Invalid IP Adresses", JOptionPane.PLAIN_MESSAGE);
				}
				//**/
					}
		});

		
		ListSelectionListener listSelectionListener = new ListSelectionListener() {
			@Override
		      public void valueChanged(ListSelectionEvent listSelectionEvent) {
		
		      }
		    };    
		    jlist.addListSelectionListener(listSelectionListener);
		    
		    
		contentPane.add(btnScan);
		
		textField = new JFormattedTextField();
		textField.setText("192.168.1.0");
		textField.setBounds(119, 29, 109, 20);
		contentPane.add(textField);
		textField.setColumns(10);
		//*****TextFieldCheckJustify****////
		
		 textField.setInputVerifier(new InputVerifier() {
	            Pattern pat = Pattern.compile("\\b(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\."+
	                    "(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\." +
	                    "(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\." +
	                    "(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\b");
	            public boolean shouldYieldFocus(JComponent input) {
	                boolean inputOK = verify(input);
	                if (inputOK) {
	                    return true;
	                } else {
	                    Toolkit.getDefaultToolkit().beep();
	                    JOptionPane.showMessageDialog(null, "Please Enter a Valid IP Address", "Invalid IP Adress", JOptionPane.PLAIN_MESSAGE);
	                    
	                    textField.addFocusListener(new java.awt.event.FocusAdapter() {
	                	    public void focusGained(java.awt.event.FocusEvent evt) {
	                	    	SwingUtilities.invokeLater( new Runnable() {

	                				@Override
	                				public void run() {
	                					 textField.selectAll();		
	                				}
	                			});
	                	    }
	                	});
	                    return false;
	                }
	            }
	            public boolean verify(JComponent input) {
	                JTextField field = (JTextField) input;
	                Matcher m = pat.matcher(field.getText());
	                return m.matches();
	            }
	        });
		
		//*****TextFieldCheckJustify****////
		
		
		textField_1 = new JFormattedTextField();
		textField_1.setText("192.168.1.13");
		textField_1.setBounds(119, 54, 109, 20);
		contentPane.add(textField_1);
		textField_1.setColumns(10);
		
		//*****TextField_1CheckJustify****////
		
		 textField_1.setInputVerifier(new InputVerifier() {
	            Pattern pat = Pattern.compile("\\b(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\."+
	                    "(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\." +
	                    "(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\." +
	                    "(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\b");
	            public boolean shouldYieldFocus(JComponent input) {
	                boolean inputOK = verify(input);
	                if (inputOK) {
	                    return true;
	                } else {
	                    Toolkit.getDefaultToolkit().beep();
	                    JOptionPane.showMessageDialog(null, "Please Enter a Valid IP Address", "Invalid IP Adress", JOptionPane.PLAIN_MESSAGE);
	                    
	                    
	                    textField_1.addFocusListener(new java.awt.event.FocusAdapter() {
	                	    public void focusGained(java.awt.event.FocusEvent evt) {
	                	    	SwingUtilities.invokeLater( new Runnable() {

	                				@Override
	                				public void run() {
	                					 textField_1.selectAll();		
	                				}
	                			});
	                	    }
	                	});
	                    
	                    return false;
	                }
	            }
	            public boolean verify(JComponent input) {
	                JTextField field = (JTextField) input;
	                Matcher m = pat.matcher(field.getText());
	                return m.matches();
	            }
	        });
		
		//*****TextFieldCheckJustify****////
		
	
	}
}

