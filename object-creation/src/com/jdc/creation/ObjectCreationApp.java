package com.jdc.creation;

public class ObjectCreationApp {
	
	@SuppressWarnings("all")
	public static void main(String[] args) {
		
		try {
			Ledger newLedger = new Ledger(0, null);
			System.out.println("Using new keyword: " + newLedger);
			
			Ledger newInstance = Ledger.class.newInstance(); // must have default constructor otherwise exception occur
			System.out.println("Using Class.newInstance: " + newInstance);
			
			var forName = Class.forName("com.jdc.creation.Ledger").newInstance();
			System.out.println("Using Class.forName: " + forName);
			
			var constructor = Ledger.class.getConstructor(int.class, String.class).newInstance(10, "Hello");
			System.out.println("Using Class.getConstructor: " + constructor);
			
			var clone = constructor.clone();
			System.out.println("Using clonable: " + clone);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

}






