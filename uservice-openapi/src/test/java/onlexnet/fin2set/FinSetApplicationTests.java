package onlexnet.fin2set;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import onlexnet.fin2set.db.DbExtension;

@SpringBootTest
@ExtendWith(DbExtension.class)
@ActiveProfiles("test")
class FinSetApplicationTests {

	@Test
	void contextLoads() {
	}

}
