package com.ss.storyboxservice.model;

import java.time.LocalDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonAlias;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Journal {

	@JsonAlias(value = "dbId")
	String id;
	String text;
	String username;
	String tags;
	List<String> img;
	LocalDateTime date;


}
