package kr.cloudev.controllers;

import org.kohsuke.github.GitHub;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

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

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("title", "홈 - Cloudev");
        modelAndView.addObject("username", github.getMyself().getName());
        modelAndView.addObject("avatar_url", github.getMyself().getAvatarUrl());
        modelAndView.addObject("profile_url", github.getMyself().getUrl());
        modelAndView.setViewName("home");

        return modelAndView;
    }

}
