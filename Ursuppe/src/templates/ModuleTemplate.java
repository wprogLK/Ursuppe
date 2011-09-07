 package templates;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Date;

import enums.EColor;
import gameObjectsASCII.*;
import helper.Setting;
import helper.UserInput;
import interfaces.IAmoeba;
import interfaces.IBoard;
import interfaces.ICompassSquare;
import interfaces.IDie;
import interfaces.IGame;
import interfaces.IModule;
import interfaces.IPhase;
import interfaces.IPlayer;
import interfaces.ISoupSquare;

/**
 * used if {@code game} is running in ASCII mode.
 * 
 * @author Lukas Keller
 * @version 1.0.1
 *
 * @see IPhase
 * @see PhaseAASCII
 * @see IGame
 * @see GameASCII
 */
public abstract class ModuleTemplate implements IModule
{
	protected PrintStream outStream;
	protected PrintStream errorStream;
	
	
	public ModuleTemplate(PrintStream out, PrintStream err)
	{
		this.outStream=out;
		this.errorStream=err;
	}
	
	protected final ArrayList<IAmoeba> createAllAmoebas(EColor color)
	{
		ArrayList<IAmoeba> amoebas=new ArrayList<IAmoeba>();
		
		for(int nr=1; nr<Setting.maxNumbersOfAmoebasPerPlayer+1; nr++)
		{
			IAmoeba amoeba=this.createAmoeba(color, nr);
			amoebas.add(amoeba);
		}
		
		return amoebas;
	}
}
