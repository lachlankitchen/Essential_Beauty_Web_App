/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import domain.Product;
import domain.Sale;
import domain.SaleItem;
import java.time.LocalDateTime;
import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.customizer.BindBean;
import org.jdbi.v3.sqlobject.statement.GetGeneratedKeys;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;
import org.jdbi.v3.sqlobject.transaction.Transaction;

/**
 *
 * @author lachlankitchen
 */
public interface SaleJdbiDAO extends SaleDAO {

    @SqlUpdate("INSERT INTO SALE(SALE_DATE, CUSTOMER_ID, STATUS) VALUES (:date, :customer.customerId, :status)")
    @GetGeneratedKeys
    Integer insertSale(@BindBean Sale sale);


    @SqlUpdate("INSERT INTO SALEITEM(SALE_ID, QUANTITYPURCHASED, SALEPRICE, PRODUCTID) VALUES (:saleId, :quantityPurchased, :salePrice, :product.productId)")
    void insertSaleItem(@BindBean SaleItem item, @Bind("saleId") Integer saleId);

    @SqlUpdate("UPDATE PRODUCT SET QUANTITYINSTOCK = QUANTITYINSTOCK - :quantityPurchased WHERE PRODUCTID=:product.productId")
    void updateStockLevel(@BindBean SaleItem item);

    @Override
    @Transaction
    default void save(Sale sale) {
        // save current date
        sale.setDate(LocalDateTime.now());
        
        // set sale status
        sale.setStatus("NEW ORDER");

        // call the insertSale method, and get the generated sale ID.
        Integer saleId = insertSale(sale);
        sale.setSaleId(saleId);

        ProductDAO pdao = JdbiDaoFactory.getProductDAO();
        
        // loop through the sale's items.
        for (SaleItem item : sale.getItems()) {
            String productId = item.getProduct().getProductId();
            Product product = pdao.searchById(productId);
            item.setSalePrice(product.getListPrice());
            insertSaleItem(item, saleId);
            updateStockLevel(item);
        }

    }
}