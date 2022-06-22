package pnr.controleur;

import io.github.palexdev.materialfx.controls.MFXListView;
import io.github.palexdev.materialfx.controls.cell.MFXListCell;
import io.github.palexdev.materialfx.font.MFXFontIcon;

public class PersonCellFactory extends MFXListCell<String> {
    private final MFXFontIcon userIcon;

    public PersonCellFactory(MFXListView<String> listView, String data, String icon) {
        super(listView, data);

        userIcon = new MFXFontIcon(icon, 18);
        // delete-icon = new MFXFontIcon("mfx-delete", 18);
        userIcon.getStyleClass().add("user-icon");
        render(data);
    }

    @Override
    protected void render(String data) {
        super.render(data);
        if (userIcon != null) getChildren().add(0, userIcon);
    }
}