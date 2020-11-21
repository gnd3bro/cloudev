package kr.cloudev.controllers;

import kr.cloudev.models.view.BaseModel;
import kr.cloudev.models.view.page.UserModel;
import org.kohsuke.github.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.support.RequestContextUtils;
import org.springframework.web.util.UrlPathHelper;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

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

        GHMyself user = (GHMyself) session.getAttribute("user");
        BaseModel baseModel = (BaseModel) session.getAttribute("baseModel");

        UserModel model = new UserModel();

        model.setModelFields(baseModel);
        model.setTitle(model.getUsername().concat(" - ").concat(model.getSiteName()));
        model.setProfileUrl(user.getHtmlUrl().toString());
        model.setBio(user.getBio());
        model.setFollowersCount(user.getFollowersCount());
        model.setFollowingCount(user.getFollowingCount());
        model.setTwitterUsername(user.getTwitterUsername());
        model.setBlogUrl(user.getBlog());
        model.setLocation(user.getLocation());
        model.setEmail(user.getEmail());
        model.setUrlMapDoRepoList("/repo/starred_list.do");

        return new ModelAndView("base", "model", model);
    }
}
