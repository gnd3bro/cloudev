package kr.cloudev.controllers;

import com.google.gson.GsonBuilder;
import kr.cloudev.models.view.BaseModel;
import kr.cloudev.models.RepositoryModel;
import kr.cloudev.models.view.page.UserModel;
import org.kohsuke.github.*;
import org.springframework.stereotype.Controller;
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
@RequestMapping("/user")
public class UserController {

    @RequestMapping()
    public ModelAndView user(HttpServletRequest request, HttpServletResponse response) throws IOException {
        if (RequestContextUtils.getInputFlashMap(request) == null) {
            response.sendRedirect("/base.do?referer=" + UrlPathHelper.getResolvedLookupPath(request));
            return null;
        }

        HttpSession session = request.getSession();

        GitHub github = (GitHub) session.getAttribute("github");
        BaseModel baseModel = (BaseModel) session.getAttribute("baseModel");

        GHMyself myself = github.getMyself();

        UserModel model = new UserModel();

        model.setModelFields(baseModel);
        model.setTitle(myself.getName().concat(" - Cloudev"));
        model.setProfileUrl(myself.getHtmlUrl().toString());
        model.setBio(myself.getBio());
        model.setFollowersCount(myself.getFollowersCount());
        model.setFollowingCount(myself.getFollowingCount());
        model.setTwitterUsername(myself.getTwitterUsername());
        model.setBlogUrl(myself.getBlog());
        model.setLocation(myself.getLocation());
        model.setEmail(myself.getEmail());

        return new ModelAndView("base", "model", model);
    }

    @ResponseBody
    @RequestMapping("/starred_list.do")
    public String doUserStarredList(HttpServletRequest request) throws IOException {
        HttpSession session = request.getSession();

        GitHub github = (GitHub) session.getAttribute("github");

        if (github == null) {
            return null;
        }

        GHMyself myself = github.getMyself();

        PagedIterator<GHRepository> starredPagedIterator = myself.listStarredRepositories().iterator();
        List<RepositoryModel> starredRepoList = new ArrayList<>();

        while (starredRepoList.size() < 10){
            starredRepoList.add(new RepositoryModel(starredPagedIterator.next()));
        }

        return new GsonBuilder().create().toJson(starredRepoList);
    }
}
