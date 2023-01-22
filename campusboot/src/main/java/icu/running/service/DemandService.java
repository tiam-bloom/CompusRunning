package icu.running.service;

import icu.running.pojo.Demand;

import java.util.List;

public interface DemandService {
    List<Demand> findAll();

    List<Demand> findDemandByTitle(String title);

    List<Demand> findDemandById(Integer id);

    List<Demand> findDemandByItd(Integer id);

    int upName(Demand demand);

    int addStateAndLtd(Demand demand);

    Demand findDemandByDid(String did);

    int addDemand(Demand demand);

    int upDemand(Demand demand);

    int delDemand(Integer did);

    List<Demand> findAllDemand();

    List<Demand> findBigHeadAndDemand();
}
