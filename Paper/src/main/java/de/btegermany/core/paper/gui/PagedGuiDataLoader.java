package de.btegermany.core.paper.gui;

import de.btegermany.core.paper.gui.base.PagedCustomGui;
import de.btegermany.core.paper.gui.itemdata.ItemData;

public interface PagedGuiDataLoader <I extends ItemData> {

    void loadData(PagedCustomGui<I> gui, boolean loadInitialData, String[] args, int... pages);

    default void loadMoreData(PagedCustomGui<I> gui, String[] args, int... pages) {
        this.loadData(gui, false, args, pages);
    }

}
