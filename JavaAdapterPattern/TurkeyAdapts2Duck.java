/*
 * From:
 * Eric and Elizabeth Freeman: Head first design patterns, O'Reilly 2004
 * 
 */

public class TurkeyAdapts2Duck implements Duck {

	private final Turkey turkey;
	
	public TurkeyAdapts2Duck(Turkey turkey) {
		this.turkey = turkey;
	}
	
	@Override
	public void quack() {
		turkey.gobble();
	}

	@Override
	public void fly() {
		for (int i=0; i < 5; i++) {
			turkey.fly();
		}
	}
}
