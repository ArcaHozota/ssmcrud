package jp.co.toshiba.ppok.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author Administrator
 */
@Controller
public class ForwardController {

    @RequestMapping("/index")
    public void index(HttpServletResponse response) throws IOException {
        response.sendRedirect("/index.html");
    }
}
