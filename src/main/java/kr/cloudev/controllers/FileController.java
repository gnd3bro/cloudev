package kr.cloudev.controllers;

import com.google.gson.GsonBuilder;
import kr.cloudev.models.action.RepoListModel;
import kr.cloudev.models.action.RepositoryModel;
import kr.cloudev.models.view.BaseModel;
import kr.cloudev.models.view.page.RepoModel;
import org.kohsuke.github.GHMyself;
import org.kohsuke.github.GHRepository;
import org.kohsuke.github.GitHub;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.support.RequestContextUtils;
import org.springframework.web.util.UrlPathHelper;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/file")
public class FileController {

    @RequestMapping()
    public ModelAndView file(HttpServletRequest request, HttpServletResponse response) throws IOException {
        if (RequestContextUtils.getInputFlashMap(request) == null) {
            response.sendRedirect("/base.do?referer=" + UrlPathHelper.getResolvedLookupPath(request));
            return null;
        }

        HttpSession session = request.getSession();

        BaseModel model = (BaseModel) session.getAttribute("baseModel");

        model.setTitle("Repositories".concat(" - ").concat(model.getSiteName()));
        model.setUrlMapDoRepoList("/repo/repo_list.do");

        return new ModelAndView("base", "model", model);
    }

    @RequestMapping("/{repoName}")
    public ModelAndView repo(HttpServletRequest request, HttpServletResponse response, @PathVariable String repoName) throws IOException {
        if (RequestContextUtils.getInputFlashMap(request) == null) {
            response.sendRedirect("/base.do?referer=" + UrlPathHelper.getResolvedLookupPath(request));
            return null;
        }

        HttpSession session = request.getSession();

        GHMyself user = (GHMyself) session.getAttribute("user");

        if (user.getRepository(repoName) == null) {
            response.sendRedirect("/404");
            return null;
        }

        BaseModel baseModel = (BaseModel) session.getAttribute("baseModel");
        RepoModel model = new RepoModel();

        model.setModelFields(baseModel);
        model.setTitle(user.getName().concat(" - ").concat(model.getSiteName()));
        model.setRepoName(repoName);

        return new ModelAndView("base", "model", model);
    }

    @ResponseBody
    @RequestMapping("/file_list.do")
    public String doRepoList(HttpServletRequest request, @PathVariable String type) throws IOException {
        HttpSession session = request.getSession();

        GitHub github = (GitHub) session.getAttribute("github");

        if (github == null) {
            return null;
        }

        GHMyself user = (GHMyself) session.getAttribute("user");
        List<String> repoFullnameList = new ArrayList<>();
        Iterator<GHRepository> repoList;
        String loginId;

        if (type.equals("repo")) {
            loginId = user.getLogin();
            repoList = user.getRepositories().values().iterator();
        } else if (type.equals("starred")) {
            loginId = null;
            repoList = user.listStarredRepositories().iterator();
        } else {
            return null;
        }

        while (repoList.hasNext()) {
            repoFullnameList.add(repoList.next().getFullName());
        }

        return new GsonBuilder().create().toJson(new RepoListModel(loginId, repoFullnameList));
    }


    @ResponseBody
    @PostMapping("/file.do")
    public String doRepo(HttpServletRequest request,
                         @RequestParam Map<String, Object> param) throws IOException {
        HttpSession session = request.getSession();

        GitHub github = (GitHub) session.getAttribute("github");

        if (github == null || param == null) {
            return null;
        }

        String[] fullname = ((String) param.get("fullname")).split("/");
        String loginId = (String) param.get("loginId");
        String owner = fullname[0];
        String repo = fullname[1];

        GHMyself user = (GHMyself) session.getAttribute("user");

        GHRepository rawRepo = user.getRepository(repo);
        RepositoryModel repoList = null;

        if (owner.equals(user.getLogin())) {
            repoList = new RepositoryModel(rawRepo);
        } else if (loginId == null) {
            repoList = new RepositoryModel(github.getUser(owner).getRepository(repo));
        }

        return new GsonBuilder().create().toJson(repoList);
    }
}
