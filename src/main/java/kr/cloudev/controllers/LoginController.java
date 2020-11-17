package kr.cloudev.controllers;

import org.kohsuke.github.GitHub;
import org.kohsuke.github.GitHubBuilder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Controller
public class LoginController {

    @RequestMapping("/login")
    public ModelAndView login(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession();
        GitHub github = (GitHub) session.getAttribute("github");

        if (github != null) {
            response.sendRedirect("/");
            return null;
        }

        ModelAndView mav = new ModelAndView();
        mav.addObject("title", "로그인 - Cloudev");
        mav.setViewName("login");

        return mav;
    }

    @RequestMapping(value = "/login.do", method = RequestMethod.POST)
    public String doLogin(HttpServletRequest request) {
        HttpSession session = request.getSession();
        String token = request.getParameter("token");

        if (token != null) {
            session.setAttribute("token", token);

            return "redirect:/login_confirm.do";
        }

        return "redirect:/login";
    }

    @RequestMapping("/login_confirm.do")
    public String doLoginConfirm(HttpServletRequest request) throws Exception {
        HttpSession session = request.getSession();
        String token = (String) session.getAttribute("token");

        GitHub github = new GitHubBuilder().withOAuthToken(token).build();

        if (github.isCredentialValid()) {
            session.setAttribute("github", github);

            return "redirect:/";
        }
        session.setAttribute("token", null);

        return "redirect:/login";
    }

    @RequestMapping("/login_out.do")
    public String doLoginOut(HttpServletRequest request) {
        HttpSession session = request.getSession();

        session.setAttribute("github", null);
        session.setAttribute("token", null);

        return "redirect:/";
    }

}
