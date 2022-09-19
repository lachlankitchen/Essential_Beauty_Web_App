package dao;

import domain.Customer;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.MatcherAssert.assertThat;
import org.hamcrest.Matchers;
import static org.hamcrest.core.IsNull.nullValue;
import org.junit.jupiter.api.AfterEach;
import static org.junit.jupiter.api.Assertions.assertFalse;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class CustomerDAOTest {

    private CustomerDAO dao;

    private Customer c1;
    private Customer c2;
    private Customer c3;

    @BeforeAll
    public static void initialise() {
        JdbiDaoFactory.setJdbcUri("jdbc:h2:mem:tests;INIT=runscript from 'src/main/java/dao/schema.sql'");
    }

    @BeforeEach
    public void setUp() {

        //dao = DaoFactory.getCustomerDAO();
        dao = JdbiDaoFactory.getCustomerDAO();
                
        c1 = new Customer("kitla230", "Lachy", "Kitchen", "apple123", "39 Brodie Street", "kitla230@student.otago.ac.nz");
        c2 = new Customer("belpi829", "Pieta", "Bell", "banana987", "116 Bowenvale Ave", "belpi829@student.otago.ac.nz");
        c3 = new Customer("wynjo482", "Josh", "Wynne", "orange456", "52 Hackthorne Road", "wynjo482@student.otago.ac.nz");

        dao.saveCustomer(c1);
        dao.saveCustomer(c2);
        // intentionally not saving c3
    }

    @AfterEach
    public void tearDown() {
        dao.removeCustomer(c1);
        dao.removeCustomer(c2);
        dao.removeCustomer(c3);
    }

    @Test
    public void testSave() {
        // make sure that c3 does not yet exist
        assertThat(dao.searchByUsername(c3.getUsername()), is(nullValue()));

        // save c3
        dao.saveCustomer(c3);

        // make sure that c3 now exists
        assertThat(dao.searchByUsername(c3.getUsername()), is(c3));
    }

    @Test
    public void testDelete() {
        // make sure that c1 already exists
        assertThat(dao.searchByUsername(c1.getUsername()), is(c1));

        // delete c1
        dao.removeCustomer(c1);

        assertThat(dao.searchByUsername(c3.getUsername()), is(nullValue()));
    }

    @Test
    public void testAuthenticate() {

        Customer shallowCopy = new Customer(c1.getUsername(), c1.getFirstName(), c1.getSurname(), c1.getPassword(), c1.getShippingAddress(), c1.getEmailAddress());

        assertThat(dao.authenticate(c1.getUsername(), c1.getPassword()), is(true));
        assertThat(dao.authenticate(c1.getUsername(), "wrong"), is(false));
        
        assertThat(shallowCopy, Matchers.samePropertyValuesAs(c1));

        shallowCopy.setPassword("fonnyPassword");

        assertThat(shallowCopy, not(Matchers.samePropertyValuesAs(c1, "customerId")));
        assertFalse(dao.authenticate(c1.getUsername(), shallowCopy.getPassword()));
    }

    @Test
    public void testSearchByUsername() {
        Customer result = dao.searchByUsername(c1.getUsername());

        assertThat(result, is(c1));
        assertThat(result, Matchers.samePropertyValuesAs(c1, "customerId"));
    }
}
