package com.greatLearning.employeeManagement.entity;

import javax.persistence.*;

import lombok.Data;

@Entity
@Table(name = "roles")
@Data
public class Role {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "role_id")
	private Integer id;

	@Column(name = "name")
	private String name;
}
