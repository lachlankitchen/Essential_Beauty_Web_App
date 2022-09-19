/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package web;

import domain.Sale;
import io.jooby.Jooby;
import io.jooby.StatusCode;

/**
 *
 * @author lachlankitchen
 */
public class SalesModule extends Jooby {

    public SalesModule(Sale sale) {

        post("/api/sales", ctx -> {

            return ctx.send(StatusCode.CREATED);
        });
    }

}
