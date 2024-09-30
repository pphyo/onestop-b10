package com.jdc.iami.entity;

import java.io.Serializable;
import java.time.LocalDateTime;

import lombok.Data;

@Data
public class SalePK implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private Long customerId;
	private Long productId;
	private LocalDateTime saleDate;

}
