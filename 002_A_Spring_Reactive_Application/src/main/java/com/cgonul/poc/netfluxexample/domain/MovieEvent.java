package com.cgonul.poc.netfluxexample.domain;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data
@NoArgsConstructor
public class MovieEvent {
	private String movieId;

	@NonNull
	private String title;

}
