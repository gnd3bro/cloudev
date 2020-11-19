package kr.cloudev.controllers;

import kr.cloudev.models.HomeModel;
import org.kohsuke.github.GHUser;
import org.kohsuke.github.GitHub;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Controller
@RequestMapping("/repo")
public class RepoController {

    @RequestMapping()
    public ModelAndView repoList(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession();

        GitHub github = (GitHub) session.getAttribute("github");

        if (github == null) {
            response.sendRedirect("/");
            return null;
        }

        HomeModel model = new HomeModel();
        GHUser user = github.getMyself();

        model.setTitle("Repo List - Cloudev");
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

}
