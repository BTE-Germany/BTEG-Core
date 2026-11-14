package de.btegermany.core.paper.gui;

import dev.triumphteam.gui.guis.PaginatedGui;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;

public class GuiTitle {
    private static final String CHAR_REPO_NEG8 = "\uF808";
    private static final String CHAR_REPO_NEG170 = "\uF802\uF808\uF80A\uF80C";

    private final Component title;
    private final String customData;

    public GuiTitle(Component title) {
        this.title = title;
        this.customData = null;
    }

    public GuiTitle(String title) {
        this(Component.text(title));
    }

    public GuiTitle(Component title, String customData) {
        this.title = title;
        this.customData = customData;
    }

    public GuiTitle(String title, String customData) {
        this(Component.text(title), customData);
    }

    public Component getComponent() {
        return Component.text(CHAR_REPO_NEG8)
                .append(Component.text(this.customData, NamedTextColor.WHITE))
                .append(Component.text(CHAR_REPO_NEG170))
                .append(this.title);
    }

    public Component getComponentWithCurrentPage(PaginatedGui gui) {
        int currentPage = gui.getCurrentPageNum();
        Component pageComponent = Component.text(" - Seite %d".formatted(currentPage));

        return this.getComponent()
                .append(pageComponent);
    }

}
