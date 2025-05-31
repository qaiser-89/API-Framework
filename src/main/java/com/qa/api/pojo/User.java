package com.qa.api.pojo;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(Include.NON_NULL) 
public class User {
	private Integer id;
	private String name;
	private String email;
	private String gender;
	private String status;
	

}
/*
 * if we have to use the same pojo for 2 different methods/classes 
 * for serialization in 1 method and for deserialization for different method we need to add include
 * NON_NULL arguments so if creating method we dont need id and for Deserialization we need id
 * so create method will not include id
 */
