package br.com.casadocodigo.loja.controllers;

import br.com.casadocodigo.loja.dao.ProdutoDAO;
import br.com.casadocodigo.loja.models.Produto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Calendar;
import java.util.List;


@Controller
@RequestMapping("/casadocodigo/relatorio-produtos")
public class RelatorioProdutoController {
    @Autowired
    ProdutoDAO produtoDAO;

    @RequestMapping( method=RequestMethod.GET)
    @ResponseBody
    public List<Produto> relatorioProduto(){
        List<Produto> produtos = produtoDAO.listar();
        return produtos;
    }

    @RequestMapping(value = "/{dataLancamento}", method = RequestMethod.GET)
    @ResponseBody
    public List<Produto> findDate(@PathVariable("dataLancamento") Calendar dataLancamento){
        List<Produto> produtos = (List<Produto>) produtoDAO.findDate(dataLancamento);
        return produtos;
    }
}