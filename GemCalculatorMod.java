import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class GemCalculatorMod 
{
	private ArrayList<Integer> 
	qualityGems, 		// receives input from user.
	removeGems,		// receives gems that can be taken out of the trade based on extraQuality, if any.
	twentyQualityGems;	// takes all 20% Gems from qualityGems<>.


	private int
	qualityTotal,		// Gets inputed Quality Gems.
	extraQuality,		// Gets the remainder of Gems to calculate change.
	prismsTotal; 		// Gets Prisms number.

	public GemCalculatorMod()
	{
		qualityGems		= new ArrayList<>();
		removeGems		= new ArrayList<>();
		twentyQualityGems	= new ArrayList<>();

		qualityTotal = prismsTotal = 0;
	}

	/**
	 * Adds Integers to the ArrayList qualityGems.
	 */
	public void addQualityGems( Integer input )
	{
		qualityGems.add( input );
	}

	public void sortQualityGems()
	{
		Collections.sort( qualityGems );
	}

	/**
	 * Takes all 20% quality gems and add to prismsTotal.
	 */
	public void takeTwentyQualityGemsOut()
	{
		for (int i = qualityGems.size()-1 ; i >= 0; i--)
		{
			if ( qualityGems.get( i ).intValue() == 20 )
			{
				twentyQualityGems.add( qualityGems.get(i).intValue() );
				qualityGems.remove( i );
			}
			else
				break;
		}
	}

	/**
	 * Calculate qualityGems total.
	 */
	public void calculateGemsQualityTotal()
	{
		for ( int i = 0 ; i < qualityGems.size() ; i++ )
		{
			qualityTotal += qualityGems.get( i ).intValue();
		}		
	}

	/**
	 * Gets the total of Prisms after TwentyQualityGems are taken and qualityGems sorted.
	 */
	public String calculatePrismsTotal()
	{
		if ( qualityTotal >= 40 )
		{
			if ( qualityTotal % 40 == 0 )
			{
				prismsTotal 	= qualityTotal / 40;
			}
			else if ( qualityTotal % 40 > 0 )
			{
				prismsTotal 	= qualityTotal / 40;
				extraQuality 	= qualityTotal % 40;
			}
		}

		prismsTotal += twentyQualityGems.size();

		return + prismsTotal + " Gemscutter's Prism(s).";
	}

	/**
	 * This method will add to ArrayList removeGems values from ArrayList gemsQuality that are less equal than extraQuality.
	 * Then it will check the best combination to avoid losing quality gems, if possible.
	 */
	public String getGemsToRemove()
	{		
		int gemsToRemoveTotal = 0;

		if (extraQuality > 0)
		{
			// GET ALL qualityGems THAT ARE LESS EQUAL THAN extraQuality VALUE and ADD TO removeGems.
			for (int i = 0 ; i < qualityGems.size(); i++)
			{
				if ( qualityGems.get(i).intValue() <= extraQuality )
				{
					removeGems.add( qualityGems.get(i).intValue() );
				}
			}

			// CHECK IF THE LAST removeGem value is = extraQuality
			if ( removeGems.size() > 0 )
				if ( removeGems.get( ( removeGems.size() )-1 ).intValue() == extraQuality )
				{
					return "Remove " + extraQuality + "% from trade.";
				}

			// CALCULATE gemsToRemoveTotal
			//
			for ( int i = 0; i < removeGems.size() ; i++ )
			{
				gemsToRemoveTotal += removeGems.get(i).intValue();
			}

			// CHECK IF gemsToRemoveTotal = extraQuality
			if ( gemsToRemoveTotal == extraQuality )
			{
				String gems = "";

				for ( int i = 0; i < removeGems.size() ; i++ )
				{
					if ( i == removeGems.size()-1 )
						gems = " and " + removeGems.get(i).intValue() + "% ";
					else
						gems = gems.concat(" " + removeGems.get(i).intValue() + "%, ");
				}
				return "You can remove" + gems +".";
			}

			// CHECK COMBINATIONS OF removeGems values to find if any is equal to extraQuality.
			// AT THE SAME TIME, TRACK THE CLOSEST SUM WITH minimum extraQuality loss.
			// RETURN THE BEST RESULT
			return checkBestRemoveGemsCombination( 0, removeGems, new ArrayList<>() );
		}
		
		if ( prismsTotal > 0)
			return "You are missing " + (40 - qualityTotal) + "% quality to complete another prism.";
		
		return "You are missing " + (40 - qualityTotal) + "% quality.";
	}

	/**
	 * Check combinations of removeGems recursively to find the closest sum with minimum extraQuality loss.
	 */
	public String checkBestRemoveGemsCombination( int indexCursor, ArrayList<Integer> removeGems, ArrayList< ArrayList<Integer> > bestChange )
	{
		boolean complete = false;
		ArrayList<Integer> bestGemsToRemove = new ArrayList<>();
		ArrayList< ArrayList<Integer> > bestChangeToRemove = bestChange;
		int sumTotal, altTotal;

		if (removeGems.size() > 0)
		{
			sumTotal = removeGems.get( indexCursor ).intValue();
			altTotal = 0;
			bestGemsToRemove.add( removeGems.get( indexCursor ).intValue() );

			if ( !complete )
			{

				// SUM ALL VALUES OF THE ARRAY STARTING FROM THE CURSOR
				for ( int shifter = indexCursor+1 ; shifter < removeGems.size() && !complete ; shifter++)
				{
					sumTotal += removeGems.get( shifter ).intValue();

					if ( sumTotal == extraQuality )
					{
						bestGemsToRemove.add(removeGems.get( shifter ).intValue());
						complete = true;
						break;
					}

					else if ( sumTotal < extraQuality )
					{
						bestGemsToRemove.add( removeGems.get( shifter ).intValue() );
						bestChangeToRemove.add(bestGemsToRemove);
					}

					else if ( sumTotal > extraQuality )
						break;
				}

				if (!complete)
				{
					// RESETS THE ARRAY bestGemsToRemove to the second test.
					bestGemsToRemove = new ArrayList<>();
					bestGemsToRemove.add( removeGems.get( indexCursor ).intValue() );
				}

				// ALTERNATE SUMS OF VALUES OF X + Y HAVING 
				for ( int altShifter = indexCursor+1; altShifter < removeGems.size() && !complete ; altShifter++ )
				{
					altTotal = removeGems.get(indexCursor) + removeGems.get( altShifter ).intValue();

					if ( altTotal == extraQuality )
					{
						bestGemsToRemove.add( removeGems.get( altShifter ).intValue() );
						complete = true;
						break;
					}			
				}

				if ( !complete )
				{
					indexCursor++;

					if (indexCursor == removeGems.size())
						return "You can't take any gems out and you will lose " + extraQuality + "% quality in this trade.";
					else
						// RECURSIVE CALL TO THIS METHOD.
						checkBestRemoveGemsCombination( indexCursor, removeGems, bestChangeToRemove );
				}
			}


			// IF A PERFECT CHANGE IS FOUND, THEN IS COMPLETE
			// IF COMPLETE, MAKE bestGemsToRemoveString.
			if (complete)
			{
				String bestGemsToRemoveString = new String();

				for ( int y = 0 ; y < bestGemsToRemove.size() ; y++ )
				{
					if ( y == 0 )
						bestGemsToRemoveString = "" + bestGemsToRemove.get( y ).intValue() + "%";

					else if ( y == bestGemsToRemove.size()-1 )
						bestGemsToRemoveString = bestGemsToRemoveString.concat( " and " + bestGemsToRemove.get( y ).intValue() + "% " );

					else
						bestGemsToRemoveString = bestGemsToRemoveString.concat( ", " + bestGemsToRemove.get( y ).intValue() + "%");
				}

				return "You can take " + bestGemsToRemove.size() +" gems out: " + bestGemsToRemoveString + ".";
			}

			int bestChangeTotalIndex = -1;
			int smallestDifference = extraQuality;

			// CALCULATE bestChangeToRemove TOTAL
			for ( int x = 0 ; x < bestChangeToRemove.size() ; x++ )
			{
				int changeTotal = 0;

				for ( int y = 0 ; y < bestChangeToRemove.get(x).size(); y++ )
				{
					changeTotal += bestChangeToRemove.get(x).get(y).intValue();
				}

				if ( (extraQuality - changeTotal) < smallestDifference )
				{
					smallestDifference = (extraQuality - changeTotal);
					bestChangeTotalIndex = x;
				}

			}

			// MAKE bestChangeToRemoveString
			if ( bestChangeToRemove.size() > 0 )
			{
				String bestChangeToRemoveString = new String();
				for ( int y = 0 ; y < bestChangeToRemove.get( bestChangeTotalIndex ).size() ; y++ )
				{
					if ( y == 0 )
						bestChangeToRemoveString = "" + bestChangeToRemove.get( bestChangeTotalIndex ).get( y ).intValue() + "%";

					else if ( y == bestChangeToRemove.get(bestChangeTotalIndex).size()-1 )
						bestChangeToRemoveString = bestChangeToRemoveString.concat( " and " + bestChangeToRemove.get( bestChangeTotalIndex ).get( y ).intValue() + "% " );

					else
						bestChangeToRemoveString = bestChangeToRemoveString.concat( ", " + bestChangeToRemove.get( bestChangeTotalIndex ).get( y ).intValue() + "%");
				}

				return "You can take " + bestChangeToRemoveString + "out and you will lose " + smallestDifference + "% quality in this trade.";
			}
		}

		return "You can't take any gems out and you will lose " + extraQuality + "%.";
	}

	/**
	 * Returns qualityGems reference.
	 * 
	 * @return
	 * ArrayList<Integer> qualityGems;
	 */
	public ArrayList<Integer> getQualityGems()
	{
		return qualityGems;
	}

	/**
	 * Console to test 
	 */
	public static void main(String[]args)
	{
		GemCalculatorMod calc = new GemCalculatorMod();
		Scanner in = new Scanner(System.in);
		int input = 0;
		int size = 0;
		boolean done = true;

		System.out.println("Welcome com POE Gemscutter's Prism Calculator: ");
		System.out.print("Enter how many gems you have:");
		size = in.nextInt();

		int counter = 1;
		do
		{
			System.out.print("Quality Gem #" +counter+ ": ");
			input = in.nextInt();
			calc.addQualityGems( input );
			counter++;
		}
		while(counter<=size);

		in.close();

		calc.sortQualityGems();

		calc.takeTwentyQualityGemsOut();
		calc.calculateGemsQualityTotal();

		System.out.println( calc.calculatePrismsTotal() );

		System.out.println( calc.getGemsToRemove() );
	}


}
