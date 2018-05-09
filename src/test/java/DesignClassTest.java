import com.artibarti.backgammon.model.Board;
import com.artibarti.backgammon.utils.DesignManager;
import com.artibarti.backgammon.utils.GameUtil;
import javafx.scene.Node;
import javafx.scene.layout.AnchorPane;
import org.junit.Test;
import java.util.ArrayList;
import java.util.List;
import static junit.framework.TestCase.assertEquals;

public class DesignClassTest {


    @Test
    public void TestSetStyle()
    {
        DesignManager designManager = new DesignManager();
        AnchorPane anchorPane = new AnchorPane();
        AnchorPane anchorPane2 = new AnchorPane();

        String res = "selection_for_kicked_and_borne_fields";

        designManager.setStyle("style1",anchorPane,"selection_for_kicked_and_borne_fields");
        assertEquals(anchorPane.getStyleClass().toString(), "selection_for_kicked_and_borne_fields");
        designManager.setStyle("style1",anchorPane2,"selection_for_kicked_and_borne_fields");
        assertEquals(anchorPane.getStyleClass().toString(), "");
        designManager.dropStyle("style1");
        assertEquals(anchorPane2.getStyleClass().toString(), "");
        designManager.dropStyle("style2");
    }
}