package com.draftking.domain;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.draftking.domain.HomeStatsAllYears;



	@RunWith(SpringJUnit4ClassRunner.class)
	@Transactional
	@ContextConfiguration(locations = { "classpath:/META-INF/applicationContext.xml" })
	/*By extending this class, we give our methods transactional support at the class level.
	If we did not do this, and we wanted transactional support, we would have to either annotate our methods with 
	@Transactional or configure our transaction manager with the @TransactionConfiguration annotation.
	*/
	public class HomeStatsAllYearsViewTest extends AbstractTransactionalJUnit4SpringContextTests {
		// @Autowired
		// protected SessionFactory sessionFactory;
		@PersistenceContext
		protected EntityManager entityManager;
		

		@Before
		public void setUp() throws Exception {

			
		}

		@Test
		// @Rollback(false)
		public void test() {

		//	HomeAddress loaded = (HomeAddress) sessionFactory.getCurrentSession()
			Query query=entityManager.createQuery("select e from HomeStatsAllYears e");
			List <HomeStatsAllYears> result =query.getResultList();
			assert(result.size()>0);
			

		}

	}