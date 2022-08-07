import org.example.Account;
import org.example.Employee;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import javax.persistence.*;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

class ForeignKeyAssociationTest {

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

  @DisplayName("使用外键单向关联")
  @Test
  void test() {
    Account account = new Account("002");
    Employee employee = new Employee("zhang", "san", "2@qq.com");
    employee.setAccount(account);

    entityManager.persist(employee);

    TypedQuery<Employee> from_employee = entityManager.createQuery("from Employee where account = :account", Employee.class)
      .setParameter("account", account);

    assertAll("employee外键获取account对象",
      () -> assertEquals(employee.getFirstName(), from_employee.getSingleResult().getFirstName()),
      () -> assertEquals(employee.getLastName(), from_employee.getSingleResult().getLastName()),
      () -> assertEquals(employee.getEmail(), from_employee.getSingleResult().getEmail()),
      () -> assertEquals(account.getNum(), from_employee.getSingleResult().getAccount().getNum()));
  }

  @DisplayName("使用外键双向关联")
  @Test
  void test1() {
    Account account = new Account("002");
    Employee employee = new Employee("zhang", "san", "2@qq.com");
    employee.setAccount(account);
    account.setEmployee(employee);

    entityManager.persist(employee);

    TypedQuery<Account> from_account = entityManager.createQuery("from Account where num = :num", Account.class)
      .setParameter("num", account.getNum());

    assertAll("account获取employee",
      () -> assertEquals(employee.getFirstName(), from_account.getSingleResult().getEmployee().getFirstName()),
      () -> assertEquals(employee.getLastName(), from_account.getSingleResult().getEmployee().getLastName()),
      () -> assertEquals(employee.getEmail(), from_account.getSingleResult().getEmployee().getEmail())
    );
  }
}
