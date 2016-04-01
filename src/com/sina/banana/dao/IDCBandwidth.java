package com.sina.banana.dao;

import java.io.UnsupportedEncodingException;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

public interface IDCBandwidth {
	List<Map<String, Object>> getIDCList(Calendar when) throws Exception;
	List<Map<String, Object>> getIDCList() throws Exception;
	Map<String, List<Double>> getIDCBandwidth(Map<String, Object> IDCInfo, Calendar from, Calendar to, String step) throws Exception;
}
