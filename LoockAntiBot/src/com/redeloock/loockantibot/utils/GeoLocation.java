package com.redeloock.loockantibot.utils;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.InetSocketAddress;
import java.net.URL;

public class GeoLocation {
	URL url;
	BufferedReader stream;
	StringBuilder entirePage;
	String inputLine;

	public GeoLocation(InetSocketAddress ip) throws Exception {
		url = new URL("http://ip-api.com/json/" + ip.getHostName());
		stream = new BufferedReader(new InputStreamReader(url.openStream()));
		entirePage = new StringBuilder();
		while ((inputLine = stream.readLine()) != null)
			entirePage.append(inputLine);
		stream.close();

	}

	public String getCountryCode() throws Exception {
		if (!(entirePage.toString().contains("\"countryCode\":\"")))
			new Exception("Impossivel localizar dados!");
		return entirePage.toString().split("\"countryCode\":\"")[1].split("\",")[0];
	}

	public String getCountry() throws Exception {
		if (!(entirePage.toString().contains("\"country\":\"")))
			new Exception("Impossivel localizar dados!");
		return entirePage.toString().split("\"country\":\"")[1].split("\",")[0];
	}

	public String getState() throws Exception {
		if (!(entirePage.toString().contains("\"regionName\":\"")))
			new Exception("Impossivel localizar dados!");
		return entirePage.toString().split("\"regionName\":\"")[1].split("\",")[0];
	}
}
