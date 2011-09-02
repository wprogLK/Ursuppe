package enums;

/**
 * Contains all possible type of modes to run the game.
 * 
 * <p><u> Overview:</u> </p>
 * <table border="1">
 * 	<tr>
 * 		<th> Mode </th>
 * 		<th> Description </th>
 * 	</tr>
 * 	<tr>
 * 		<td> GUI </td>
 * 		<td> runs the with a GUI </td>
 * 	</tr>
 * <tr>
 * 		<td> ASCII </td>
 * 		<td> runs the in a console </td>
 * 	</tr>
 * <tr>
 * 		<td> Test </td>
 * 		<td> this mode is only for debugging and testing. It's needed when a JUnit test running the game </td>
 * 	</tr>
 * </table>
 * 
 * <br>
 * 
 * @author Lukas Keller
 * @version 1.0.0
 *
 */
public enum EMode 
{
	guiMode,
	asciiMode,
	testMode;
}
