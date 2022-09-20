package Controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

public class viewController {
    @RequestMapping("/")
    public String paginaInicial() {
        return "index";
    }
    
    @RequestMapping("/cliente")
    public String pagCliente() {
        return "cliente";
    }
}
