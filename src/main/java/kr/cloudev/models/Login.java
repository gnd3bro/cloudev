package kr.cloudev.models;

import org.springframework.web.servlet.ModelAndView;

public class Login {

    private ModelAndView mav;

    public Login(ModelAndView mav) {
        this.mav = mav;
    }

    public void bindDataToAttributes() {

    }
}
