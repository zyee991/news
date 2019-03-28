import com.itcuc.NewsApplication;
import com.itcuc.base.entity.Manager;
import com.itcuc.base.entity.Role;
import com.itcuc.base.repository.ManagerDao;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes= NewsApplication.class)
public class TestJap {
    @Autowired
    ManagerDao managerDao;
    @Test
    public void test() {
        String id = "9bd880a0-3462-4ea5-acf8-2ed5ae629bca";
        Manager manager = managerDao.findById(id).get();
        List<Role> roleList = managerDao.findRoleByUserId(id);

        System.out.println("end");
    }
}