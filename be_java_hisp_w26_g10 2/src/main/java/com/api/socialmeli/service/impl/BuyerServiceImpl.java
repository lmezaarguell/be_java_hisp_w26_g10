package com.api.socialmeli.service.impl;

import com.api.socialmeli.entity.Buyer;
import com.api.socialmeli.repository.IBuyerRepository;
import com.api.socialmeli.service.IBuyerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BuyerServiceImpl implements IBuyerService {

    @Autowired
    private IBuyerRepository repositoryBuyer;

    @Override
    public Buyer getBuyerById(Integer id) {
        return repositoryBuyer.getById(id);
    }
}
