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

