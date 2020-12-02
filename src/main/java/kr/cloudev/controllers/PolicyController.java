package kr.cloudev.controllers;

import kr.cloudev.models.view.BaseModel;
import kr.cloudev.models.view.page.UserModel;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.support.RequestContextUtils;
import org.springframework.web.util.UrlPathHelper;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@SuppressWarnings("DuplicatedCode")
@Controller
@RequestMapping("/policy")
public class PolicyController {

    @RequestMapping()
    public ModelAndView user(HttpServletRequest request, HttpServletResponse response) throws IOException {
        if (RequestContextUtils.getInputFlashMap(request) == null) {
            response.sendRedirect("/base.do?referer=" + UrlPathHelper.getResolvedLookupPath(request));
            return null;
        }

        HttpSession session = request.getSession();

        BaseModel baseModel = (BaseModel) session.getAttribute("baseModel");

        UserModel model = new UserModel();

        model.setModelFields(baseModel);

        return new ModelAndView("base", "model", model);
    }
}