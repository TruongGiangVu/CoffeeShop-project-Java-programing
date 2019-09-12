package bus;

import java.util.ArrayList;

import dao.ProductTypeDAO;
import dto.ProductType;

public class ProductTypeBUS {
	private ProductTypeDAO dao = new ProductTypeDAO();
	public ArrayList<ProductType> readAll(){
		return dao.readAll();
	}
	public ArrayList<ProductType> read(){
		return dao.readAll();
	}
	public ProductType searchByID(String id) {
		ArrayList<ProductType> arr = dao.readAll();
		for(ProductType p : arr) {
			if(p.getType_id().equals(id)) return p;
		}
		return null;
	}
	public ProductType searchByName(String name) {
		ArrayList<ProductType> arr = dao.readAll();
		for(ProductType p : arr) {
			if(p.getType_name().equals(name)) return p;
		}
		return null;
	}
	public int add(ProductType p) {
		return dao.add(p);
	}
	public int delete(String id) {
		return dao.delete(id);
	}
	public int disable(String id) {
		return dao.disable(id);
	}
	public int active(String id) {
		return dao.active(id);
	}
	public int update(ProductType p) {
		return dao.update(p);
	}
}
