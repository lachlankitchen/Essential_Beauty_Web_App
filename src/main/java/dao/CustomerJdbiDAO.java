/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package dao;

import domain.Customer;
import org.jdbi.v3.sqlobject.config.RegisterBeanMapper;
import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.customizer.BindBean;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;

/**
 *
 * @author lachlankitchen
 */
public interface CustomerJdbiDAO extends CustomerDAO {

    @Override
    @SqlQuery("SELECT EXISTS (SELECT FROM CUSTOMER WHERE USERNAME = :username AND PASSWORD = :password)")
    Boolean authenticate(@Bind("username") String username, @Bind("password") String password);

    @Override
    @SqlUpdate("DELETE FROM CUSTOMER WHERE USERNAME = :username")
    void removeCustomer(@BindBean Customer customer);

    @Override
    @SqlUpdate("INSERT INTO CUSTOMER(USERNAME, FIRSTNAME, SURNAME, PASSWORD, EMAILADDRESS, SHIPPINGADDRESS) VALUES (:username, :firstName, :surname, :password, :emailAddress, :shippingAddress)")
    void saveCustomer(@BindBean Customer customer);

    @Override
    @SqlQuery("SELECT * FROM CUSTOMER WHERE USERNAME = :username")
    @RegisterBeanMapper(Customer.class)
    Customer searchByUsername(@Bind("username") String username);
    
}
