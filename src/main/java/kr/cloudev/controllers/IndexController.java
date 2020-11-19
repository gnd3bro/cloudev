package kr.cloudev.controllers;

import org.kohsuke.github.GitHub;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Controller
public class IndexController {

    @RequestMapping("/")
    public String index(HttpServletRequest request, HttpServletResponse response) throws IOException {
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

        return "redirect:/user";
    }

}
