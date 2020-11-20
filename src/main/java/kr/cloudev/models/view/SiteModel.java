package kr.cloudev.models.view;

public abstract class SiteModel {

    private String title;

    public void setModelFields(SiteModel model) {
        setTitle(model.title);
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
