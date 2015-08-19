package com.tma.config;

import java.net.InetAddress;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(name = "connection")
public class ApplicationSetup {
	private static final Logger log = LoggerFactory
			.getLogger(ApplicationSetup.class);
	private String username;
	private InetAddress remoteAddress;

	public ApplicationSetup() {
	}

	public ApplicationSetup(String username, InetAddress remoteAddress) {
		this.username = username;
		this.remoteAddress = remoteAddress;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public InetAddress getRemoteAddress() {
		return remoteAddress;
	}

	public void setRemoteAddress(InetAddress remoteAddress) {
		this.remoteAddress = remoteAddress;
	}

	@Override
	public String toString() {
		return username + " : " + remoteAddress;
	}

	@PostConstruct
	public void xxx() {
		log.info("Initialized [{}] [{}]", username, remoteAddress);
	}
}
