package kr.cloudev.models.action;

import org.kohsuke.github.GHContent;


public class FileListModel {

    private String name;
    private String type;
    private long size;

    public FileListModel(GHContent content) {
        setName(content.getName());
        setType(content.getType());
        setSize(content.getSize());
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public long getSize() {
        return size;
    }

    public void setSize(long size) {
        this.size = size;
    }
}
