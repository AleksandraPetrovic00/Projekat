package ac.rs.uns.ftn.fitnescentar.service;

import ac.rs.uns.ftn.fitnescentar.model.FitnesCentar;

import java.util.List;

public interface FitnesCentarService {

    FitnesCentar findOne(Long id);

    List<FitnesCentar> findAll();

    FitnesCentar create(FitnesCentar fitnesCentar) throws Exception;

}
