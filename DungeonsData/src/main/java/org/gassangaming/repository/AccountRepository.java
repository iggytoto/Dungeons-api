package org.gassangaming.repository;

import org.gassangaming.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface AccountRepository extends JpaRepository<Account, Long> {


    @Query(value = "SELECT * FROM " + Account.TABLE_NAME + " u WHERE u." + Account.USER_ID_COLUMN_NAME + " = :userid", nativeQuery = true)
    Account findByUserId(@Param("userid") long userId);
}
