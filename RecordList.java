package Gui.models;

import javax.swing.*;
import java.util.ArrayList;

public class RecordList extends ArrayList<Record> {

    public DefaultListModel<String> getWebsiteList() {
        var sublist = new DefaultListModel<String>();
        for (Record r: this) {
            sublist.add(sublist.getSize(), r.getWebsite());
        }
        return sublist;
    }
}
