package kr.cloudev.controllers;

import kr.cloudev.models.view.BaseModel;
import org.kohsuke.github.GHMyself;
import org.kohsuke.github.GitHub;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.support.RequestContextUtils;
import org.springframework.web.util.UrlPathHelper;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@SuppressWarnings("DuplicatedCode")
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
            response.sendRedirect("/login_confirm.do");
            return null;
        }

        return "redirect:/user";
    }

    @RequestMapping("/404")
    public ModelAndView error_404(HttpServletRequest request, HttpServletResponse response) throws IOException {
        if (RequestContextUtils.getInputFlashMap(request) == null) {
            response.sendRedirect("/base.do?referer=" + UrlPathHelper.getResolvedLookupPath(request));
            return null;
        }

        HttpSession session = request.getSession();

        BaseModel model = (BaseModel) session.getAttribute("baseModel");

        model.setTitle("404".concat(" - ").concat(model.getSiteName()));

        return new ModelAndView("base", "model", model);
    }

    @RequestMapping("/base.do")
    public String doBase(HttpServletRequest request, RedirectAttributes redirectAttributes,
                         @RequestParam("referer") String referer) throws IOException {
        HttpSession session = request.getSession();

        GitHub github = (GitHub) session.getAttribute("github");

        if (github == null) {
            return "redirect:/";
        }

        GHMyself user = (GHMyself) session.getAttribute("user");

        BaseModel baseModel = new BaseModel();

        baseModel.setUrlMapHome("/user");
        baseModel.setUrlMapUser("/user");
        baseModel.setUrlMapEditor("/user");
        baseModel.setUrlMapRepositories("/repo");
        baseModel.setUrlMapPolicy("/policy");
        baseModel.setUrlMapDoLogout("/login_out.do");
        baseModel.setSiteName("Cloudev");
        baseModel.setUsername(user.getName() == null ? user.getLogin() : user.getName());
        baseModel.setLoginId(user.getLogin());
        baseModel.setAvatarUrl(user.getAvatarUrl());

        session.setAttribute("baseModel", baseModel);
        redirectAttributes.addFlashAttribute("isBaseDone", true);

        return "redirect:" + referer;
    }
}
