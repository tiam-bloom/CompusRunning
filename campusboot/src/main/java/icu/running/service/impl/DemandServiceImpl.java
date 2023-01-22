package icu.running.service.impl;

import icu.running.mapper.DemandMapper;
import icu.running.pojo.Demand;
import icu.running.service.DemandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DemandServiceImpl implements DemandService {
    @Autowired(required = false)
    private DemandMapper demandMapper;

    @Override
    public List<Demand> findAll() {
        return demandMapper.findAll();
    }

    @Override
    public List<Demand> findDemandByTitle(String title) {
        return demandMapper.findDemandByTitle(title);
    }

    @Override
    public List<Demand> findDemandById(Integer id) {
        return demandMapper.findDemandById(id);
    }

    @Override
    public List<Demand> findDemandByItd(Integer id) {
        return demandMapper.findDemandByItd(id);
    }

    @Override
    public int upName(Demand demand) {
        return demandMapper.upName(demand);
    }

    @Override
    public int addStateAndLtd(Demand demand) {
        return demandMapper.addStateAndLtd(demand);
    }

    @Override
    public Demand findDemandByDid(String did) {
        return demandMapper.findDemandByDid(did);
    }

    @Override
    public int addDemand(Demand demand) {
        return demandMapper.addDemand(demand);
    }

    @Override
    public int upDemand(Demand demand) {
        return demandMapper.upDemand(demand);
    }

    @Override
    public int delDemand(Integer did) {
        return demandMapper.delDemand(did);
    }

    @Override
    public List<Demand> findAllDemand() {
        return demandMapper.findAllDemand();
    }

    @Override
    public List<Demand> findBigHeadAndDemand() {
        return demandMapper.findBigHeadAndDemand();
    }

}
