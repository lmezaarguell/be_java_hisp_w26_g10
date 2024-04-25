package com.api.socialmeli.repository.impl;

import com.api.socialmeli.entity.FollowedBy;
import com.api.socialmeli.repository.IFollowedByRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class FollowedByRepositoryImpl implements IFollowedByRepository {
    private List<FollowedBy> followedList;

    public FollowedByRepositoryImpl(){
        followedList = new ArrayList<>();
    }

    @Override
    public void save(FollowedBy followedBy) {
        followedList.add(followedBy);
    }

    @Override
    public Optional<FollowedBy> getFollowById(int buyer_id, int seller_id){
        return followedList.stream()
                .filter(follow ->
                        follow.getBuyer_id() == buyer_id
                        &&
                        follow.getSeller_id() == seller_id
                ).findFirst();
    }

    @Override
    public List<FollowedBy> getFollowedByList(int seller_id) {
        return followedList.stream()
                .filter(follow
                        -> follow.getSeller_id() == seller_id
                ).toList();
    }

    @Override
    public List<FollowedBy> getFollowsOfBuyer(int buyer_id) {
        return followedList.stream()
                .filter(follow
                        -> follow.getBuyer_id() == buyer_id
                ).toList();
    }

    @Override
    public void delete(int seller_id, int buyer_id) {
        List<FollowedBy> updatedFollowers = followedList.stream()
                .filter(follow -> follow.getSeller_id() == seller_id
                &&
                        follow.getBuyer_id() == buyer_id
                ).toList();
        followedList = updatedFollowers;
    }
}
