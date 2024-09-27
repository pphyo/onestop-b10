package com.jdc.creation;

import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Ledger implements Cloneable {

	static final Logger LOGGER = Logger.getLogger(Ledger.class.getSimpleName());

	private int id;
	private String type;

	public Ledger() {
		LOGGER.log(Level.INFO, "Ledger Default Contructor is called.");
	}

	public Ledger(int id) {
		this.id = id;
		LOGGER.log(Level.INFO, "Ledger Contructor with int param is called.");
	}

	public Ledger(String type) {
		this.type = type;
		LOGGER.log(Level.INFO, "Ledger Contructor with string param is called.");
	}

	public Ledger(int id, String type) {
		this.id = id;
		this.type = type;
		LOGGER.log(Level.INFO, "Ledger Contructor with int and string params is called.");
	}

	@Override
	protected Ledger clone() throws CloneNotSupportedException {
		return new Ledger();
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, type);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Ledger other = (Ledger) obj;
		return id == other.id && Objects.equals(type, other.type);
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

}
