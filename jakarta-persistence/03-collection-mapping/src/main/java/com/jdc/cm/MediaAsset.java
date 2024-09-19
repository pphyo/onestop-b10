package com.jdc.cm;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@Entity @Table(name = "media_asset_tbl")
public class MediaAsset implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	private Integer id;
	private String name;
	private String format;
	
	@ElementCollection
	@CollectionTable(name = "permission_tbl", 
		joinColumns = @JoinColumn(name = "meida_asset_id"))
	@Column(name = "permission", nullable = false)
	@Enumerated(EnumType.STRING)
	private List<Permission> permissions;
	
	@ElementCollection
	@Column(name = "permission_number", nullable = false)
	private Set<Integer> permissionNumbers;
	
	public enum Permission {
		READ, WRITE, EXECUTABLE
	}

}









