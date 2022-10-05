/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package web;

import domain.Sale;
import io.jooby.Jooby;
import io.jooby.StatusCode;
import dao.SaleDAO;

/**
 *
 * @author lachlankitchen
 */
public class SalesModule extends Jooby {

    public SalesModule(SaleDAO dao) {
        
        post("/api/sales", ctx -> {
          
            Sale sale = ctx.body().to(Sale.class);
            System.out.println(sale);
            dao.save(sale);
            return ctx.send(StatusCode.CREATED);
        });       
    }

}
