package helpClasses;

import java.util.ArrayList;
import phases.IPhase;


public abstract class InterpretAndRunBasicUserInput {
	private static IPhase phase;
	
	public static void readAndInterpretInput(String input, IPhase phaseInput)
	{
		phase=phaseInput;
		
		if (input.length()==1)
		{
			interpretOneCharInput(input);
		}
		else if (input.length()==2)
		{
			interpretTwoCharInput(input);
		}
		else
		{
			phase.doErrorInputTryAgainAllPhases(input);
		}
	}
	
	public static String possibleStandardInstructions()
	{
		ArrayList<String> arrStr=new ArrayList<String>();
		
		createNewStringAndAddToArrayList("You can always do following actions:",arrStr);
		createNewStringAndAddToArrayList(getOneCharInstr(),arrStr);
		createNewStringAndAddToArrayList(getTwoCharInstr(),arrStr);
		createNewStringAndAddToArrayList(getMultiCharInstr(),arrStr);
		
		return doNewLines(arrStr);
	}

	private static String getMultiCharInstr() {
		return "";
	}

	private static String getTwoCharInstr() {
		ArrayList<String> arrStr=new ArrayList<String>();
		
		createNewStringAndAddToArrayList("\t - <wp>: go back to the Welcome menu",arrStr);
		createNewStringAndAddToArrayList("\t - <ep>: exit",arrStr);
		createNewStringAndAddToArrayList("\t - <np>: go to the next phase if possible",arrStr);
		createNewStringAndAddToArrayList("\t - <pp>: go back to the previous phase",arrStr);
		createNewStringAndAddToArrayList("\t - <ap>: see information about this programm",arrStr);
		createNewStringAndAddToArrayList("\t - <sp>: see the summary of the current game",arrStr);
		createNewStringAndAddToArrayList("\t - <cp>: go to the cheats",arrStr);
		
		return doNewLines(arrStr);
	}

	private static String getOneCharInstr() {
		ArrayList<String> arrStr=new ArrayList<String>();
		
		
		createNewStringAndAddToArrayList("\t - <s>: Save the current game",arrStr);
		createNewStringAndAddToArrayList("\t - <l>: Load a game",arrStr);
		
		return doNewLines(arrStr);
		
	}
	
	private static void createNewStringAndAddToArrayList(String str, ArrayList<String> arrStr)
	{
		arrStr.add(str);
	}

	private static void interpretMultiCharInput(String input) 
	{
		//TODO Later (at the moment not necessary)
	}
	
	private static String doNewLines(ArrayList<String> arrStr)
	{
		String str="";
		
		for (int i=0; i<arrStr.size();i++)
		{
			str=str+arrStr.get(i) + "\n";
		}
		
		return str;
	}

	private static void interpretTwoCharInput(String input) 
	{
		if (input.matches("wp"))
		{
			phase.doGoToWelcomePhase();
		}
		else if (input.matches("ep"))
		{
			phase.doGoToExitPhase();
		}
		else if (input.matches("np"))
		{
			phase.doGoToNextPhase();
		}
		else if (input.matches("pp"))
		{
			phase.doGoToPreviousPhase();
		}
		else if (input.matches("ap"))
		{
			phase.doGoToAboutPhase();
		}
		else if (input.matches("sp"))
		{
			phase.doGoToSummaryPhase();
		}
		else if (input.matches("cp"))
		{
			phase.doGoToCheatPhase();
		}
		else
		{
			phase.doErrorInputTryAgainAllPhases(input);
		}
	}

	private static void interpretOneCharInput(String input) {
		char a=input.charAt(0);
		
		switch (a)
		{
			case 's':
			{
				phase.doGoToSavePhase();
				
				break;
			}
			case 'l':
			{
				phase.doGoToLoadPhase();
				
				break;
			}
			default:
			{
				phase.doErrorInputTryAgainAllPhases(input);
			}
	
		}
	}
	

}
