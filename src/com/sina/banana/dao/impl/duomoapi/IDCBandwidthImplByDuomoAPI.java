package com.sina.banana.dao.impl.duomoapi;

import java.io.IOException;
import java.net.URL;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.sina.banana.dao.IDCBandwidth;
import com.sina.banana.utils.LoadJsonString;

public class IDCBandwidthImplByDuomoAPI implements IDCBandwidth {

	private static Logger logger = Logger.getLogger(IDCBandwidthImplByDuomoAPI.class); 

	@SuppressWarnings("unchecked")
	@Override
	public List<Map<String, Object>>  getIDCList(Calendar when) throws Exception {
		List<Map<String, Object>> IDCList = null;	
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
		String uri = "http://api.duomo.intra.sina.com.cn/index.php/api/api/getIdcFlow?"
	               + "s_day="+simpleDateFormat.format(when.getTime())
	               + "&day_or_month=day";	
		URL url = new URL(uri); 			
		ObjectMapper mapper = new ObjectMapper(); 
		ObjectNode root = (ObjectNode) mapper.readTree(url);
		String data = root.get("aaData").toString();
		IDCList = mapper.readValue(data, List.class);
		logger.debug(url+"IDCList_size:"+"==="+IDCList.size());
		return IDCList;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Map<String, Object>>  getIDCList() throws JsonProcessingException, IOException{
		List<Map<String, Object>> IDCList = null;	
		String uri = "http://api.duomo.intra.sina.com.cn/index.php/rest/idc/list?ticket=26419cae7f73edcddabb7a218f080e7b0f32bbb1";	
		URL url = new URL(uri); 		
		ObjectMapper mapper = new ObjectMapper(); 
		ObjectNode root = (ObjectNode) mapper.readTree(url);
		String data = root.get("datas").toString();
		IDCList = mapper.readValue(data, List.class);
		logger.debug(url+"IDCList_size:"+"==="+IDCList.size());
		return IDCList;
	}

	@Override
	public Map<String,List<Double>> getIDCBandwidth(Map<String, Object> IDCInfo,Calendar from, Calendar to, String step) throws Exception {
		Map<String, List<Double>> result = new HashMap<String, List<Double>>();
		String idc_export_name_origon = (String) IDCInfo.get("name");
		String idc_export_name = URLEncoder.encode(idc_export_name_origon, "utf-8");
		String uri = "http://api.duomo.intra.sina.com.cn/index.php/api/api/getIdcTsdData?"
	               + "idc="+(String) IDCInfo.get("wh")
				   + "&ld="+step
	               + "&merge=true"
				   + "&stime="+from.getTimeInMillis()/1000
	               + "&etime="+to.getTimeInMillis()/1000
				   + "&dev="+(String) IDCInfo.get("dev")
	               + "&idc_export_name="+idc_export_name;		
		URL url = new URL(uri);  
		ObjectMapper mapper = new ObjectMapper(); 
		ObjectNode root = (ObjectNode) mapper.readTree(url);
		String data = root.get("data").toString();
		List<Map<String, Object>> tempList = mapper.readValue(data, List.class);
		String inMaxString  = tempList.get(0).get("timevalue").toString().replaceAll("Nan", "null");
		String outMaxString  = tempList.get(1).get("timevalue").toString().replaceAll("Nan", "null");
		List<Double> inMax =  mapper.readValue(inMaxString , List.class);
		List<Double> outMax =  mapper.readValue(outMaxString, List.class);
		result.put("inMax", inMax);
		result.put("outMax", outMax);
		logger.debug(url);
		logger.debug("from:"+from.getTime()+" to:"+to.getTime());
		logger.debug("outMax:"+result.get("outMax").size()+"==inMax:"+result.get("inMax").size());
		return result;
	}

}
