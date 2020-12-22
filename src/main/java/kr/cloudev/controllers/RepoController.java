package kr.cloudev.controllers;

import com.google.gson.GsonBuilder;
import kr.cloudev.models.action.RepoListModel;
import kr.cloudev.models.action.RepositoryModel;
import kr.cloudev.models.view.BaseModel;
import kr.cloudev.models.view.page.RepoModel;
import org.kohsuke.github.GHContent;
import org.kohsuke.github.GHMyself;
import org.kohsuke.github.GHRepository;
import org.kohsuke.github.GitHub;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.HandlerMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.support.RequestContextUtils;
import org.springframework.web.util.UrlPathHelper;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.*;

@SuppressWarnings("DuplicatedCode")
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

        BaseModel baseModel = (BaseModel) session.getAttribute("baseModel");
        RepoModel model = new RepoModel();

        model.setModelFields(baseModel);
        model.setTitle("Repositories".concat(" - ").concat(model.getSiteName()));

        return new ModelAndView("base", "model", model);
    }

    @RequestMapping(value = { "/{repoName}", "/{repoName}/**" })
    public ModelAndView repo(HttpServletRequest request, HttpServletResponse response,
                             @PathVariable String repoName, String type) throws IOException {
        if (RequestContextUtils.getInputFlashMap(request) == null) {
            String param = type != null ? "&type=" + type : "";
            response.sendRedirect("/base.do?referer=" + UrlPathHelper.getResolvedLookupPath(request) + param);
            return null;
        }

        HttpSession session = request.getSession();

        GHMyself user = (GHMyself) session.getAttribute("user");

        if (user.getRepository(repoName) == null) {
            response.sendRedirect("/404");
            return null;
        }

        String path = UrlPathHelper.getResolvedLookupPath(request).replace("/repo/".concat(repoName), "");

        if (type != null) {
            if (type.equals("file")) {
                path = UrlPathHelper.getResolvedLookupPath(request).replace("/repo/".concat(repoName).concat("/"), "");
            }
        }

        BaseModel baseModel = (BaseModel) session.getAttribute("baseModel");
        RepoModel model = new RepoModel();

        model.setModelFields(baseModel);
        model.setTitle(user.getName().concat(" - ").concat(model.getSiteName()));
        model.setRepoName(repoName);
        model.setPath(path);

        return new ModelAndView("base", "model", model);
    }
    
    // TODO: 인덱스 포함해서 순서 일관적으로 유지 할 수 있도록 구현 / JS 에서 미리 컨테이너 생성 후 할당 하는 방법으로
    @ResponseBody
    @RequestMapping("/{type}_list.do")
    public String doRepoList(HttpServletRequest request,
                             @PathVariable String type) throws IOException {
        HttpSession session = request.getSession();

        GitHub github = (GitHub) session.getAttribute("github");

        if (github == null) {
            return null;
        }

        GHMyself user = (GHMyself) session.getAttribute("user");
        List<String> repoFullnameList = new ArrayList<>();
        List<GHRepository> repoList;
        String loginId;

        if (type.equals("repo")) {
            loginId = user.getLogin();
            repoList = new ArrayList<>(user.getRepositories().values());
        } else if (type.equals("starred")) {
            loginId = null;
            repoList = user.listStarredRepositories().toList();
        } else {
            return null;
        }

        for (GHRepository repo : repoList) {
            repoFullnameList.add(repo.getFullName());
        }

        return new GsonBuilder().create().toJson(new RepoListModel(loginId, repoFullnameList));
    }


    @ResponseBody
    @PostMapping("/repo.do")
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
