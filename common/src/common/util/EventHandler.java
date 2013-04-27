package common.util;

public abstract class EventHandler {

	protected Object _target;

	protected EventHandler(Object target) {
		_target = target;
	}

	public abstract void execute(String param);
	public abstract void execute(String[] param);

	protected Object getTarget() {
		return _target;
	}
}
