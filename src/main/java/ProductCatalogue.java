import dao.CustomerDAO;
import dao.DaoFactory;
import dao.ProductCollectionsDAO;
import dao.ProductJdbiDAO;
import dao.ProductDAO;
import gui.MainMenu;

/**
 * @author Mark George
 */
public class ProductCatalogue {

	public static void main(String[] args) {
                ProductDAO dao = DaoFactory.getProductDAO();

            	//ProductDAO dao = new ProductCollectionsDAO();
		//ProductDAO dao = new ProductJdbiDAO();

		// create the frame instance
		MainMenu menu = new MainMenu(dao);

		// centre the frame on the screen
		menu.setLocationRelativeTo(null);

		// show the frame
		menu.setVisible(true);
	}

}
