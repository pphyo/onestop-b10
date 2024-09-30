package com.jdc.cl.test;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import com.jdc.cl.entity.Account;
import com.jdc.cl.entity.Contact;

@TestMethodOrder(OrderAnnotation.class)
public class ListenerTest extends BaseTest {

	@Order(1)
	@DisplayName("Test for inserting account")
	@ParameterizedTest
	@CsvFileSource(resources = "/data/account.txt")
	void test1(String name, int amount) {
		Account account = new Account();
		account.setName(name);
		account.setAmount(amount);

		em.getTransaction().begin();
		em.persist(account);
		em.getTransaction().commit();
	}

	@Order(2)
	@DisplayName("Test for update account")
	@ParameterizedTest
	@CsvSource({ "1, 500000" })
	void test2(int id, int amountToUpdate) {
		var account = em.getReference(Account.class, 1);
		account.setAmount(amountToUpdate);

		em.getTransaction().begin();
		em.merge(account);
		em.getTransaction().commit();
	}

	@Order(3)
	@DisplayName("Test for delete account")
	@ParameterizedTest
	@ValueSource(ints = 2)
	void test3(int idForDelete) {
		var account = em.find(Account.class, idForDelete);

		em.getTransaction().begin();
		em.remove(account);
		em.getTransaction().commit();
	}

	@Order(4)
	@DisplayName("Test for create contact")
	@ParameterizedTest
	@CsvSource({ "someone@gmail.com, +1339499543", "david@microsoft.com, +6439494332" })
	void test4(String email, String phone) {
		var contact = new Contact();
		contact.setEmail(email);
		contact.setPhone(phone);

		em.getTransaction().begin();
		em.persist(contact);
		em.getTransaction().commit();
	}

}
