# POEGemcuttersPrismCalculator

# INFO
This calculator aims to help Path of Exile players decision making when doing Gemcutter's Prism (GCP) vendor recipe.
In Path of Exile there is no money like gold, instead, the currency of the game is based on items that help players on crafting weapons and gear. There are lots of different recipes in this game that are based on this same principle, however, a GCP's value has a significant weight if a player manages to use this recipe with maximum efficiency.

# HOW THE RECIPE WORKS
Whenever the total of quality gems in a trade reach 40% quality, a GCP is given as payment.
Another important info is that a gem with 20% quality is equal to a GCP.

# STUDY CASES

	Perfect trade Examples:

	    { 20 }                        = 1 GCP.
	    { 20, 20 }                    = 2 GCP.
	    { 10, 10, 10, 10, 20 }        = 2 GCP.


	Extra Quality with no change and extra currency lost:

	    { 12, 12, 12 , 12 }           = 1 GCP and extra 8% Quality lost.
	    { 17, 17, 17 }                = 1 GCP and extra 11% Quality lost.
	
	Extra Quality with perfect change:

	    { 5, 5, 15, 15, 19 }          = 1 GCP. You can take Gem 19% out.
	    { 3, 3, 4, 5, 6, 10, 11, 17 } = 1 GCP. You can take Gems 3%, 5% and 11% out.
	    { 5, 12, 15, 16, 18, 19, 20 } = 3 GCP. You can take Gem 5% out.


	Extra Quality with change and currency lost:

	    { 15, 15, 18, 18 }            = 1 GCP. You can take Gem 18% out but 8% Quality is lost.
	    { 3, 5, 10, 13, 15 }          = 1 GCP. You can take Gem 5% out but 1% Quality is lost.
	    { 3, 12, 12, 12, 12, 12 }     = 1 GCP. You can take Gems 12% and 3% but 8% Quality is lost.

# CONSIDERATIONS
Based on these examples, I used JAVA to write a class called GemCalculator.
Variables names and comments are yet to be added to explain the logic and how the program works.
Beforehand I know that this is not the most efficient approach for this program, and it's open for improvement.
Right after I also posted the current GUI I wrote using javax.swing (JFrame) library.

"If necesseray, and I believe it is, we can restart from scratch".


# class GemCalculator
import java.awt.Image;
import java.util.Arrays;

public class GemCalculator 
{
	private int size = 0;
	private int inputIndex = 0;
	private int[] gems;
	int total = 0;

	public GemCalculator(){}

	public GemCalculator(int size) 
	{
		gems = new int[size];
		this.size = size;
	}

	public void addGem(int number)
	{
		if (inputIndex == size)
		{
			return;
		}
		else
		{
			gems[inputIndex++] = number;
		}
	}

	// IF RESTART IS CLICKED
	public void restart(int size)
	{
		total = 0;
		inputIndex = 0;
		setSize(size);
		gems = null;

		gems = new int[size];
	}

	public void setSize(int size)
	{
		this.size = size;
	}

	public int getSize()
	{
		return size;
	}

	public int getInputIndex()
	{
		return inputIndex;
	}

	public String getResult()
	{
		boolean checked = false;
		Arrays.sort(gems);

		String line_1 = new String();
		String line_2 = new String();

		for (int n : gems)
		{
			total += n;
		}

		for	(int i = 1, prism = 40 ; i < 61 ; prism = 40 * i)
		{
			if (total == prism)
			{
				line_1 = "Perfect "+ prism +"! "+i+" Gemcutter's Prism.";
				checked = true;
				break;
			}
			i++;
		}

		if (total < 40)
		{
			line_1 = "You are missing " + (40-total) + " of quality gem(s).";
		}
		else if (total > 40 && checked == false)
		{
			int resultValue = 0;
			for (int i = 1 ; i <= (total/40) ; i++)
			{
				resultValue = (total-(40*i));
				line_1 = "Total = "+i+" Gemcutter's Prism and an extra " + resultValue + " of quality.";
			}

			int test_1 = 0;
			int test_2 = 0;
			int test_3 = 0;


			for (int x = 0 ; x < gems.length; x++)
			{
				for (int y = 0; y < gems.length ; y++)
				{
					for (int z = 0 ; z < gems.length ; z++)
					{
						for (int a = 0; a < gems.length ; a++)
						{

							if (x == y && y == z && z == a)
							{
								y++;
								z+=2;
								a+=3;
							}

							if (a == z)
							{
								a++;
							}

							if (a == y)
							{
								a++;
							}

							if (a == x)
							{
								a++;
							}

							if (z == a)
							{
								z++;
							}

							if (z == y)
							{
								z++;
							}

							if (z == x)
							{
								z++;
							}

							if (y == a)
							{
								y++;
							}

							if (y == z)
							{
								y++;
							}

							if (y == x)
							{
								y++;
							}


							if (y >= gems.length || z >= gems.length || a >= gems.length)
								break;

							test_1 = gems[x] + gems[y];
							test_2 = gems[x] + gems[y] + gems[z];
							test_3 = gems[x] + gems[y] + gems[z] + gems[a];

							if (test_1 == resultValue)
							{
								line_2 = "You can take gems with quality " + gems[x] + " and " + gems[y] + " out.";

								return line_1 + " " + line_2;
							}
							if (test_2 == resultValue)
							{
								line_2 = "You can take gems with quality " + gems[x] + ", " + gems[y] + " and " + gems[z] + " out.";
								return line_1 + " " + line_2;
							}
							if (test_3 == resultValue)
							{
								line_2 = "You can take gems with quality " + gems[x] + ", " + gems[y] + ", " + gems[z] + " and " + gems[a] + " out.";
								return line_1 + " " + line_2;
							}
							else if (gems[x] == resultValue)
							{
								line_2 = "Take quality " + gems[x] + " out.";
								return line_1 + " " + line_2;
							}
							else
							{
								line_2 = getLossConditionString(resultValue);
							}

						} // END A
					} // END Z
				} // END Y					
			} // END X

		}

		return line_1 + " " + line_2;
	}

	public String getLossConditionString(int resultValue)
	{
		int difference_1 = 0;
		int difference_2 = 0;
		int smallestDifference = 1200;
		int biggestGemQuality = 0;
		
		boolean gemsXY_Check = false;
		int gemX = -1;
		int gemY = -1;
		int xyDifference = 0;
		
		int biggestDifference = -1200;
		int smallestGemQuality = 0;
		
		int test_1 = 0;
		
		String lossCondition = new String();
		
		for (int x = 0 ; x < gems.length ; x++)
		{
			for (int y = 0 ; y < gems.length ; y++)
			{
				if (y == x)
				{
					y++;
				}
				if (y >= gems.length)
					break;
				
				test_1 = gems[x] + gems[y];
				
				difference_1 = resultValue - gems[x];
				difference_2 = resultValue - (gems[x] + gems[y]);
				
				if (difference_2 > 0)
				{
					gemX = gems[x];
					gemY = gems[y];
					gemsXY_Check = true;
					xyDifference = difference_2;
				}

				if (smallestDifference > difference_1 && difference_1 > 0)
				{
					smallestDifference = difference_1;
					biggestGemQuality = gems[x];
				}
				
				if (biggestDifference < difference_1 && difference_1 > 0)
				{
					biggestDifference = difference_1;
					smallestGemQuality = gems[x];
				}
			}			
			
		}
		
		if (gemsXY_Check)
			lossCondition = "Taking gems with quality " + gemX + " and " + gemY + " leaves you with " + xyDifference + " extra quality.";	
		else if (smallestDifference < biggestGemQuality && smallestDifference > 0)		
			lossCondition = "Taking gem with " + biggestGemQuality + " of quality leaves you with " + smallestDifference + " extra quality.";	
		else if (smallestDifference < 0 && biggestDifference > 0)
			lossCondition = "Taking gem with " + smallestGemQuality + " of quality leaves you with " + biggestDifference + " extra quality.";
		else if (biggestDifference < 0)
			lossCondition = "You cannot take any gem out.";
		else if (smallestDifference > 0)
			lossCondition = "Taking gem with " + biggestGemQuality + " of quality leaves you with " + smallestDifference + " extra quality.";

		return lossCondition;
	}


}

# class GemCalculatorUI
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


