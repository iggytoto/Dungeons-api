package org.gassangaming.dto;

import lombok.*;
import org.gassangaming.model.Match;
import org.gassangaming.model.MatchStatus;

import java.util.Date;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
public class MatchDto extends DtoBase {

    private Long id;
    private Long userOneId;
    private Long userTwoId;
    private MatchStatus status;
    private String serverIpAddress;
    private String serverPort;
    private Date createdAt;

    public static MatchDto ofDomain(Match m) {
        return MatchDto
                .builder()
                .id(m.getId())
                .userOneId(m.getUserOneId())
                .userTwoId(m.getUserTwoId())
                .status(m.getStatus())
                .serverIpAddress(m.getServerIpAddress())
                .serverPort(m.getServerPort())
                .createdAt(m.getCreatedAt())
                .build();
    }
}
