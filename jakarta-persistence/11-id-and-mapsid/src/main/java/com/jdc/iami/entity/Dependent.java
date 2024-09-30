package com.jdc.iami.entity;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinColumns;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import lombok.Data;

@Data
@Entity
public class Dependent {

	@EmbeddedId
	private DependentId id;

	@MapsId("empPK")
	@ManyToOne
	@JoinColumns({
		@JoinColumn(name = "PK_frist_name", referencedColumnName = "firstName"),
		@JoinColumn(name = "PK_last_name", referencedColumnName = "lastName")
	})
	private Employee emp;

}
