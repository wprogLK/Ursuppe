
package tests.templates;

import module.ModuleASCII;



/**
 * @author Lukas Keller
 * @version 1.0.0
 *
 */
public class ASCIITestTemplate extends TestTemplate
{
	public ASCIITestTemplate()
	{
		super();
	}
	
	public final void createModule()
	{
		this.module=new ModuleASCII(this.outStream,this.errStream);
	}
}






		
		
