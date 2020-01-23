package com.ditra.travelagency.core.voyage;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface VoyageRespitory  extends JpaRepository<Voyage, Integer>{
    List<Voyage> findByPrixLessThanEqualAndNbPlacesNot(double p, int nb);
}
