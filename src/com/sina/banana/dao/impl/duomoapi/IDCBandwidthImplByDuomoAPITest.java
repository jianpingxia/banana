package com.sina.banana.dao.impl.duomoapi;

import static org.junit.Assert.assertTrue;
import java.io.IOException;
import java.util.Calendar;
import java.util.List;
import java.util.Map;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class IDCBandwidthImplByDuomoAPITest {
	IDCBandwidthImplByDuomoAPI  duomoapi;

	@Before
	public void setUp() throws Exception {
		duomoapi = new IDCBandwidthImplByDuomoAPI();
	}

	@After
	public void tearDown() throws Exception {
		duomoapi = null;
	}

	@Test
	public final void testGetIDCList() {
		int resultSize = 0;
		try {
			resultSize = duomoapi.getIDCList().size();
		} catch (IOException e) {
			e.printStackTrace();
		}
		assertTrue(resultSize>0);
	}

	@Test
	public final void testGetIDCListCalendar() {
		Calendar when = Calendar.getInstance();	
		when.add(Calendar.DATE, -1);
		List<Map<String, Object>> result = null;
		try {
			result = duomoapi.getIDCList(when);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		assertTrue(result.size()>0);
	}

	@Test
	public final void testGetIDCBandwidth() {
		
		List<Map<String, Object>> IDCList = null;
		try {
			IDCList = duomoapi.getIDCList();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		
		for(Map<String, Object> IDCInfo:IDCList){			
			Calendar from = Calendar.getInstance();
			from.add(Calendar.DATE, -100);	
			Calendar to = Calendar.getInstance();
			//to.add(Calendar.DATE, -1);
			String step = "300";
			Map<String, List<Double>> result = null;
			try {
				result = duomoapi.getIDCBandwidth(IDCInfo, from, to, step );
			} catch (Exception e) {
				e.printStackTrace();
			}
			assertTrue(IDCInfo.get("name").toString(),result!=null);
		}
	}

}
