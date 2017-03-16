package com.picanco.menuboard.web.controller.page;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class MenuPageController {


    @RequestMapping("/menu-board/")
    public ModelAndView menuBoard() {
        return new ModelAndView("menuBoard");
    }

    @RequestMapping("/menu-board/editor")
    public ModelAndView menuBoardEditor() {
        return new ModelAndView("editor");
    }

}
