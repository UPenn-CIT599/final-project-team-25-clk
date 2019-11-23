package eventobject;

import java.util.EventListener;

public interface NewUserFormListener extends EventListener {
	public void formEventOccurred(NewUserForm e);
}
