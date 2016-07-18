package com.alexbt.mongodb;

import java.io.IOException;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

@SpringBootApplication
public class Launcher {
	public static void main(String[] args) throws IOException {
		new SpringApplicationBuilder() //
				.sources(Launcher.class)//
				.run(args);
	}
}