
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import javax.swing.*;


public class SuspectPanel extends JFrame {

	private JPanel suspectPanel;
	private JTextField suspectName;
	private JTextField suspectNickName;
	private JList phoneNumbers;
	private JTextField findPhoneSMS;
	private JTextArea messages;
	private JButton SMSbutton;
	private JTextArea partners;
	private JTextArea suggestedPartners;
	private JTextArea suspectsWithSameOrigin;
	private JButton returnToSearchScreen;
	private Suspect suspect;
	private Registry registry;
	
	public SuspectPanel(Suspect aSuspect , Registry aRegistry) {
	
		suspect = aSuspect;
		registry = aRegistry;
		
		suspectPanel = new JPanel();
		
		//block1
		JPanel block1 = new JPanel();
		suspectName = new JTextField(suspect.getName());
		suspectName.setEditable(false);
		suspectNickName = new JTextField(suspect.getCodeName());
		suspectNickName.setEditable(false);
		
		ArrayList<String> dummy = new ArrayList<>();
		
		phoneNumbers = new JList();
		DefaultListModel<String> model = new DefaultListModel<>();
		for(String s: suspect.getPhoneNumbers()) {
		    model.addElement(s);
		   
		}
		
		
		phoneNumbers.setModel(model);
		
		
		block1.add(suspectName);
		block1.add(suspectNickName);
		block1.add(phoneNumbers);
		block1.setBorder(BorderFactory.createTitledBorder("Suspect Infos"));
	
		
		
		
		//block2
		JPanel block2 = new JPanel();
		findPhoneSMS = new JTextField("Put phone number");
		messages = new JTextArea(10,30);
		SMSbutton = new JButton("Find SMS");
		SMSButtonListener smsListener = new SMSButtonListener();
		SMSbutton.addActionListener(smsListener);
		
		block2.add(findPhoneSMS);
		block2.add(messages);
		block2.add(SMSbutton);
		block2.setBorder(BorderFactory.createTitledBorder("Search for suspicious messages"));
		
		
		
		//block3
		JPanel block3 = new JPanel();
		partners = new JTextArea(10,30);
        
		for(Suspect s:suspect.getPartners()) {	
			partners.append(s.getName()+", "+s.getCodeName()+"\n");
			
		}
		
		block3.add(partners);
		block3.setBorder(BorderFactory.createTitledBorder("Partners of suspect"));
	
		
		
		//block4 
		JPanel block4 = new JPanel();
		suggestedPartners = new JTextArea(10,30);
		
		for(Suspect s:suspect.SuggestedPartners()) {	
			suggestedPartners.append(s.getName()+", "+s.getCodeName()+"\n");
			
		}
		
		block4.add(suggestedPartners);
		block4.setBorder(BorderFactory.createTitledBorder("Suggested partners"));
		
		
		
		//block5
		JPanel block5 = new JPanel();
		suspectsWithSameOrigin = new JTextArea(10,30);
		for(Suspect s : registry.getAllSusp()) {
			if(suspect.getOrigin().equals(s.getOrigin()) && (s != suspect)) {
				suspectsWithSameOrigin.append(s.getName() +", "+s.getCodeName()+"\n");
			}
		}
		
		block5.add(suspectsWithSameOrigin);
		block5.setBorder(BorderFactory.createTitledBorder("Suspects coming from "+suspect.getOrigin()));
		
		
		
		//return BUTTON
		returnToSearchScreen = new JButton("Return to Search Screen");
		returnToSearchScreen.setPreferredSize(new Dimension(230,40));
		ReturnButtonListener returnListener = new ReturnButtonListener();
		returnToSearchScreen.addActionListener(returnListener);
		
		
		
		//add gui elements
		suspectPanel.add(block1);
		suspectPanel.add(block2);
		suspectPanel.add(block3);
		suspectPanel.add(block4);
		suspectPanel.add(block5);
		suspectPanel.add(returnToSearchScreen);
		
		this.setVisible(true);
		this.setContentPane(suspectPanel);
		this.setTitle("Suspect Page");
		this.setSize(600, 1000);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
	}
	
	class SMSButtonListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent number) {
		
			ArrayList<SMS> sms = new ArrayList<>();
			
			for(String phoneNumber: suspect.getPhoneNumbers()){
		        sms.addAll(registry.getMessagesBetween(phoneNumber, findPhoneSMS.getText()));
			}
			
			for(SMS s:sms) {
				messages.setText(s.getMessage());
			}
			
		}
		
	}
	
	class ReturnButtonListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			
			new FindSuspectPanel(registry);
			dispose();
		}
		
	}
}
	
