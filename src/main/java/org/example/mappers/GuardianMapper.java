package org.example.mappers;

import org.example.dtos.GuardianRequest;
import org.example.entities.Guardian;

public class GuardianMapper {
    public static Guardian fromRequest(GuardianRequest guardianRequest){
        return new Guardian(guardianRequest.name(), guardianRequest.email(),
                guardianRequest.phone(), guardianRequest.address());
    }
}
