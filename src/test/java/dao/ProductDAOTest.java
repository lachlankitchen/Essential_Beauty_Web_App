package dao;

import domain.Product;
import java.math.BigDecimal;
import java.util.Collection;
import static org.hamcrest.CoreMatchers.hasItem;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import org.hamcrest.Matchers;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.core.IsNull.nullValue;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ProductDAOTest {

    private ProductDAO dao;

    private Product p1;
    private Product p2;
    private Product p3;

    @BeforeAll
    public static void initialise() {
        JdbiDaoFactory.setJdbcUri("jdbc:h2:mem:tests;INIT=runscript from 'src/main/java/dao/schema.sql'");
    }

    @BeforeEach
    public void setUp() {

//        dao = DaoFactory.getProductDAO();
        dao = JdbiDaoFactory.getProductDAO();

        p1 = new Product("BM235", "Back Massage", "30 Minute luxurious massage", "Massage", new BigDecimal("45.00"), new BigDecimal("4.0"));
        p2 = new Product("UPW498", "Upper Lip Wax", "60 Minute deluxe massage", "Wax", new BigDecimal("10.00"), new BigDecimal("1.0"));
        p3 = new Product("EF581", "Express Facial", "60 Minute deluxe Facial", "Wax", new BigDecimal("60.00"), new BigDecimal("5.0"));

        dao.saveProduct(p1);
        dao.saveProduct(p2);
        // intentionally not saving s3
    }

    @AfterEach
    public void tearDown() {
        dao.removeProduct(p1);
        dao.removeProduct(p2);
        dao.removeProduct(p3);
    }

    @Test
    public void testSave() {
        // make sure that s3 does not yet exist
        assertThat(dao.searchById(p3.getProductId()), is(nullValue()));
        assertThat(dao.getProducts(), hasSize(2));

        // save s3
        dao.saveProduct(p3);

        // make sure that s3 now exists
        assertThat(dao.searchById(p3.getProductId()), is(p3));
        assertThat(dao.getProducts(), hasSize(3));
    }

    @Test
    public void testDelete() {
        // make sure that s1 already exists
        assertThat(dao.searchById(p1.getProductId()), is(p1));
        assertThat(dao.getProducts(), hasSize(2));

        // delete s1
        dao.removeProduct(p1);

        // make sure that s1 no longer exists
        assertThat(dao.searchById(p1.getProductId()), is(nullValue()));
        assertThat(dao.getProducts(), hasSize(1));
    }

    @Test
    public void testGetAll() {
        Collection<Product> products = dao.getProducts();

        assertThat(products, hasSize(2));
        assertThat(products, hasItem(p1));
        assertThat(products, hasItem(p2));

        // make sure that we haven't swapped/lost any fields
        Product result = products.stream()
                .filter(s -> s.getProductId().equals(p1.getProductId())).findFirst().get();
        assertThat(result, Matchers.samePropertyValuesAs(p1));
    }

    @Test
    public void testGetByID() {
        Product result = dao.searchById(p1.getProductId());

        assertThat(result, is(p1));
        assertThat(result, Matchers.samePropertyValuesAs(p1));
    }
    
    @Test
    public void getCategories() {

        assertThat(dao.getCategories(), hasSize(2));
        assertThat(dao.searchById(p2.getProductId()).getCategory(), is(p2.getCategory()));

        // save s3
        dao.saveProduct(p3);

        //checks size of unique categories is unchanged
        assertThat(dao.getCategories(), hasSize(2));
        assertThat(dao.searchById(p3.getProductId()).getCategory(), is(p3.getCategory()));
    }
    
    @Test
    public void filterByCategory() {
        
        // save s3
        dao.saveProduct(p3);
        
        assertThat(dao.filterByCategory(p1.getCategory()), hasSize(1));
        assertThat(dao.filterByCategory(p2.getCategory()), hasSize(2));
    }    
}
