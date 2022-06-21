package pnr;

import static org.junit.Assert.*;

import org.junit.Test;

import javafx.scene.control.ComboBox;
import io.github.palexdev.materialfx.controls.MFXTextField;
import pnr.controleur.*;
import pnr.modele.*;

/**
 * Unit test
 */
public class MethodesTest {
    

    @Test
    public void testGetPerm() throws RuntimeException {
        
        ControllerNouveauProfil cnv = new ControllerNouveauProfil();
        ComboBox<String> perm = new ComboBox<String>();
        // assertEquals(-1, cnv.getPerm(perm));
    }
}