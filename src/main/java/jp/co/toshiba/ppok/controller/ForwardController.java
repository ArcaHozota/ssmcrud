package jp.co.toshiba.ppok.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
public class ForwardController {

    @RequestMapping("/grssmcrud/city")
    public void index(HttpServletResponse response) throws IOException {
        response.sendRedirect("/static/cities.html");
    }
}
