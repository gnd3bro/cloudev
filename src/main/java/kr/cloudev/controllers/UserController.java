package kr.cloudev.controllers;

import com.google.gson.GsonBuilder;
import kr.cloudev.models.HomeModel;
import kr.cloudev.models.MyRepo;
import org.kohsuke.github.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController {

    @RequestMapping("/profile")
    public ModelAndView userProfile(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession();

        GitHub github = (GitHub) session.getAttribute("github");

        if (github == null) {
            response.sendRedirect("/");
            return null;
        }

        HomeModel model = new HomeModel();
        GHUser user = github.getMyself();

        model.setTitle("Profile - Cloudev");
        model.setAvatarUrl(user.getAvatarUrl());
        model.setUsername(user.getName());
        model.setLoginId(user.getLogin());
        model.setProfileUrl(user.getHtmlUrl().toString());
        model.setFollowersCount(user.getFollowersCount());
        model.setFollowingCount(user.getFollowingCount());
        model.setBio(user.getBio());
        model.setTwitterUsername(user.getTwitterUsername());
        model.setBlogUrl(user.getBlog());
        model.setLocation(user.getLocation());
        model.setEmail(user.getEmail());

        return new ModelAndView("home", "model", model);
    }

    @ResponseBody
    @RequestMapping("/starred_list.do")
    public String doUserStarredList(HttpServletRequest request) throws IOException {
        HttpSession session = request.getSession();

        GitHub github = (GitHub) session.getAttribute("github");
        GHUser user = github.getMyself();

        PagedIterator<GHRepository> starredPagedIterator = user.listStarredRepositories().iterator();
        List<MyRepo> starredRepoList = new ArrayList<>();

        while (starredRepoList.size() < 10){
            starredRepoList.add(new MyRepo(starredPagedIterator.next()));
        }

        return new GsonBuilder().create().toJson(starredRepoList);
    }

    @ResponseBody
    @RequestMapping("/repo_list.do")
    public String doRepoList(HttpServletRequest request) throws IOException {
        HttpSession session = request.getSession();

        GitHub github = (GitHub) session.getAttribute("github");
        GHUser user = github.getMyself();

        PagedIterator<GHRepository> repositoryPagedIterator = user.listRepositories().iterator();
        List<String> repoNameList = new ArrayList<>();

        while (repositoryPagedIterator.hasNext()){
            repoNameList.add(repositoryPagedIterator.next().getName());
        }

        return new GsonBuilder().create().toJson(repoNameList);
    }

}
