package de.btegermany.core.paper.gui;

import dev.triumphteam.gui.guis.BaseGui;
import lombok.experimental.UtilityClass;

@UtilityClass
public class GuiUtils {

    public final int NAVBAR_ROWS = 1;

    public void fill(BaseGui gui) {
        gui.getFiller().fillBetweenPoints(1 + NAVBAR_ROWS, 1, gui.getRows(), 9, GuiItems.fillerItem());
    }

}
