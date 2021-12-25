package com.dcho.bhc;

import javax.ws.rs.core.Application;

import java.util.Set;

//import org.glassfish.jersey.jackson.JacksonFeature;

@javax.ws.rs.ApplicationPath("webresources")
public class ApplicationConfig extends Application {
	@Override
	public Set<Class<?>> getClasses(){
		Set<Class<?>> resources = new java.util.HashSet<>();
		addRestResourceClasses(resources);
		return resources;
	}

	private void addRestResourceClasses(Set<Class<?>> resources) {
		// TODO Auto-generated method stub
		resources.add(com.dcho.bhc.Calculator.class);
		//resources.add(JacksonFeature.class);
		
	}
}