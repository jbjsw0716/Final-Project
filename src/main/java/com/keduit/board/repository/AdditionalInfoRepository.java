package com.keduit.board.repository;

import com.keduit.board.entity.AdditionalInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdditionalInfoRepository extends JpaRepository<AdditionalInfo, Long> {
    AdditionalInfo findByMovieCode(String movieCode);
//    AdditionalInfo findByAdditionalInfoId(Long additionalInfoId);

}
