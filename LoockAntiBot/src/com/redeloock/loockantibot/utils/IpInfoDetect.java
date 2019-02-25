package com.redeloock.loockantibot.utils;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.InetSocketAddress;
import java.net.URL;

public class IpInfoDetect {
	String chave = "";
	String urlString = "https://www.ipqualityscore.com/api/json/ip/" + chave + "/";
	URL url;
	BufferedReader stream;
	StringBuilder entirePage;
	String inputLine;

	public IpInfoDetect(InetSocketAddress ip) throws Exception {
		url = new URL(urlString + ip.getHostName());
		stream = new BufferedReader(new InputStreamReader(url.openStream()));
		entirePage = new StringBuilder();
		while ((inputLine = stream.readLine()) != null)
			entirePage.append(inputLine);
		stream.close();
	}

	public byte getFraudScore() throws Exception {
		if (!(entirePage.toString().contains("\"fraud_score\":\"")))
			new Exception("Impossivel localizar dados!");
		return Byte.parseByte(entirePage.toString().split("\"fraud_score\":\"")[1].split("\",")[0]);
	}

	public boolean isProxy() throws Exception {
		if (!(entirePage.toString().contains("\"proxy\":\"")))
			new Exception("Impossivel localizar dados!");
		return (entirePage.toString().split("\"proxy\":\"")[1].split("\",")[0]).equals("true");
	}

	public boolean isTor() throws Exception {
		if (!(entirePage.toString().contains("\"tor\":\"")))
			new Exception("Impossivel localizar dados!");
		return (entirePage.toString().split("\"tor\":\"")[1].split("\",")[0]).equals("true");
	}

	public boolean isVpn() throws Exception {
		if (!(entirePage.toString().contains("\"vpn\":\"")))
			new Exception("Impossivel localizar dados!");
		return (entirePage.toString().split("\"vpn\":\"")[1].split("\",")[0]).equals("true");
	}
}
