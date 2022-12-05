package org.gassangaming.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

import java.io.Serializable;

import static org.gassangaming.model.Account.TABLE_NAME;

@Entity
@Table(name = TABLE_NAME)
@Getter
@Setter
public class Account implements Serializable {

    public static final String TABLE_NAME = "accounts";
    public static final String USER_ID_COLUMN_NAME = Constants.USER_ID_FOREIGN_KEY_COLUMN_NAME;
    public static final String GOLD_AMOUNT_COLUMN_NAME = "gold_amount";
    public static final String SEQUENCE_NAME = "s_tokens_id";
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = SEQUENCE_NAME)
    @SequenceGenerator(name = SEQUENCE_NAME, allocationSize = 1)
    protected Long id;

    @Column(name = USER_ID_COLUMN_NAME)
    private long userId;
    @Column(name = GOLD_AMOUNT_COLUMN_NAME)
    private long goldAmount;

}
