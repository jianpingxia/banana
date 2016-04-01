package com.sina.banana;

import java.sql.Timestamp;
import java.util.Calendar;

import org.apache.log4j.Logger;
import org.elasticsearch.client.Client;

import com.sina.banana.dao.impl.duomoapi.IDCBandwidthImplByDuomoAPI;
import com.sina.banana.service.FeedIDCBandWidth2ES;

public class Main {
	
	private static Logger logger = Logger.getLogger(Main.class); 

	public static void main(String[] args) {
		if(args.length>0){
			if(args[0].equals("normal")){
				Calendar from = Calendar.getInstance();
				from.set(Calendar.MINUTE, 5);
				logger.info(from.get(Calendar.MINUTE));
				logger.info(60-from.get(Calendar.MINUTE));
				/*Client esClient = null;
				FeedIDCBandWidth2ES feedIDC2ES = new FeedIDCBandWidth2ES(esClient);
				
				//from.setTime(Timestamp.valueOf("2016-03-01 00:00:00.000"));
				from.add(Calendar.MINUTE, -15);
				from.set(Calendar.SECOND, 0);
				from.set(Calendar.MILLISECOND, 0);
				System.out.println(from.getTime());
				System.out.println(from.getTimeInMillis());				
				Calendar to = from;			
				feedIDC2ES.addLog(from, to, "300");*/
			}
		}
	}

}
