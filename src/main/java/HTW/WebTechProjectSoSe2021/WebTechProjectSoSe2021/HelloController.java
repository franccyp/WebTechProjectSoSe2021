package HTW.WebTechProjectSoSe2021.WebTechProjectSoSe2021;


import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;


@RestController
public class HelloController {

    @RequestMapping("/")
    public String index() {

        return "Hello World!";
    }

    @RequestMapping("/hello")
    public String landingPage() {

        return "Hello World from Franccy and Kris!";
    }

}

