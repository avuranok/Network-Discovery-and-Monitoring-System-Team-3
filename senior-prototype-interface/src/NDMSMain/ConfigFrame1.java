package NDMSMain;


import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;

import SNMPHandler.SNMPSet;

import com.sun.tools.javac.code.Attribute.Array;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.ArrayList;

import javax.swing.JTextField;

public class ConfigFrame1 extends JFrame {

	frame1 mainFrame1;
	functions func = new functions();
	
	SNMPSet snmpSet = new SNMPSet();
	private JPanel contentPane;
	JComboBox comboBoxVlans;
	JComboBox comboBoxIntCount;
	public JTextField textField_config_ip;
	

	/**
	 * Create the frame.
	 */
	public ConfigFrame1(frame1 mf1) {
		mainFrame1 = mf1;
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		final JComboBox comboBoxPorts = new JComboBox();
		comboBoxPorts.setModel(new DefaultComboBoxModel(new String[] {"FastEthernet", "GigabitEthernet", "Ethernet"}));
		comboBoxPorts.setSelectedIndex(0);
		comboBoxPorts.setBounds(5, 47, 135, 27);
		contentPane.add(comboBoxPorts);
	
		comboBoxVlans = new JComboBox();
		comboBoxVlans.setBounds(291, 47, 72, 27);
		contentPane.add(comboBoxVlans);
		
		
		JButton btnAsignVlan = new JButton("Asign");
		btnAsignVlan.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				/*
				 * interfaceTypes:
				 * 0:FasthEthernet
				 * 1:GigabitEthernet
				 * 2:Ethernet
				 */
				
				int interfaceType = comboBoxPorts.getSelectedIndex();
				int vlanId = Integer.parseInt(comboBoxVlans.getSelectedItem().toString());
				String deviceIp = textField_config_ip.getText().trim().toString();
				int intNumber = comboBoxIntCount.getSelectedIndex()+1;
				String intNumberOid=null;
				
				
				if(intNumber<10)
				{
					intNumberOid="0"+intNumber;
				}
				else
				{
					intNumberOid=intNumber+"";
				}
				
				if(interfaceType==0)
				{
					
					snmpSet.main(deviceIp, "public", "1.3.6.1.4.1.9.9.68.1.2.2.1.2.100"+intNumberOid, vlanId);
				}
				else if(interfaceType==1)
				{
					snmpSet.main(deviceIp, "public", "1.3.6.1.4.1.9.9.68.1.2.2.1.2.101"+intNumberOid, vlanId);

				}
				else
				{
					func.showAlert("There is  no Ethernet Port on Device");
				}
				
				
				
				
			}
		});
		btnAsignVlan.setBounds(372, 46, 72, 29);
		contentPane.add(btnAsignVlan);
		
		JButton btnNext = new JButton("OK");
		btnNext.setBounds(327, 243, 117, 29);
		contentPane.add(btnNext);
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				dispose();
				comboBoxVlans.removeAllItems();
			}
		});
		btnCancel.setBounds(204, 243, 117, 29);
		contentPane.add(btnCancel);
		
		textField_config_ip = new JTextField();
		textField_config_ip.setEditable(false);
		textField_config_ip.setBounds(6, 16, 134, 28);
		contentPane.add(textField_config_ip);
		textField_config_ip.setColumns(10);
		
		comboBoxIntCount = new JComboBox();
		comboBoxIntCount.setBounds(185, 47, 66, 27);
		contentPane.add(comboBoxIntCount);
		
	}
}
