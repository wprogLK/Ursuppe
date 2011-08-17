
package tests;
/**
 * 
 */
import static org.junit.Assert.*;

import java.util.ArrayList;

import enums.EPhases;
import helper.ErrorLogger;
import helper.ReadAndWriteFiles;
import helper.Setting;
import helper.UserInput;
import interfaces.IGame;
import interfaces.IPhase;
import interfaces.IPlayer;

import org.junit.Test;
import ch.unibe.jexample.JExample;
import ch.unibe.jexample.Given;


import org.junit.runner.RunWith;

import templates.ASCIITestTemplate;


@RunWith(JExample.class)
public class ErrorLoggerTest
{	
	/**
	 * 
	 */
	@Test
	public void errorLoggerTest() 
	{
		try 
		{
			//throw new IllegalArgumentException("PhysicalExam.scaleBloodPressure: age is negative");
			throw new IllegalArgumentException("2nd exception");
		} 
		catch (IllegalArgumentException e) 
		{
			ErrorLogger.logAError(e);
		}
		
	}
	

	

	
	
	
}






	
	