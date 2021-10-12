package com.monique.main.service;

import com.monique.common.enums.CommonCode;
import com.monique.domain.Celebration;
import com.monique.main.dto.CelebrationDTO;
import com.monique.main.repository.CelebrationRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class CelebrationService {

    private final CelebrationRepository cbtRepo;

    // 1. main -> All Celebration with paging order by createdDATE
    @Transactional(readOnly = true)
    public Page<Celebration> getAllCelebrationWithPaging(int pageNum)
    {
        return cbtRepo.findAll(PageRequest.of(pageNum==0? 0:pageNum-1, CommonCode.PAGE_POST_COUNT, Sort.by(Sort.Direction.DESC, "createdDate")));
    }

    // 2. Celebration regist
    @Transactional
    public void postCelebration(CelebrationDTO cbtDTO)
    {
        cbtRepo.save(Celebration.builder(cbtDTO).build());
    }


}
