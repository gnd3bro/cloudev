package kr.cloudev.controllers;

import kr.cloudev.models.HomeModel;
import org.kohsuke.github.GHRepository;
import org.kohsuke.github.GitHub;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Map;

@Controller
public class IndexController {

    @RequestMapping("/")
    public String index() {

        return "redirect:/home";
    }

    @RequestMapping("/home")
    public ModelAndView home(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession();

        String token = (String) session.getAttribute("token");
        GitHub github = (GitHub) session.getAttribute("github");

        if (token == null) {
            response.sendRedirect("/login");
            return null;
        } else if (github == null) {
            response.sendRedirect("/login.do");
            return null;
        }


        HomeModel model = new HomeModel();

        model.setTitle("홈 - Cloudev");
        model.setUsername(github.getMyself().getName());
        model.setAvatar_url(github.getMyself().getAvatarUrl());
        model.setProfile_url(github.getMyself().getHtmlUrl().toString());

        return new ModelAndView("home", "model", model);
    }

}
