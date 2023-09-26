package jp.co.toshiba.ppok.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * インデクスコントローラ
 *
 * @author ArcaHozota
 * @since 2.64
 */
@Controller
public class IndexController {

	@GetMapping("/index")
	public void index(final HttpServletResponse response) throws IOException {
		response.sendRedirect("/public/cities.html");
	}
}
