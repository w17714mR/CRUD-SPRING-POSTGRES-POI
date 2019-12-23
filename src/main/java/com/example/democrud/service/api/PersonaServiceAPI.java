package com.example.democrud.service.api;

import java.io.ByteArrayInputStream;

import com.example.democrud.commons.GenericServiceAPI;
import com.example.democrud.model.Persona;

public interface PersonaServiceAPI extends GenericServiceAPI<Persona, Long>  {
	
	
	ByteArrayInputStream exportAllData() throws Exception;
	
}
