package com.preclaim.config;

import java.io.File;

public interface Config {

	String site_name = "Pre-Claim Investigation";
	String version = "UAT version 0.4";
	String upload_directory = "C:\\Pre-Claim Investigation\\uploads" + File.separator;
	String upload_url = "http://172.23.44.9:8444/claims/";
}
