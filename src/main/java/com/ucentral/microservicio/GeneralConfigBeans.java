package com.ucentral.microservicio;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

@Configuration
public class GeneralConfigBeans {

	@Bean
	@Scope("singleton")
	public ModelMapper modelMapper() {

		ModelMapper mapper = new ModelMapper();

		mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);

		mapper.getConfiguration().setAmbiguityIgnored(true);

		return mapper;
	}

}
