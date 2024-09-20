package com.jdc.im;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Data;

@Embeddable
@Data
public class Address implements Serializable {

	private static final long serialVersionUID = 1L;

	private String township;

	@Column(unique = true)
	private String state;

}
