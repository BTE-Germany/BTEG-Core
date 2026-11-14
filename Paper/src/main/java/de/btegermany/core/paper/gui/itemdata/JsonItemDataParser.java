package de.btegermany.core.paper.gui.itemdata;

import org.json.JSONObject;

public interface JsonItemDataParser <I extends ItemData> extends ItemDataParser<JSONObject, I> {

    @Override
    I parseData(JSONObject data);

}
