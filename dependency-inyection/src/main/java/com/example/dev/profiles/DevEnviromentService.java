package com.example.dev.profiles;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Service
@Profile("dev")
public class DevEnviromentService implements EnviromentService {

	@Override
	public String getEnviroment() {
		return "Dev";
	}
}
