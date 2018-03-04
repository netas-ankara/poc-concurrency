package com.cgonul.poc.netfluxexample.domain;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MovieEvent {

	@NonNull
	private String movieId;

	@NonNull
	private Date date;

}
