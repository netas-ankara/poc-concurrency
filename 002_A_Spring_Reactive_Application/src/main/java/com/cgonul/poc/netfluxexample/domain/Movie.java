package com.cgonul.poc.netfluxexample.domain;

import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Document
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Movie {

	private String id;

	@NonNull
	private String title;
}
