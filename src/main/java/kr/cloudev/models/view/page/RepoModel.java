package kr.cloudev.models.view.page;

import kr.cloudev.models.view.BaseModel;

public class RepoModel extends BaseModel {

    private String repoName;
    private String path;

    public String getRepoName() {
        return repoName;
    }

    public void setRepoName(String repoName) {
        this.repoName = repoName;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
}
