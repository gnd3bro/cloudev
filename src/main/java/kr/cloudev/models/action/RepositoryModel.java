package kr.cloudev.models.action;

import com.google.gson.annotations.SerializedName;
import org.kohsuke.github.GHRepository;

import java.io.IOException;
import java.util.List;

public class RepositoryModel {

    @SerializedName("name")
    public String name;

    @SerializedName("htmlUrl")
    public String htmlUrl;

    @SerializedName("description")
    public String description;

    @SerializedName("topicList")
    public List<String> topicList;

    @SerializedName("language")
    public String language;

    @SerializedName("license")
    public String license;

    @SerializedName("forksCount")
    public int forksCount;

    @SerializedName("stargazersCount")
    public int stargazersCount;

    public RepositoryModel(GHRepository repo) throws IOException {
        name = repo.getName();
        htmlUrl = repo.getHtmlUrl().toString();
        topicList = repo.listTopics();
        forksCount = repo.getForksCount();
        stargazersCount = repo.getStargazersCount();

        if (repo.getDescription() == null) {
            description = "";
        } else {
            description = repo.getDescription();
        }

        if (repo.getLanguage() == null) {
            language = "Unknown";
        } else {
            language = repo.getLanguage();
        }

        if (repo.getLicense() == null) {
            license = "None";
        } else {
            license = repo.getLicense().getName();
        }
    }

}
