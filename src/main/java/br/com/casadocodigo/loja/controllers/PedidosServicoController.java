package br.com.casadocodigo.loja.controllers;

import br.com.casadocodigo.loja.models.Pedidos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@RequestMapping("/pedidos")
@Controller
public class PedidosServicoController {

    @Autowired
    private RestTemplate restTemplate;

    @GetMapping
    public ModelAndView finalizar(RedirectAttributes model) {
        String uri = "https://book-payment.herokuapp.com/orders";
        try {
            Pedidos[] response = restTemplate.getForObject(uri, Pedidos[].class);
            ModelAndView modelAndView = new ModelAndView("carrinho/pedidos");
            modelAndView.addObject("response", response);
            return modelAndView;
        } catch (HttpClientErrorException e) {
            e.printStackTrace();
            return new ModelAndView("redirect:/pedidos");
        }
    }
}