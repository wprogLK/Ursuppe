
public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) 
	{
		TechDemoEnums techDemoEnums=new TechDemoEnums();
		
		techDemoEnums.showDefault();
		
		techDemoEnums.config1();
		//techDemoEnums.show();
		techDemoEnums.showWithIterator();
		
		techDemoEnums.config2();
		//techDemoEnums.show();
		techDemoEnums.showWithIterator();
		
		techDemoEnums.setConfigNr(1);
		System.out.println("***********RE-SHOW OF CONFIG 1:*********");
		techDemoEnums.showWithIterator();
		
		
		System.out.println("***********PLAY ONCE CONFIG 2:*********");
		techDemoEnums.setConfigNr(2);
		techDemoEnums.showCurrentPlayer();
		//techDemoEnums.currentPlayerPlay();
		techDemoEnums.showCurrentPlayer();
		
		System.out.println("***********RE-SHOW OF CONFIG 2:*********");
		techDemoEnums.showWithIterator();
	}

}
