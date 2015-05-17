package Observer;

import java.util.HashMap;

public interface Observer {
	public void update(int lastIndex,HashMap<String, Boolean> map);
}
