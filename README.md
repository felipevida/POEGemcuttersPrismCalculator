# POEGemcuttersPrismCalculator

# INFO
This calculator aims to help Path of Exile players decision making when doing Gemcutter's Prism (GCP) vendor recipe.

In Path of Exile there is no money like gold, instead, the currency of the game is based on items that help players on crafting weapons and gear. There are lots of different recipes in this game that are based on this same principle, however, a GCP's value has a significant weight if a player manages to use this recipe with maximum efficiency.

# HOW THE RECIPE WORKS
Whenever the total of quality gems in a trade reach 40% quality, a GCP is given as payment.

Another important info is that a gem with 20% quality is equal to a GCP.
THIS CONDITION STILL NEED TO BE IMPLEMENTED IN THE PRESENT CODE.

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
Variables names and comments are yet to be added to explain the logic and how the program works on posted files.
Beforehand I know that this is not the most efficient approach for this program, and it's open for improvement.

Please open the .java files to test the first version, and remember that there are NO input validations, neither conditions for Gems input of 20%.

"If necesseray, and I believe it is, we can restart from scratch, I do have some extra code that it's posted too".
