package dao;

import domain.Customer;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Mark George
 */
public class CustomerCollectionsDAO implements CustomerDAO {

    private static final Map<String, Customer> customers = new HashMap<>();

    // @SuppressWarnings("OverridableMethodCallInConstructor")
    public CustomerCollectionsDAO() {
        saveCustomer(new Customer("kitla230", "Lachy", "Kitchen", "apple123", "39 Brodie Street", "kitla230@student.otago.ac.nz"));
        saveCustomer(new Customer("belpi829", "Pieta", "Bell", "banana987", "116 Bowenvale Ave", "belpi829@student.otago.ac.nz"));
        saveCustomer(new Customer("wynjo482", "Josh", "Wynne", "poos456", "52 Hackthorne Road", "wynjo482@student.otago.ac.nz"));
    }

    @Override
    public void saveCustomer(Customer customer) {
        customers.put(customer.getUsername(), customer);
    }

    @Override
    public void removeCustomer(Customer customer) {
        if (customers.isEmpty()) {
            return;
        }
        customers.remove(customer.getUsername());
    }

    @Override
    public boolean authenticate(String username, String password) {
        if (customers.isEmpty()) {
            return false;
        }
        Customer customer = customers.get(username);
        if (customer == null) {
            return false;
        }
        return customer.getUsername().equals(username) && customer.getPassword().equals(password);
    }

    @Override
    public Customer searchByUsername(String username) {
        if (customers.isEmpty()) {
            return null;
        }
        return customers.get(username);
    }
}
