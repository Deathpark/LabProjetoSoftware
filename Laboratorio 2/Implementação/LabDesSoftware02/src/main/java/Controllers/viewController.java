package Controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@Controller
@RequestMapping("/")
public class viewController {
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String pagInicial() {
        return "index";
    }
    
    @RequestMapping(value = "/cliente", method = RequestMethod.GET)
    public String pagCliente() {
        return "cliente";
    }

}
