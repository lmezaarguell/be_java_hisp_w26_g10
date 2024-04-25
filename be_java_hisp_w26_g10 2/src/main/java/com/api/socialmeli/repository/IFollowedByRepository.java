package com.api.socialmeli.repository;

import com.api.socialmeli.entity.FollowedBy;

import java.util.List;
import java.util.Optional;

public interface IFollowedByRepository {
    void save(FollowedBy followedBy);
    List<FollowedBy> getFollowedByList(int seller_id);
    List<FollowedBy> getFollowsOfBuyer(int buyer_id);
    Optional<FollowedBy> getFollowById(int buyer_id, int seller_id);
    void delete(int seller_id, int buyer_id);
}
