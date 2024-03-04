import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;


import javax.swing.*;

public class FindSuspectPanel extends JFrame{
	
	private JPanel findPanel;
	private JTextField suspectName;
	private JButton findButton;
	private JButton visualizeButton;
	private Registry registry;
	
	public FindSuspectPanel (Registry aRegistry) {
		
		registry = aRegistry;
		
		findPanel = new JPanel();
		suspectName = new JTextField("Enter name of a suspect");
		findButton = new JButton("Find");
		visualizeButton = new JButton("Visualize Network");
		
		findPanel.add(suspectName);
		findPanel.add(findButton);
		findPanel.add(visualizeButton);
		
		this.setVisible(true);
		this.setContentPane(findPanel);
		this.setTitle("Find Suspect");
		this.setSize(300, 150);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		
		 FindButtonListener Findlistener = new FindButtonListener();
		 findButton.addActionListener(Findlistener);
		 
		 VisualizeButtonListener Visualizelistener = new VisualizeButtonListener();
		 visualizeButton.addActionListener(Visualizelistener);
		 
		 
		
		
	}
	
	
	class FindButtonListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			
			boolean notFound = true;
			
			for(Suspect s:registry.getAllSusp()) {
			   if(suspectName.getText().equals(s.getName())) {
				   
				   notFound = false;
				   new SuspectPanel(s , registry); 
				   dispose();
			   }
			}
			if(notFound) JOptionPane.showMessageDialog(null,"Suspect not found in Database");  
		}
		
	}
	
	class VisualizeButtonListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			
			new VisualizeGraph(registry);
			
		}
		
	}

}
