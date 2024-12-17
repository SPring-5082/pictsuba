package test.dao;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import beans.Advertisement;
import dao.AdvertisementDAO;

class TestAdvertisementDAO {

	@Test
	void testFindAll() throws SQLException {
		List<Advertisement> list = new ArrayList<Advertisement>();
		Advertisement advertisement  =
			new Advertisement(1, "Prime送料無料", "https://m.media-amazon.com/images/I/71TdCjGge+L._SX3000_.jpg");
		Advertisement advertisement2 =
			new Advertisement(2, "NEW ECHO SPOT Alexa", "https://m.media-amazon.com/images/I/71G11mdH2LL._SX3000_.jpg");
		Advertisement advertisement3 =
			new Advertisement(3, "Christmas Gifts FIRE", "https://m.media-amazon.com/images/I/61dC9VndL+L._SX3000_.jpg");
		list.add(advertisement);
		list.add(advertisement2);
		list.add(advertisement3);
		List<Advertisement> list2 = AdvertisementDAO.findAll();
		for(int i = 0;i < list.size();i ++) {
			Advertisement db = list2.get(i);
			Advertisement nodb = list.get(i);
			assertEquals(db.advertisement_id(), nodb.advertisement_id());
			assertEquals(db.advertisement_name(), nodb.advertisement_name());
			assertEquals(db.url(), nodb.url());
		}
		
	}

}
