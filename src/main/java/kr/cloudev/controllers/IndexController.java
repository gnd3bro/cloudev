package kr.cloudev.controllers;

import kr.cloudev.models.view.BaseModel;
import org.kohsuke.github.GHMyself;
import org.kohsuke.github.GitHub;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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

    @RequestMapping("/base.do")
    public String doBase(HttpServletRequest request, RedirectAttributes redirectAttributes,
                         @RequestParam("referer") String referer) throws IOException {
        HttpSession session = request.getSession();

        GitHub github = (GitHub) session.getAttribute("github");

        if (github == null) {
            return "redirect:/";
        }

        GHMyself myself = github.getMyself();
        BaseModel baseModel = new BaseModel();

        baseModel.setUrlMapHome("/user");
        baseModel.setUrlMapUser("/user");
        baseModel.setUrlMapEditor("/user");
        baseModel.setUrlMapRepositories("/repo");
        baseModel.setUrlMapDoLogout("/login_out.do");
        baseModel.setSiteName("Cloudev");
        baseModel.setUsername(myself.getName());
        baseModel.setLoginId(myself.getLogin());
        baseModel.setAvatarUrl(myself.getAvatarUrl());

        session.setAttribute("baseModel", baseModel);
        redirectAttributes.addFlashAttribute("isBaseDone", true);

        return "redirect:" + referer;
    }
}
