package org.gassangaming.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = Match.TABLE_NAME)
@Getter
@Setter
public class Match implements Serializable {
    public static final String TABLE_NAME = "matches";
    public static final String USER_ONE_ID_COLUMN_NAME = "user_one_id";
    public static final String USER_TWO_ID_COLUMN_NAME = "user_two_id";
    public static final String MATCH_STATUS_COLUMN_NAME = "status";
    public static final String SERVER_ADDRESS_COLUMN_NAME = "address";
    public static final String SERVER_PORT_COLUMN_NAME = "port";
    public static final String CREATED_AT_COLUMN_NAME = "created_at";
    public static final String SEQUENCE_NAME = "s_matches_id";

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = SEQUENCE_NAME)
    @SequenceGenerator(name = SEQUENCE_NAME, allocationSize = 1)
    protected Long id;
    @Column(name = USER_ONE_ID_COLUMN_NAME)
    private Long userOneId;
    @Column(name = USER_TWO_ID_COLUMN_NAME)
    private Long userTwoId;
    @Column(name = MATCH_STATUS_COLUMN_NAME)
    @Enumerated(EnumType.STRING)
    private MatchStatus status;
    @Column(name = SERVER_ADDRESS_COLUMN_NAME)
    private String serverIpAddress;
    @Column(name = SERVER_PORT_COLUMN_NAME)
    private String serverPort;
    @Column(name = CREATED_AT_COLUMN_NAME)
    private Date createdAt;

    protected Match() {
    }

    public static Match Of(long userOneId, MatchStatus status, Date createdAt) {
        final var m = new Match();
        m.setUserOneId(userOneId);
        m.setStatus(status);
        m.setCreatedAt(createdAt);
        return m;
    }
}
