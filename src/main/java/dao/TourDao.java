package dao;

import models.Tour;
import models.Transfert;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TourDao extends JpaRepository<Tour, Integer> {

    @Query("SELECT t FROM Tour t Where t.transf.idTransfert=:idtransf and t.etatTour = 1")
    List<Tour> toursActive( @Param("idtransf") int idtransf);


}
