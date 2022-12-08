package org.gassangaming.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.ArrayList;

@Data
@EqualsAndHashCode(callSuper = true)
public class MatchMakingRegisterRequestDto extends DtoBase {
    ArrayList<Long> rosterUnitsIds;

}
