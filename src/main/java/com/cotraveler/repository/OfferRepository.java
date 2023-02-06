/*
 * Copyright (c)  Aleksey Plekhanov 03.02.2023, 03:14
 */

package com.cotraveler.repository;

import com.cotraveler.entities.Offer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Repository
public interface OfferRepository extends JpaRepository<Offer, Long> {

    List<Offer> findByFromLabelNameAndFinishTimeNull(String fromLabelName);

    List<Offer> findByClientIdAndFinishTimeNull(String clientId);

    int countByFromLabelNameAndFinishTimeNotNull(String fromLabelName);

    int countByClientIdAndCreateTimeGreaterThan(String fromLabelName, Date timestamp);

    @Transactional
    void deleteByCreateTimeLessThan(Date timestamp);

    List<Offer> findByFinishTimeNullAndCreateTimeLessThan(Date timestamp);

    int countByFinishTimeNull();

}
