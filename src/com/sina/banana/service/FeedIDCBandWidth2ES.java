package com.sina.banana.service;

import java.util.Calendar;
import java.util.List;
import java.util.Map;

import com.sina.banana.dao.IDCBandwidth;
import com.sina.banana.dao.impl.duomoapi.IDCBandwidthImplByDuomoAPI;

import org.apache.log4j.Logger;
import org.elasticsearch.client.Client;

public class FeedIDCBandWidth2ES {
	
	private static Logger logger = Logger.getLogger(FeedIDCBandWidth2ES.class); 	
	private IDCBandwidth idcBandwidth;
	private Client esClient;

	public FeedIDCBandWidth2ES(Client esClient) {
		super();
		this.idcBandwidth = new IDCBandwidthImplByDuomoAPI();
		this.esClient = esClient;
	}
	
	public void addLog(Calendar from,Calendar to,String step){
		
		List<Map<String, Object>> IDCList = null;
		try {
			IDCList = idcBandwidth.getIDCList();
			System.out.println(IDCList.size());
		} catch (Exception e) {
			logger.error("IDC列表获取失败:"+e.getMessage());			
			return;
		}
		for(Map<String, Object> IDC:IDCList){
			logger.debug(IDC.get("wh")+":"+IDC.get("dev")+":"+IDC.get("name"));
			Map<String, List<Double>> bandwidth;
			try {
				bandwidth = idcBandwidth.getIDCBandwidth(IDC, from, to, step );
				logger.debug("size:"+bandwidth.get("inMax").size()+",outMax:"+bandwidth.get("outMax").get(0)+",inMax:"+bandwidth.get("inMax").get(0));	
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
