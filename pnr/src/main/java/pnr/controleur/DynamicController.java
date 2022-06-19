package pnr.controleur;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Labeled;
public class DynamicController extends Controller {


    @FXML
    private Button btnModifierProfil;

    @FXML
    void handleBtnClick(ActionEvent event) {
        initVisualiser(((Labeled) event.getSource()).getText());
        System.out.println(((Labeled) event.getSource()).getText());
        loadStage("../vue/ModifierUnProfil.fxml", event);
        // if (event.getSource() == btnModifierProfil) {
        //     loadStage("../vue/ModifierUnProfil.fxml", event);
        // }
    }

}


