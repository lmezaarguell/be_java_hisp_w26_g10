package com.api.socialmeli.repository.impl;

import com.api.socialmeli.entity.FollowedBy;
import com.api.socialmeli.repository.IFollowedByRepository;

import java.util.List;

public class FollowedByRepositoryImpl implements IFollowedByRepository {
    @Override
    public void save(FollowedBy followedBy) {

    }

    @Override
    public List<FollowedBy> getFollowedByList(int seller_id) {
        return null;
    }

    @Override
    public List<FollowedBy> getFollowsOfBuyer(int buyer_id) {
        return null;
    }

    @Override
    public void delete(int seller_id, int buyer_id) {

    }
}
