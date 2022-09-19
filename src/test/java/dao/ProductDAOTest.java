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

        p1 = new Product("WD1234", "Slimy Widget", "A widget that is covered in some kind of nasty shmoo.", "Widgets", new BigDecimal("7.32"), new BigDecimal("35.0"));
        p2 = new Product("WD5678", "Green Widget", "A widget that has gone mouldy.", "Widgets", new BigDecimal("21.43"), new BigDecimal("3.0"));
        p3 = new Product("DH8832", "Dodgy Doohicky", "A doohicky that might work, or it might not...", "Doohickies", new BigDecimal("12.32"), new BigDecimal("5.0"));

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

    // TODO getCategories and filterByCategory
    
}
