package com.jdc.bm.test;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

import com.jdc.bm.entity.Player;
import com.jdc.bm.entity.Team;

public final class BMOneToManyTest extends BaseTest {
	
	@Test
	void test_for_insert_one_to_many_bi() {
		
		var tx = em.getTransaction();
		
		tx.begin();
		
		var manu = new Team();
		manu.setName("Manchester United");
		manu.setFoundedAt(LocalDate.of(1950, 02, 01));
		manu.setTrophy(22);
		
		var onana = new Player();
		onana.setName("Andre Onana");
		onana.setAge(28);
		onana.setJersyNo(24);
		onana.setPosition("GK");
		manu.addPlayer(onana);
		
		var maguire = new Player();
		maguire.setName("Harry Maguire");
		maguire.setAge(31);
		maguire.setJersyNo(5);
		maguire.setPosition("CB");
		manu.addPlayer(maguire);
		
		var bruno = new Player();
		bruno.setName("Bruno Fernandes");
		bruno.setAge(30);
		bruno.setJersyNo(8);
		bruno.setPosition("AMF");
		manu.addPlayer(bruno);
		
		em.persist(manu);
		
		tx.commit();
		
	}

}













