package eventobject;
import java.util.EventListener;

public interface ExistingUserListener extends EventListener {
	public void userSelectionOccured(int userId);
}