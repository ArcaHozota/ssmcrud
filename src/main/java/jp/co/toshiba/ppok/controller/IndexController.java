package jp.co.toshiba.ppok.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author Administrator
 */
@Controller
public class IndexController {

	@RequestMapping("/index")
	public void index(final HttpServletResponse response) throws IOException {
		response.sendRedirect("/templates/index.html");
	}
}
