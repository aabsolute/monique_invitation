package com.monique.board.service;


import com.monique.board.repository.BoardRepository;
import com.monique.common.enums.CommonCode;
import com.monique.domain.Board;
import com.monique.domain.Gallery;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@RequiredArgsConstructor
@Service
public class BoardService {

    private final BoardRepository boardRepo;

    @Autowired
    ModelMapper modelMapper;

    // 1. admin galleryList -> All gallery with paging order by createdDATE
    @Transactional(readOnly = true)
    public Page<Board> getAllBoardWithPaging(int pageNum)
    {
        return boardRepo.findAll(PageRequest.of(pageNum==0? 0:pageNum-1, CommonCode.PAGE_POST_COUNT, Sort.by(Sort.Direction.DESC, "createdDate")));
    }
}
