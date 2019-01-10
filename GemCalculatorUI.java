import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import java.awt.SystemColor;
import javax.swing.SwingConstants;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class GemCalculatorUi extends JFrame {

	private JPanel panel_1;
	private JPanel panel_2;
	private JPanel panel_3;
	private ImageIcon img = new ImageIcon("D:\\Dropbox (WebFolder)\\Dropbox\\2. Pratice\\ICOs\\Poe\\gc.png");

	private GemCalculator gemCalc;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GemCalculatorUi frame = new GemCalculatorUi();
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
	public GemCalculatorUi() {
		setBackground(SystemColor.menu);

		gemCalc = new GemCalculator();

		setTitle("POE :: Gemcutter's Prism Calculator");

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 708, 125);


		showPanel_1();

	} // END OF CONSTRUCTOR

	public void showPanel_1()
	{
		/*
		 * PANEL 1
		 * 
		 * Label:	HOW MANY GEMS YOU HAVE?
		 * Text:	Input INTEGER.
		 * Button:	SET.
		 * 
		 * */
		panel_1 = new JPanel();
		panel_1.setBorder(new EmptyBorder(5, 5, 5, 5));
		panel_1.setLayout(new BorderLayout(0, 0));
		setContentPane(panel_1);
		
		/*
		 * PANEL 2
		 * 
		 * Label:	INPUT VALUE +gemCalc.getInputIndex()+1
		 * Button:	OK.
		 * 
		 * */
		panel_2 = new JPanel();
		panel_2.setBorder(new EmptyBorder(5, 5, 5, 5));
		panel_2.setLayout(new BorderLayout(0, 0));

		/*
		 * PANEL 3
		 * 
		 * Label:	STRING RESULT.
		 * Button:	RESTART.
		 * 
		 * */
		panel_3 = new JPanel();
		panel_3.setBorder(new EmptyBorder(5, 5, 5, 5));
		panel_3.setLayout(new BorderLayout(0, 0));

		setIconImage(img.getImage());

		// JPANEL 1 LABEL
		panel_1.setVisible(true);		
		JLabel howManyGemsLabel = new JLabel("How many gems you have?");
		howManyGemsLabel.setHorizontalAlignment(SwingConstants.CENTER);

		panel_1.add(howManyGemsLabel, BorderLayout.NORTH);

		// JPANEL 1 TEXT
		JTextField sizeInputTextPane = new JTextField();
		sizeInputTextPane.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent arg0) {
				if(arg0.getKeyCode() == KeyEvent.VK_ENTER){
					showPanel_2(Integer.parseInt(sizeInputTextPane.getText()));
				}
			}

		});
		sizeInputTextPane.setToolTipText("Only Numbers, please.");
		panel_1.add(sizeInputTextPane, BorderLayout.CENTER);

		// JPANEL 1 BUTTON
		JButton btnSet = new JButton("SET");
		btnSet.setBackground(Color.LIGHT_GRAY);
		btnSet.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				showPanel_2(Integer.parseInt(sizeInputTextPane.getText()));

			} // END OF PANEL 1 ACTION LISTENER
		});
		panel_1.add(btnSet, BorderLayout.SOUTH);

	} // END OF SHOW PANEL 1

	
	/*
	 * SHOW PANEL 2
	 * 
	 * Asks user for quality gems numbers.
	 * 
	 * */
	public void showPanel_2(int sizeInputTextPane)
	{
		panel_1.setVisible(false);
		setContentPane(panel_2);

		// CALCULATOR INSTANTIATION
		gemCalc.restart(sizeInputTextPane);


		// JPANEL 2 LABEL
		JLabel lblInputs = new JLabel();
		lblInputs.setHorizontalAlignment(SwingConstants.CENTER);
		lblInputs.setText("Input value "+(gemCalc.getInputIndex()+1));



		panel_2.add(lblInputs, BorderLayout.NORTH);		

		// JPANEL 2 TEXT		
		JTextPane input = new JTextPane();
		input.addKeyListener(new KeyAdapter() 
		{
			@Override
			public void keyPressed(KeyEvent arg1) 
			{
				if(arg1.getKeyCode() == KeyEvent.VK_ENTER)
				{
					okButton(lblInputs, input);
				}
			}

		});
		panel_2.add(input, BorderLayout.CENTER);


		// JPANEL 2 BUTTON
		JButton btnInput = new JButton("OK");
		btnInput.setBackground(Color.LIGHT_GRAY);
		btnInput.addActionListener(new ActionListener() 
		{
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				okButton(lblInputs, input);

			} // END OF ACTION LISTENER JPANEL 2 BUTTON
		});
		panel_2.add(btnInput, BorderLayout.SOUTH);
	}
	
	public void okButton(JLabel lblInputs, JTextPane input)
	{
		gemCalc.addGem(Integer.parseInt(input.getText()));
		lblInputs.setText("Input value "+(gemCalc.getInputIndex()+1));

		if (!(gemCalc.getInputIndex() < gemCalc.getSize()))
		{
			panel_2.setVisible(false);

			// JPANEL 3 LABEL
			JLabel result = new JLabel(gemCalc.getResult());
			result.setHorizontalAlignment(SwingConstants.CENTER);
			panel_3.add(result, BorderLayout.CENTER);

			JButton restart = new JButton("RESTART");
			restart.setBackground(Color.LIGHT_GRAY);
			restart.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {

					panel_3.setVisible(false);
					showPanel_1();
				}
			});
			panel_3.add(restart, BorderLayout.SOUTH);


			panel_3.setVisible(true);
			setContentPane(panel_3);
		}
	}

}
