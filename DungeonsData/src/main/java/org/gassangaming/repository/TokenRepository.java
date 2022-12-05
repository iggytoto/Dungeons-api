package org.gassangaming.repository;

import org.gassangaming.model.Token;
import org.gassangaming.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface TokenRepository extends JpaRepository<Token, Long> {

    @Query(value = "SELECT * FROM " + Token.TABLE_NAME + " u WHERE u." + Token.USER_ID_COLUMN_NAME + " = :userid", nativeQuery = true)
    Token findByUserId(@Param("userid") long userId);
}
