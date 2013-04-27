package common.presentation.util.grid.handler;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import common.presentation.util.grid.Grid;
import common.util.EventHandler;

public class Handler implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5049870230703028007L;

	public static final String EVENT_HANDLER_KEY = "apply-filter-evt";
	public static final String EVENT_SELECT_NO_REFRESH = "apply-select";

	public static final String EVENT_SELECT_ONE = "selectOne";
	public static final String EVENT_SELECT_ALL = "selectAll";
	public static final String EVENT_SELECT_NONE = "selectNone";
	
	private Map<String, EventHandler> events = new HashMap<String, EventHandler>();
	private Grid grid;
	public Grid getGrig() { return grid; };

	public Handler(Grid target) {
		this.grid = target;
		
		// Creación de los eventos del grid
		EventHandler selectRow = new EventHandler(this) {
			@Override
			public void execute(String param) {
				Handler target = (Handler) getTarget();
				System.out.println( target.getGrig().getName() + " " +  param);
			}

			@Override
			public void execute(String[] param) {
				// TODO Auto-generated method stub
				
			}
		};

		EventHandler selectAll = new EventHandler(this) {
			@Override
			public void execute(String param) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void execute(String[] param) {
				// TODO Auto-generated method stub
				
			}
		};		
		
		events.put("EVENT_SELECT_ONE", selectRow);
		events.put("EVENT_SELECT_ALL", selectAll);
	}

	public void processEvent(String eventDescriptor, String eventValues, HttpServletRequest request) {
			EventHandler eventHandler = (EventHandler) events.get(eventDescriptor);
			if (eventHandler != null) {
				eventHandler.execute(eventValues);
			}
	}

	public void processEvent(String eventDescriptor, String[] eventValues, HttpServletRequest request) {
			EventHandler eventHandler = (EventHandler) events.get(eventDescriptor);
			if (eventHandler != null) {
				eventHandler.execute(eventValues);
			}
	}
	
}
