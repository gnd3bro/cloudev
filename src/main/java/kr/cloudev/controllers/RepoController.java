package kr.cloudev.controllers;

import com.google.gson.GsonBuilder;
import kr.cloudev.models.RepositoryModel;
import kr.cloudev.models.view.BaseModel;
import kr.cloudev.models.view.page.RepoModel;
import org.kohsuke.github.GHMyself;
import org.kohsuke.github.GHRepository;
import org.kohsuke.github.GitHub;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.support.RequestContextUtils;
import org.springframework.web.util.UrlPathHelper;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/repo")
public class RepoController {

    @RequestMapping()
    public ModelAndView repoList(HttpServletRequest request, HttpServletResponse response) throws IOException {
        if (RequestContextUtils.getInputFlashMap(request) == null) {
            response.sendRedirect("/base.do?referer=" + UrlPathHelper.getResolvedLookupPath(request));
            return null;
        }

        HttpSession session = request.getSession();

        BaseModel model = (BaseModel) session.getAttribute("baseModel");

        return new ModelAndView("base", "model", model);
    }

    @RequestMapping("/{repoName}")
    public ModelAndView repo(HttpServletRequest request, HttpServletResponse response, @PathVariable String repoName) throws IOException {
        if (RequestContextUtils.getInputFlashMap(request) == null) {
            response.sendRedirect("/base.do?referer=" + UrlPathHelper.getResolvedLookupPath(request));
            return null;
        }

        HttpSession session = request.getSession();

        GitHub github = (GitHub) session.getAttribute("github");
        BaseModel baseModel = (BaseModel) session.getAttribute("baseModel");

        GHMyself myself = github.getMyself();

        RepoModel model = new RepoModel();

        model.setModelFields(baseModel);
        model.setTitle(myself.getName().concat(" - Cloudev"));

        return new ModelAndView("base", "model", model);
    }

    @ResponseBody
    @RequestMapping("/repo_list.do")
    public String doRepoList(HttpServletRequest request) throws IOException {
        HttpSession session = request.getSession();

        GitHub github = (GitHub) session.getAttribute("github");

        if (github == null) {
            return null;
        }

        GHMyself myself = github.getMyself();

        List<RepositoryModel> repoList = new ArrayList<>();

        for (GHRepository repo : myself.getRepositories().values()) {
            repoList.add(new RepositoryModel(repo));
        }

        return new GsonBuilder().create().toJson(repoList);
    }
}
