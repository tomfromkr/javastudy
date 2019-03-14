package study1;

public class ShopService {

	private static ShopService  shopService = new ShopService();
	public ShopService() {}
	static ShopService getInstance() {
		return shopService;
	}
	
}
