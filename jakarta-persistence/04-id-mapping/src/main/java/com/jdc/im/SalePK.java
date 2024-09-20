package com.jdc.im;

import java.io.Serializable;
import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Data;

@Data
@Embeddable
public class SalePK implements Serializable {

	private static final long serialVersionUID = 1L;

	@Column(name = "sale_id")
	private int saleId;
	@Column(name = "sale_date")
	private LocalDateTime saleDate;

}
