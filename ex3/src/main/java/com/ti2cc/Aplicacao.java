package com.ti2cc;

import static spark.Spark.*;

import service.ProdutoService;

public class Aplicacao {
	
	private static ProdutoService produtoService = new ProdutoService();
	
    public static void main(String[] args) {
        port(8080);

        post("/produto", (request, response) -> produtoService.add(request, response));

        get("/produto/:id", (request, response) -> produtoService.get(request, response));

        put("/produto/:id", (request, response) -> produtoService.update(request, response));

        delete("/produto/:id", (request, response) -> produtoService.remove(request, response));

        get("/produto", (request, response) -> produtoService.getAll(request, response));
    }
}
