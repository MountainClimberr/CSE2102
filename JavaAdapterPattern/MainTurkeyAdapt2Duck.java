/*
 * Based on
 * Eric and Elizabeth Freeman: Head first design patterns, O'Reilly 2004
 * 
 */

public class MainTurkeyAdapt2Duck {
	
	public static void main(String[] args) {
		MallardDuck mDuck = new MallardDuck();
		
		WildTurkey wTurkey = new WildTurkey();
		
		Duck turkeyAdapted2Duck = new TurkeyAdapts2Duck(wTurkey);
		
		System.out.println("\n Turkey says:");
		wTurkey.gobble();
		wTurkey.fly();
		
		System.out.println("\n Duck says:");
		testDuck(mDuck);
		
		System.out.println("\n Turkey adapted to Duck says:");
		testDuck(turkeyAdapted2Duck);
	}
	
	private static void testDuck(Duck duck) {
		duck.quack();
		duck.fly();
	}
	

}
