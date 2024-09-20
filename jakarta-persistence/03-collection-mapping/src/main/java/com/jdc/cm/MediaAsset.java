package com.jdc.cm;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.MapKeyColumn;
import jakarta.persistence.MapKeyEnumerated;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "media_asset_tbl")
public class MediaAsset implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	private Integer id;
	private String name;
	private String format;

	@ElementCollection
	@CollectionTable(name = "permission_tbl", joinColumns = @JoinColumn(name = "meida_asset_id"))
	@Column(name = "permission", nullable = false)
	@Enumerated(EnumType.STRING)
	private List<Permission> permissions;

//	@ElementCollection
//	@Column(nullable = false)
//	private Deque<LocalDateTime> modifications;
//	
//	@ElementCollection
//	@Column(name = "permission_number", nullable = false)
//	private Set<Integer> permissionNumbers;
//	
//	@ElementCollection
//	@CollectionTable(name = "media_asset_tag", 
//		joinColumns = @JoinColumn(name = "media_asset_id"))
//	@MapKeyColumn(name = "tag_key")
//	@Column(name = "tag", nullable = false)
//	private Map<Integer, String> tags;

	@ElementCollection
	@MapKeyColumn(name = "permission_map_key")
	@MapKeyEnumerated(EnumType.STRING)
	@Enumerated(EnumType.STRING)
	private Map<Permission, PermissionNumber> permssionMap;

	public enum Permission {
		EXECUTABLE, WRITE, READ
	}

	public enum PermissionNumber {
		ONE, TWO, FOUR
	}

}
