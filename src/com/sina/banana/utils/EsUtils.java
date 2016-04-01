package com.sina.banana.utils;

import java.net.InetAddress;
import java.net.UnknownHostException;

import org.elasticsearch.client.Client;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;

public class EsUtils {
	
	public static Client  getClient(byte[] addr) throws UnknownHostException{
		//byte addr[] = {10,(byte)210,(byte)229,(byte)227};
		Settings settings = Settings.settingsBuilder()
		        .put("cluster.name", "sina-idc-es").build();//sina-idc-test sina-idc-es
		Client client = TransportClient.builder().settings(settings).build().addTransportAddress(new InetSocketTransportAddress(InetAddress.getByAddress(addr), 9300));
		/*Client client = TransportClient.builder().build()
					.addTransportAddress(new InetSocketTransportAddress(InetAddress.getByAddress(addr), 9300));*/
		return client;
	}
	
	public static Client  getClient(String host) throws UnknownHostException{
		Settings settings = Settings.settingsBuilder()
		        .put("cluster.name", "sina-idc-es").build();//sina-idc-test sina-idc-es
		Client client = TransportClient.builder().settings(settings).build().addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName(host), 9300));
		return client;
	}

}
