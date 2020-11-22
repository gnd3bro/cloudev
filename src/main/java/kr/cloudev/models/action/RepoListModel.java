package kr.cloudev.models.action;

import java.util.List;

public class RepoListModel {

    private String loginId;
    private List<String> repoFullnameList;

    public RepoListModel(String loginId, List<String> repoFullnameList) {
        setLoginId(loginId);
        setRepoFullnameList(repoFullnameList);
    }

    public String getLoginId() {
        return loginId;
    }

    public void setLoginId(String loginId) {
        this.loginId = loginId;
    }

    public List<String> getRepoFullnameList() {
        return repoFullnameList;
    }

    public void setRepoFullnameList(List<String> repoFullnameList) {
        this.repoFullnameList = repoFullnameList;
    }
}
