package util;

import java.util.Timer;
import java.util.TimerTask;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public abstract class Scheduler {
	// Log
	private static final Log logger = LogFactory.getLog(Scheduler.class);

	private long interval;
	private Timer timer;
	
	public Scheduler(long interval, TimerTask task) {
		super();
		this.interval = interval;
		timer = new Timer();
		timer.scheduleAtFixedRate(task, 0, interval);
		logger.info(task.getClass().getName() + " en ejecución (" + interval + " Milisegundos).");
	}

	protected void setInterval(long interval) {
		this.interval = interval;
	}

	protected long getInterval() {
		return interval;
	}
	
	public Timer getTimer()
	{
		return timer;
	}
}
