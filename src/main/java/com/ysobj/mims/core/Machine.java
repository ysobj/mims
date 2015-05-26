package com.ysobj.mims.core;

import lombok.Data;

public @Data class Machine {
	private String id;
	private String name;
	private String provider;
	private String state;
	private String directory;
}
