package org.gassangaming.repository;

import org.gassangaming.model.Match;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import static org.gassangaming.repository.Constants.PARAM_1;

public interface MatchMakingRepository extends JpaRepository<Match, Long> {

    String MATCH_EXISTS_FOR_USER_QUERY = "select case when count(*) > 0 then 'true' else 'false' end from matches m where m.user_one_id=:" + PARAM_1 + " or m.user_two_id=:" + PARAM_1;
    String FIND_FOR_USER_QUERY = "select m from Match m where m.userOneId=:" + PARAM_1 + " or m.userTwoId=:" + PARAM_1;
    String FIND_FIRST_FREE_TO_JOIN_MATCH_QUERY = "SELECT * FROM matches m WHERE m.user_two_id is NULL LIMIT 1";
    String FIND_CANCELLABLE_FOR_USER_QUERY = "select m from Match m where m.userOneId=:" + PARAM_1 + " and m.status='Searching'";
    String FIND_FIRST_OLDEST_AWAITING_SERVER = "SELECT * FROM matches m WHERE m.status ='PlayersFound' ORDER by m.created_at LIMIT 1";


    @Query(value = MATCH_EXISTS_FOR_USER_QUERY, nativeQuery = true)
    boolean hasRegistered(@Param(PARAM_1) long userId);

    @Query(value = FIND_FIRST_FREE_TO_JOIN_MATCH_QUERY, nativeQuery = true)
    Match findFirstFree();

    @Query(value = FIND_CANCELLABLE_FOR_USER_QUERY)
    Match findCancellableForUser(@Param(PARAM_1) long userId);

    @Query(value = FIND_FOR_USER_QUERY)
    Match findForUser(@Param(PARAM_1) long userId);

    @Query(value = FIND_FIRST_OLDEST_AWAITING_SERVER, nativeQuery = true)
    Match findFirstOldestAwaitingServer();
}
