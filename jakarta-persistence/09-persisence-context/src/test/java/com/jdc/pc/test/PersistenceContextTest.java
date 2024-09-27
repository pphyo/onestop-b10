package com.jdc.pc.test;

import java.time.Duration;

import org.junit.jupiter.api.Test;

import com.jdc.pc.entity.Account;

import jakarta.persistence.Persistence;

public class PersistenceContextTest {
	
	@Test
	void test() {
		try(var emf = Persistence.createEntityManagerFactory("pc");
				var em1 = emf.createEntityManager();
				var em2 = emf.createEntityManager()) {
			
			var operationOne = new Thread(() -> {
				
				var account = em1.find(Account.class, 1);
				
				try {
					Thread.sleep(Duration.ofMillis(400));
				} catch (Exception e) {
					e.printStackTrace();
				}
				
				em1.getTransaction().begin();
				
				System.out.println("Before operation one, amount of %s is %d."
						.formatted(account.getName(), account.getAmount()));

				em1.refresh(account);
				account.setAmount(account.getAmount() + 50000);
				em1.flush();

				System.out.println("After operation one, amount of %s is %d"
							.formatted(account.getName(), account.getAmount()));
				
				em1.getTransaction().commit();
				
			});
			
			var operationTwo = new Thread(() -> {
				
				var account = em2.find(Account.class, 1);
				
				try {
					Thread.sleep(Duration.ofMillis(800));
				} catch (Exception e) {
					e.printStackTrace();
				}
				
				em2.getTransaction().begin();
				
				System.out.println("Before operation two, amount of %s is %d."
						.formatted(account.getName(), account.getAmount()));
				
				em2.refresh(account);
				account.setAmount(account.getAmount() - 100000);
				em2.flush();
				
				System.out.println("After operation two, amount of %s is %d"
							.formatted(account.getName(), account.getAmount()));
				
				em2.getTransaction().commit();
				
			});
			
			operationOne.start();
			operationTwo.start();
			
			try {
				Thread.sleep(Duration.ofMillis(1000));
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		}
	}

}










