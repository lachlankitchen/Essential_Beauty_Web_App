/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package dao;

import domain.Product;
import java.util.Collection;
import org.jdbi.v3.sqlobject.config.RegisterBeanMapper;
import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.customizer.BindBean;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;

/**
 *
 * @author lachlankitchen
 */
public interface ProductJdbiDAO extends ProductDAO {

    @Override
    @SqlQuery("SELECT * FROM PRODUCT WHERE CATEGORY = :category")
    @RegisterBeanMapper(Product.class)
    public Collection<Product> filterByCategory(@Bind("category") String category);

    @Override
    @SqlQuery("SELECT DISTINCT CATEGORY FROM PRODUCT ORDER BY CATEGORY")
    @RegisterBeanMapper(Product.class)
    public Collection<String> getCategories();

    @Override
    @SqlQuery("SELECT * FROM PRODUCT ORDER BY PRODUCTID")
    @RegisterBeanMapper(Product.class)
    public Collection<Product> getProducts();

    @Override
    @SqlUpdate("DELETE FROM PRODUCT WHERE PRODUCTID = :productId")
    public void removeProduct(@BindBean Product product);

    @Override
    @SqlUpdate("INSERT INTO PRODUCT(PRODUCTID, NAME, DESCRIPTION, CATEGORY, LISTPRICE, QUANTITYINSTOCK) VALUES (:productId, :name, :description, :category, :listPrice, :quantityInStock)")
    public void saveProduct(@BindBean Product product);

    @Override
    @SqlQuery("SELECT * FROM PRODUCT WHERE PRODUCTID = :productId")
    @RegisterBeanMapper(Product.class)
    public Product searchById(@Bind("productId") String productId);
}
