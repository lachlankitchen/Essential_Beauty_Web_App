/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import domain.Product;
import domain.Sale;
import domain.SaleItem;
import java.math.BigDecimal;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author lachlankitchen
 */
public class SaleCollectionsDAO implements SaleDAO {

    private static final Set<Sale> sales = new HashSet<>();

    @Override
    public void save(Sale sale) {
        sales.add(sale);
        Collection<SaleItem> items = sale.getItems();
        for(SaleItem item : items){
            Product product = item.getProduct();
            BigDecimal quantityInStock = product.getQuantityInStock();
            BigDecimal quantityPurchased = item.getQuantityPurchased();
            System.out.println(quantityInStock.subtract(quantityPurchased));
            product.setQuantityInStock(quantityInStock.subtract(quantityPurchased));
            
            
            //saveProduct
        }
    }

}
