package dao;

import domain.Customer;
import java.util.Collection;

/**
 * @author Mark George
 */
public interface CustomerDAO {

	boolean authenticate(String username, String password);

	void removeCustomer(Customer customer);

	void saveCustomer(Customer customer);

	Customer searchByUsername(String username);
                
}