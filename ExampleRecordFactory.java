package Gui.models;

import java.util.ArrayList;

public class ExampleRecordFactory {
    public static RecordList getExampleRecords() {
        var list = new RecordList();
        list.add(new Record("benutzer1", "geheimes", "reddit.com"));
        list.add(new Record("hanspeter33", "geheimes2", "youjizz.com"));
        list.add(new Record("shehn", "geheimes3", "pornhub.com"));
        return list;
    }
}
