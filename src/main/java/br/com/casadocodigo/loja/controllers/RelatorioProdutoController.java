package br.com.casadocodigo.loja.controllers;

import br.com.casadocodigo.loja.dao.ProdutoDAO;
import br.com.casadocodigo.loja.models.Produto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;


@Controller
@RequestMapping("/casadocodigo/relatorio-produtos")
public class RelatorioProdutoController {
    @Autowired
    ProdutoDAO produtoDAO;

    @RequestMapping( method=RequestMethod.GET)
    @ResponseBody
    public  List<Produto> relatorioProduto(String data) throws Exception{
        if(data == null) data = "0000-00-00";
        Calendar calendar = Calendar.getInstance();
        Date formatter = new SimpleDateFormat("yyyy-MM-dd").parse(data);
        calendar.setTime(formatter);
        return produtoDAO.findDate(calendar);
    }

}