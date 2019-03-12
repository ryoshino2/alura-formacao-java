package br.com.casadocodigo.loja.controllers;

import br.com.casadocodigo.loja.dao.UsuarioDAO;
import br.com.casadocodigo.loja.models.Role;
import br.com.casadocodigo.loja.models.Usuario;
import br.com.casadocodigo.loja.validation.UsuarioValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    UsuarioDAO usuarioDAO;


    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.addValidators(new UsuarioValidator());
    }
    @RequestMapping("/form")
    public ModelAndView form(Usuario usuario) {
        ModelAndView modelAndView = new ModelAndView("usuarios/form");
        return modelAndView;
    }



    @RequestMapping(method= RequestMethod.POST)
    public ModelAndView gravar(@Valid Usuario usuario, BindingResult result,
                               RedirectAttributes redirectAttributes){

        if(result.hasErrors()) {
            return form(usuario);
        }
        usuario.setRoles(Arrays.asList(new Role("ROLE_USER")));

        usuarioDAO.gravar(usuario);

        redirectAttributes.addFlashAttribute("senha", "Usuário cadastrado com sucesso");
        return new ModelAndView("redirect:/");
    }

    @RequestMapping( method=RequestMethod.GET)
    public ModelAndView listar() {
        List<Usuario> usuarios = usuarioDAO.listar();
        ModelAndView modelAndView = new ModelAndView("/usuarios/lista");
        modelAndView.addObject("usuarios", usuarios);
        return modelAndView;
    }

    @RequestMapping("/detalhe/{email}")
    public ModelAndView detalhe(@PathVariable("email") String email){
        ModelAndView modelAndView = new ModelAndView("/detalhe");
        Usuario usuario= usuarioDAO.find(email);

//	    if(true) throw new RuntimeException("Excessão Genérica Acontecendo!!!!");

        modelAndView.addObject("ususario", usuario);
        return modelAndView;
    }

    @RequestMapping(value="/editarRole")
    public ModelAndView checkBox(String email,String nome) {

        System.out.println("email: "+email+" nome: "+nome);
        Usuario usuarios = usuarioDAO.find(email);

        ModelAndView modelAndView = new ModelAndView("usuarios/checkBoxUsuario");
        modelAndView.addObject("usuarios", usuarios);
        return modelAndView;

    }

    @RequestMapping(value="/editado")
    public ModelAndView updateRole(Usuario usuario, RedirectAttributes redirectAttributes) {

        if (usuario.getRoles().isEmpty()) {
            redirectAttributes.addFlashAttribute("falha", "Deve possuir alguma role");
            return new ModelAndView("redirect:/usuarios");
        }

        usuarioDAO.updateRole(usuario);

        redirectAttributes.addFlashAttribute("senha", "Roles alteradas com sucesso");
        return new ModelAndView("redirect:/usuarios");
    }


}
