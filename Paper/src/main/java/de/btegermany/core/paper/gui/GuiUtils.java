package de.btegermany.core.paper.gui;

import dev.triumphteam.gui.guis.BaseGui;

public class GuiUtils {

    public static final int NAVBAR_ROWS = 1;

    public void fill(BaseGui gui) {
        gui.getFiller().fillBetweenPoints(1 + NAVBAR_ROWS, 1, gui.getRows(), 9, GuiItems.fillerItem());
    }

}
