package de.btegermany.core.paper.gui.itemdata;

/***
 * Produces an ItemData object out of provided data
 * @param <T> The type the data is provided as
 * @param <I> The ItemData type of the result object
 */
public interface ItemDataParser <T, I extends ItemData> {

    I parseData(T data);

}
