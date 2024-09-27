package com.jdc.equal;

import com.jdc.creation.Ledger;

public class EqualApp {

	public static void main(String[] args) {
		var ledgerOne = new Ledger(0, "Credit");
		var ledgerTwo = new Ledger(0, "Credit");
		
		System.out.println("""
				Ledger One Address : %s
				Ledger Two Address : %s
				""".formatted(Integer.toHexString(ledgerOne.hashCode()), 
						Integer.toHexString(ledgerTwo.hashCode())));
		
		System.out.println("one == two : " + (ledgerOne == ledgerTwo));
		System.out.println("one equals two : " + ledgerOne.equals(ledgerTwo));
		
	}

}










