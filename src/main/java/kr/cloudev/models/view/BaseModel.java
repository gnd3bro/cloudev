package kr.cloudev.models.view;

public class BaseModel extends SiteModel {

    private String urlMapHome;
    private String urlMapUser;
    private String urlMapEditor;
    private String urlMapRepositories;
    private String urlMapPolicy;
    private String urlMapDoLogout;
    private String urlMapDoRepoList;
    private String siteName;
    private String avatarUrl;
    private String username;
    private String loginId;

    public void setModelFields(BaseModel model) {
        setModelFields((SiteModel) model);
        setUrlMapHome(model.getUrlMapHome());
        setUrlMapUser(model.getUrlMapUser());
        setUrlMapEditor(model.getUrlMapEditor());
        setUrlMapRepositories(model.getUrlMapRepositories());
        setUrlMapPolicy(model.getUrlMapPolicy());
        setUrlMapDoLogout(model.getUrlMapDoLogout());
        setUrlMapDoRepoList(model.getUrlMapDoRepoList());
        setSiteName(model.getSiteName());
        setAvatarUrl(model.getAvatarUrl());
        setUsername(model.getUsername());
        setLoginId(model.getLoginId());
    }

    public String getUrlMapHome() {
        return urlMapHome;
    }

    public void setUrlMapHome(String urlMapHome) {
        this.urlMapHome = urlMapHome;
    }

    public String getUrlMapUser() {
        return urlMapUser;
    }

    public void setUrlMapUser(String urlMapUser) {
        this.urlMapUser = urlMapUser;
    }

    public String getUrlMapEditor() {
        return urlMapEditor;
    }

    public void setUrlMapEditor(String urlMapEditor) {
        this.urlMapEditor = urlMapEditor;
    }

    public String getUrlMapRepositories() {
        return urlMapRepositories;
    }

    public void setUrlMapRepositories(String urlMapRepositories) {
        this.urlMapRepositories = urlMapRepositories;
    }

    public String getUrlMapPolicy() {
        return urlMapPolicy;
    }

    public void setUrlMapPolicy(String urlMapPolicy) {
        this.urlMapPolicy = urlMapPolicy;
    }

    public String getUrlMapDoLogout() {
        return urlMapDoLogout;
    }

    public void setUrlMapDoLogout(String urlMapDoLogout) {
        this.urlMapDoLogout = urlMapDoLogout;
    }

    public String getUrlMapDoRepoList() {
        return urlMapDoRepoList;
    }

    public void setUrlMapDoRepoList(String urlMapDoRepoList) {
        this.urlMapDoRepoList = urlMapDoRepoList;
    }

    public String getSiteName() {
        return siteName;
    }

    public void setSiteName(String siteName) {
        this.siteName = siteName;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getLoginId() {
        return loginId;
    }

    public void setLoginId(String loginId) {
        this.loginId = loginId;
    }
}
