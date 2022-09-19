/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import domain.Customer;
import domain.Product;
import domain.Sale;
import domain.SaleItem;
import java.math.BigDecimal;
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

    @SqlUpdate("INSERT INTO SALE(DATE, CUSTOMER, STATUS) VALUES (:date, :customer, :status)")
    @GetGeneratedKeys
    Integer insertSale(@BindBean Sale sale);


    @SqlUpdate("INSERT INTO SALEITEM(QUANTITYPURCHASED, SALEPRICE, PRODUCT) VALUES (:quantityPurchased, :salePrice, :product)")
    void insertSaleItem(@BindBean SaleItem item, @Bind("saleId") Integer saleId);

    @SqlUpdate("UPDATE PRODUCT SET QUANTITYINSTOCK = QUANTITYINSTOCK - :quantityPurchased")
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

        // loop through the sale's items.
        for (SaleItem item : sale.getItems()) {
            insertSaleItem(item, saleId);
            updateStockLevel(item);
        }

    }
}