package com.jdc.cl.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@Entity @Table(name = "transfer_log")
public class TransferLog extends AuditingEntity {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(name = "from_name", nullable = false)
	private String fromName;
	
	@Column(name = "to_name", nullable = false)
	private String toName;
	
	@Column(name = "transfer_amount", nullable = false)
	private int transferAmount;

}
