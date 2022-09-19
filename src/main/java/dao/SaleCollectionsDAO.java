/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import domain.Sale;
import java.util.HashMap;
import java.util.Map;


/**
 *
 * @author lachlankitchen
 */
public class SaleCollectionsDAO implements SaleDAO {

    private static final Map<Integer, Sale> sales = new HashMap<>();
    
    public void save(Sale sale){
        sales.put(sale.getSaleId(), sale);
    }

}