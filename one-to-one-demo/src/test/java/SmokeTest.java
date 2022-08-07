import org.example.Account;
import org.example.Employee;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import javax.persistence.*;

import static org.junit.jupiter.api.Assertions.*;

class SmokeTest {

  EntityManagerFactory entityManagerFactory;

  EntityManager entityManager;

  EntityTransaction transaction;

  @BeforeEach
  void before() {
    entityManagerFactory = Persistence.createEntityManagerFactory("jpa");
    entityManager = entityManagerFactory.createEntityManager();
    transaction = entityManager.getTransaction();
    transaction.begin();
  }

  @AfterEach
  void after() {
    if (transaction != null)
      transaction.commit();
    if (entityManager != null)
      entityManager.close();
    if (entityManagerFactory != null)
      entityManagerFactory.close();
  }

  @DisplayName("jpa初始化表")
  @Test
  void initTable() {
    Account account = new Account("001");
    entityManager.persist(account);
    Query from_account = entityManager.createQuery("from Account where num = '001'");

    Employee employee = new Employee("zhang", "san", "1@qq.com");
    entityManager.persist(employee);
    Query from_employee = entityManager.createQuery("from Employee where firstName = 'zhang' and lastName = 'san'");

    assertAll("初始化account、employee表",
      () -> assertEquals(1, from_account.getResultList().size()),
      () -> assertEquals(1, from_employee.getResultList().size())
    );
  }
}
