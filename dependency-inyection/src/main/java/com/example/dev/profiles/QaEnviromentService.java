package com.example.dev.profiles;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Service
@Profile({"qa", "default"})
public class QaEnviromentService implements EnviromentService{

	@Override
	public String getEnviroment() {
		return "Qa";
	}
	

}
