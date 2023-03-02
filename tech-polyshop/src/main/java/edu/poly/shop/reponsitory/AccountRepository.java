package edu.poly.shop.reponsitory;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import edu.poly.shop.domain.Account;

@Repository
public interface AccountRepository extends JpaRepository<Account, String>{

}
