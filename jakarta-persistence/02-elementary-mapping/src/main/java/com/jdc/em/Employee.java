package com.jdc.em;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.SecondaryTable;
import jakarta.persistence.SecondaryTables;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.persistence.Transient;
import jakarta.persistence.UniqueConstraint;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@Entity @Table(name = "employee_tbl")
@SecondaryTables({
	@SecondaryTable(
			name = "address_tbl",
			uniqueConstraints = @UniqueConstraint(columnNames = "state")
		),
	@SecondaryTable(
			name = "contact_tbl",
			indexes = {
					@Index(columnList = "email"),
					@Index(columnList = "phone")
			},
			uniqueConstraints = {
					@UniqueConstraint(columnNames = "email")
			}
		)
	
})
public class Employee implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	private int id;
	
	private Deparment deparment;
	
	@Column(nullable = false, length = 100)
	private String name;
	
	@Enumerated(EnumType.STRING)
	private Gender gender;
	
	@Column(precision = 15)
	private double salary;
	
	@Column(precision = 15, scale = 3)
	private BigDecimal networth;

	@Column(table = "contact_tbl", unique = true, 
			columnDefinition = "varchar(20) check(length(phone) > 9)")
	private String phone;
	
	@Column(table = "contact_tbl")
	private String email;
	
	@Column(nullable = false, table = "address_tbl")
	private String township;
	@Column(nullable = false, table = "address_tbl")
	private String state;	
	
	@Column(nullable = false, name = "assign_at")
	@Temporal(TemporalType.TIMESTAMP)
	private Date assignAt;
	
	@Transient
	private boolean deleted;
	
	public enum Gender {
		Male, Female, Other
	}
	
//	private java.util.Date utilDate;
//	private java.util.Calendar utilCalendar;	
//	private List<String> strList;

//	private java.sql.Date sqlDate;
//	private java.sql.Time sqlTime;
//	private java.sql.Timestamp sqlTimestamp;
//	private LocalDate jodaDate;
//	private LocalTime jodaTime;
//	private LocalDateTime jodaDateTime;
//	private Year jodaYear;
//	private DayOfWeek jodaDayOfWeek;
//	private Instant jodaInstant;
//	private Month jodaMonth;
//	private Duration jodaDuration;
//	private Period jodaPeriod;
	
//	private byte byteCol;
//	private short shortCol;
//	private int intCol;
//	private long longCol;
//	private char charCol;
//	private boolean boolCol;
//	private float floatCol;
//	private double doubleCol;
//	private BigInteger bigIntgerCol;
//	private BigDecimal bigDecimalCol;
//	
//	private String[] arrayCol;
//	
//	private String stringCol;

}