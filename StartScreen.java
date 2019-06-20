import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class StartScreen extends JFrame implements ActionListener
{
	
	
	final static int Width = 800;
	final static int Height = 800;
	
	JButton easy = new JButton("Easy Mode");
	JButton hard = new JButton("Hard Mode");
	JButton hell = new JButton("Hell Mode");
	
	JPanel buttonPanel = new JPanel();
	gamePanel gamepanel = new gamePanel(0);
	
	public StartScreen()
	{
		super("Ω¥∆√ ∞‘¿”");
		setSize(Width,Height);
		setLocationRelativeTo(null);
		setResizable(false);
		setVisible(true);
		
		setLayout(new BorderLayout());
		
		add(buttonPanel,BorderLayout.NORTH);
		add(gamepanel,BorderLayout.CENTER);
		
		easy.addActionListener(this);
		hard.addActionListener(this);
		hell.addActionListener(this);
		buttonPanel.add(easy);
		buttonPanel.add(hard);
		buttonPanel.add(hell);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		String buttonString = e.getActionCommand();
		
		if(buttonString == "Easy Mode")
		{
			remove(easy);
			remove(hard);
			remove(hell);
			remove(buttonPanel);
			
			gamepanel.Start();
			
		}
		else if(buttonString == "Hard Mode")
		{
			remove(easy);
			remove(hard);
			remove(hell);
			remove(buttonPanel);
			
			gamepanel.Start();
			
		}
		else if(buttonString == "Hell Mode")
		{
			remove(easy);
			remove(hard);
			remove(hell);
			remove(buttonPanel);
			
			gamepanel.Start();
			
		}
	}

}
