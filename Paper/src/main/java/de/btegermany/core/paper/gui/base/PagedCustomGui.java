package de.btegermany.core.paper.gui.base;

import de.btegermany.core.common.gui.PagedGuiType;
import de.btegermany.core.paper.gui.*;
import de.btegermany.core.paper.gui.itemdata.ItemData;
import de.btegermany.core.paper.gui.itemdata.JsonItemDataParser;
import de.btegermany.core.paper.item.head.CustomHead;
import de.btegermany.core.paper.item.ItemGenerator;
import de.btegermany.core.paper.gui.PagedGuiDataLoader;
import dev.triumphteam.gui.guis.Gui;
import dev.triumphteam.gui.guis.GuiItem;
import dev.triumphteam.gui.guis.PaginatedGui;
import lombok.Getter;
import net.kyori.adventure.text.format.NamedTextColor;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.List;

public abstract class PagedCustomGui<I extends ItemData> extends CustomGui<PaginatedGui> {

    private static final int PAGES_TO_LOAD_DATA_BEFORE_LAST = 1;

    private final GuiTitle title;
    private final PagedGuiStructure structure;
    @Getter
    protected final PagedGuiType type;
    private final CustomHead defaultHead;
    protected final PagedGuiDataLoader<I> dataLoader;
    protected final JsonItemDataParser<I> itemDataParser;
    protected final GuiItems guiItems;
    private final String[] requestArgs;

    public PagedCustomGui(GuiTitle title, PagedGuiStructure structure, PagedGuiType type, CustomHead defaultHead, Player player, PagedGuiDataLoader<I> dataLoader, JsonItemDataParser<I> itemDataParser, JavaPlugin plugin, ItemGenerator itemGenerator, String... requestArgs) {
        super(plugin, player, Gui.paginated()
                .title(title.getComponent())
                .rows(structure.rows())
                .disableAllInteractions()
                .create()
        );

        this.title = title;
        this.structure = structure;
        this.type = type;
        this.defaultHead = defaultHead;
        this.dataLoader = dataLoader;
        this.itemDataParser = itemDataParser;
        this.guiItems = new GuiItems(itemGenerator);
        this.requestArgs = requestArgs;

        this.gui.setItem(List.of(0, 1, 2, 3, 4, 5, 6, 7), this.guiItems.blankItem());
        this.gui.setItem(8, this.guiItems.closeItem(this));
    }

    private GuiItem previousPageItem() {
        return this.guiItems.emptyItem("Vorherige Seite", NamedTextColor.GOLD, this::previousPage);
    }

    private GuiItem nextPageItem() {
        return this.guiItems.emptyItem("Nächste Seite", NamedTextColor.GOLD, () -> {
            int pagesNum = this.gui.getPagesNum();
            if (this.gui.getCurrentPageNum() == pagesNum - PAGES_TO_LOAD_DATA_BEFORE_LAST) {
                this.dataLoader.loadMoreData(this, this.requestArgs, pagesNum + 1);
                return;
            }

            this.nextPage();
        });
    }

    public void addPages(JSONArray pagesData, boolean initialData) {
        this.addPages(pagesData);

        if (initialData) {
            this.updateWindow();
            return;
        }
        this.nextPage();
    }

    private void addPages(JSONArray pagesData) {
        for (int i = 0; i < pagesData.length(); i++) {
            JSONObject pageData = pagesData.getJSONObject(i);
            int page = pageData.getInt("page");
            JSONArray pageContent = pageData.getJSONArray("content");
            if (page > 1 && page <= this.gui.getPagesNum()) {
                continue;
            }

            for (int j = 0; j < ((this.gui.getRows() - GuiUtils.NAVBAR_ROWS) * 9); j++) {
                if (pageContent.length() > j) {
                    I itemData = this.itemDataParser.parseData(pageContent.getJSONObject(j));
                    this.addItem(new GuiItem(itemData.getItemStack(this.defaultHead), _ -> this.onClick(itemData)));
                    continue;
                }
                this.addItem(GuiItems.fillerItem());
            }

            this.gui.update();
        }
    }

    private void addItem(GuiItem item) {
        Bukkit.getScheduler().runTask(this.plugin, () -> {
            this.gui.addItem(item);
        });
    }

    public void previousPage() {
        Bukkit.getScheduler().runTask(this.plugin, () -> {
            this.gui.previous();
            this.updateWindow();
        });
    }

    public void nextPage() {
        Bukkit.getScheduler().runTask(this.plugin, () -> {
            this.gui.next();
            this.updateWindow();
        });
    }

    public void updateNavbar() {
        Bukkit.getScheduler().runTask(this.plugin, () -> {
            int currentPageNum = this.gui.getCurrentPageNum();
            int pagesNum = this.gui.getPagesNum();

            if (pagesNum == 1) {
                return;
            }

            if (currentPageNum == 1) {
                this.gui.updateItem(this.structure.slotPreviousItem(), this.guiItems.blankItem());
                this.gui.updateItem(this.structure.slotNextItem(), this.nextPageItem());
            } else if (currentPageNum == pagesNum) {
                this.gui.updateItem(this.structure.slotPreviousItem(), this.previousPageItem());
                this.gui.updateItem(this.structure.slotNextItem(), this.guiItems.blankItem());
            }

            if (pagesNum == 2) {
                return;
            }

            if (currentPageNum > 1 && currentPageNum < pagesNum) {
                this.gui.updateItem(this.structure.slotPreviousItem(), this.previousPageItem());
                this.gui.updateItem(this.structure.slotNextItem(), this.nextPageItem());
            }
        });
    }

    public void updateTitle() {
        Bukkit.getScheduler().runTask(this.plugin, () -> {
            if (this.gui.getPagesNum() == 1) {
                return;
            }
            this.gui.updateTitle(this.title.getComponentWithCurrentPage(this.gui));
        });
    }

    private void updateWindow() {
        this.updateNavbar();
        this.updateTitle();
    }

    @Override
    public void open() {
        super.open();
        this.dataLoader.loadData(this, true, this.requestArgs, 1, 2);
    }

    protected abstract void onClick(I itemData);



    public record PagedGuiStructure(int rows, int slotPreviousItem, int slotNextItem) {}

}
