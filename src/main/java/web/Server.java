package web;

import dao.CustomerDAO;
import dao.DaoFactory;
import dao.ProductCollectionsDAO;
import dao.ProductDAO;
import dao.SaleDAO;
import domain.Product;
import io.jooby.Jooby;
import io.jooby.ServerOptions;
import io.jooby.json.GsonModule;
import java.math.BigDecimal;

public class Server extends Jooby {

    ProductDAO pDao = DaoFactory.getProductDAO();
    CustomerDAO cDao = DaoFactory.getCustomerDAO();
    SaleDAO sDao = DaoFactory.getSaleDAO();

    public Server() {
        setServerOptions(new ServerOptions().setPort(8087));
        install(new GsonModule());
        mount(new CustomerModule(cDao));
        mount(new ProductModule(pDao));
        mount(new SalesModule(sDao));
        mount(new StaticAssetModule());
    }

    public static void main(String[] args) {
        // some dummy data for testing with
        ProductDAO dao = DaoFactory.getProductDAO();
    
        dao.saveProduct(new Product("BM235", "Back Massage", "30 Minute luxurious massage", "Massage", new BigDecimal("45.0"), new BigDecimal("4")));
        dao.saveProduct(new Product("HSM79", "Hot Stone Massage", "60 Minute hand massage", "Massage", new BigDecimal("75.0"), new BigDecimal("10")));
        dao.saveProduct(new Product("BT7123", "Back Exfoliation", "30 Minute Back blissful treatment", "Massage", new BigDecimal("70.0"), new BigDecimal("8")));
        dao.saveProduct(new Product("FBM724", "Fully Body Massage", "60 Minute deluxe massage", "Massage", new BigDecimal("60.0"), new BigDecimal("2")));
        dao.saveProduct(new Product("UPW498", "Upper Lip Wax", "60 Minute deluxe massage", "Massage", new BigDecimal("10.0"), new BigDecimal("1")));
        dao.saveProduct(new Product("FLW912", "Full Leg Wax", "60 Minute deluxe massage", "Massage", new BigDecimal("42.0"), new BigDecimal("9")));
        dao.saveProduct(new Product("TQW438", "Three Quarter Wax", "60 Minute deluxe massage", "Massage", new BigDecimal("35.0"), new BigDecimal("3")));
        dao.saveProduct(new Product("CW921", "Chin Wax", "60 Minute deluxe massage", "Massage", new BigDecimal("10.0"), new BigDecimal("7")));
        dao.saveProduct(new Product("EF581", "Express Facial", "60 Minute deluxe massage", "Massage", new BigDecimal("60.0"), new BigDecimal("5")));
        dao.saveProduct(new Product("TW913", "The Works", "60 Minute deluxe massage", "Massage", new BigDecimal("60.0"), new BigDecimal("6")));
        dao.saveProduct(new Product("DF658", "Deluxe Facial", "60 Minute deluxe massage", "Massage", new BigDecimal("75.0"), new BigDecimal("8")));
        dao.saveProduct(new Product("M581", "Microdermabrasion", "60 Minute deluxe massage", "Massage", new BigDecimal("65.0"), new BigDecimal("5")));
        dao.saveProduct(new Product("IMP581", "Indulge Me Pack", "60 Minute deluxe massage", "Massage", new BigDecimal("90.0"), new BigDecimal("1")));
        dao.saveProduct(new Product("ET913", "Eye Trio", "60 Minute deluxe massage", "Massage", new BigDecimal("34.0"), new BigDecimal("10")));
        
        System.out.println("\nStarting Server.");
        new Server().start();

    }

}
