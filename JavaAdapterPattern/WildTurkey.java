/*
 * From:
 * Eric and Elizabeth Freeman: Head first design patterns, O'Reilly 2004
 * 
 */

public class WildTurkey implements Turkey {

	@Override
	public void gobble() {
		System.out.println("WildTurkey: gobble, gobble!");
		
	}

	@Override
	public void fly() {
		System.out.println("I am a flying Wild Turkey!");
	}

}
