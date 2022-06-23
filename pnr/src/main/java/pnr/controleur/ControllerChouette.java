package pnr.controleur;

import io.github.palexdev.materialfx.controls.MFXCheckListView;
import io.github.palexdev.materialfx.controls.MFXDatePicker;
import io.github.palexdev.materialfx.controls.MFXTextField;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;

/**
 * ControllerChouette class controller
 */
public class ControllerChouette {

    @FXML
    private ComboBox<?> cbEspece;

    @FXML
    private ComboBox<?> cbProto;

    @FXML
    private ComboBox<?> cbSexe;

    @FXML
    private ComboBox<?> cbTypeObs;

    @FXML
    private MFXDatePicker datePicker;

    @FXML
    private MFXCheckListView<?> observatorList;

    @FXML
    private MFXTextField txtCoordX;

    @FXML
    private MFXTextField txtCoordY;

    @FXML
    private MFXTextField txtHeure;

    /**
     * This function is called when the user clicks the "Modify" button
     */
    public void initModify() {
        System.out.println("hello world");
    }
}