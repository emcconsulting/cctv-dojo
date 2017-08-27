package com.dojo.review_service.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.dojo.review_service.entity.ReviewEntity;

@RepositoryRestResource(collectionResourceRel = "myreviews", path = "reviews")
public interface ReviewRepository extends CrudRepository<ReviewEntity, Long> {

}
