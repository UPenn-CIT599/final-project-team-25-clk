package eventobject;
import java.util.EventListener;

public interface NewUserListener extends EventListener {
	public void newUserCreatedOccured();
}