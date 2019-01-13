import java.util.ArrayList;
import java.util.Collections;

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
		qualityGems = new ArrayList<>();
		twentyQualityGems = new ArrayList<>();

		qualityTotal = prismsTotal = 0;
	}

	public void sortQualityGems()
	{
		Collections.sort( qualityGems );
	}

	/**
	 * Takes all 20% quality gems to add to prismsTotal.
	 */
	public void getTwentyQualityGems()
	{
		for (int i = qualityGems.size()-1 ; i >= 0; i--)
		{
			if ( ( (Integer) qualityGems.get( i ) ) == 20 )
			{
				twentyQualityGems.add( (Integer) qualityGems.get(i) );
				qualityGems.remove( i );
			}
			else
				break;
		}
	}

	/**
	 * Adds Integers to the ArrayList qualityGems.
	 */
	public void addQualityGems( Integer input )
	{
		qualityGems.add( input );
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
	public void calculatePrismsTotal()
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
	}


	/**
	 * This method will add to ArrayList removeGems values from ArrayList gemsQuality that are less equal than extraQuality.
	 * Then it will check the best combination to avoid losing quality gems, if possible.
	 * 
	 * @param extraQuality
	 */
	public String getGemsToRemove()
	{		
		int gemsToRemoveTotal = 0;


		// GET ALL qualityGems THAT ARE LESS EQUAL THAN extraQuality VALUE and ADD TO removeGems.
		for (int i = 0 ; i < qualityGems.size(); i++)
		{
			if ( qualityGems.get(i).intValue() <= extraQuality )
			{
				removeGems.add( qualityGems.get(i).intValue() );
			}
		}

		// CHECK IF THE LAST removeGem value is = extraQuality
		if ( removeGems.get(removeGems.size()-1).intValue() == extraQuality)
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
		return getBestRemoveGemsCombination(0, removeGems);
	}
	
	/**
	 * Check combinations of removeGems recursively to find the closest sum with minimum extraQuality loss.
	 */
	public String getBestRemoveGemsCombination(int ref, ArrayList<Integer> removeGems)
	{
		// TODO
    		// I'm working on this at this moment.		
		return "";
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
