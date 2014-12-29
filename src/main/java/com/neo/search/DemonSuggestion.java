package com.neo.search;

import java.util.logging.Logger;

public class DemonSuggestion implements Runnable{
	private Logger logger=Logger.getLogger(getClass().getName());
	private IndexeChecker mChecker=IndexeChecker.getInstance();
	private long sleepTime=1000*60;
	
	public DemonSuggestion() {
	}
	
	public DemonSuggestion(long sleepTime) {
		super();
		this.setSleepTime(sleepTime);
	}

	@Override
	public void run() {
		while(true){
			try {
				mChecker.lancerIndexation();
				logger.info("DemonSuggestion en fond");
				Thread.sleep(100000);
			} catch (InterruptedException e) {
				logger.warning("Arrèt du minuteur de DemoSuggestion");
			}
		}
		
	}

	public long getSleepTime() {
		return sleepTime;
	}

	public void setSleepTime(long sleepTime) {
		this.sleepTime = sleepTime;
	}

}
