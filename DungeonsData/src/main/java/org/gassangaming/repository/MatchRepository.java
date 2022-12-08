package org.gassangaming.repository;

import org.gassangaming.model.Match;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import static org.gassangaming.repository.Constants.PARAM_1;

public interface MatchRepository extends JpaRepository<Match, Long> {

    String MATCH_EXISTS_FOR_USER_QUERY = "select if(count(m)>0,'true','false') from Match m where m.userOneId=:" + PARAM_1 + " or m.userTwoId=:" + PARAM_1;
    String FIND_FOR_USER_QUERY = "select m from Match m where m.userOneId=:" + PARAM_1 + " or m.userTwoId=:" + PARAM_1;
    String FIND_FIRST_FREE_TO_JOIN_MATCH_QUERY = "SELECT * FROM matches m WHERE m.user_two_id=NULL LIMIT 1";
    String FIND_CANCELLABLE_FOR_USER_QUERY = "select from Match m where m.userOneId=:" + PARAM_1 + " and m.status=Searching";


    @Query(name = MATCH_EXISTS_FOR_USER_QUERY)
    boolean matchExistsForUser(@Param(PARAM_1) long userId);

    @Query(name = FIND_FIRST_FREE_TO_JOIN_MATCH_QUERY, nativeQuery = true)
    Match findFirstFreeToJoinMatch();

    @Query(name = FIND_CANCELLABLE_FOR_USER_QUERY)
    Match findCancellableForUser(@Param(PARAM_1) long userId);

    @Query(name = FIND_FOR_USER_QUERY)
    Match findForUser(long userId);
}
