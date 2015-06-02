import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import pl.proacem.dao.HbnSingleOrderDao;
import pl.proacem.model.SingleOrder;
import pl.proacem.service.SingleOrderService;


public class SingleOrderServiceTest {

	
	@Test
	public void test() {
		List<SingleOrder> list = new ArrayList<SingleOrder>();
		SingleOrderService service = new SingleOrderService();
		list = service.findByOrderNumber("452");
		System.out.println(list);
		//assertEquals((1+1), 2);
	}

	@Test
	public void HbnSingleOrderDaoTest(){
		List<SingleOrder> list = new ArrayList<SingleOrder>();
		HbnSingleOrderDao hbnSingleOrderDao = new HbnSingleOrderDao();
		list = hbnSingleOrderDao.findByOrderNumber("452");
		System.out.println(list);
		
		
	}
}
