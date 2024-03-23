package com.groom.Kkri.service;

import com.groom.Kkri.dto.attach.AttachmentOutputDto;
import com.groom.Kkri.dto.attach.AttachmentUpdateDto;
import com.groom.Kkri.dto.board.*;
import com.groom.Kkri.entity.Member;
import com.groom.Kkri.enums.State;
import com.groom.Kkri.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;

import com.groom.Kkri.entity.Board;
import com.groom.Kkri.enums.Type;
import com.groom.Kkri.repository.BoardRepository;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;


@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class BoardService {

    private final BoardRepository boardRepository;
    private final AttachmentService attachmentService;
    private final MemberRepository memberRepository;

    public BoardPageDto getBoard(Type type, Pageable pageable){

        Slice<Board> slice = boardRepository.findByType(type, pageable);
        return getBoardPageDto(slice);
    }



    public List<BoardHomeDto> getHomeBoard(Type type, Pageable pageable) {
        List<BoardHomeDto> result = new ArrayList<>();

        for (Board board : boardRepository.findByBoardLimit5(type, pageable).getContent()) {
            result.add(new BoardHomeDto(board));
        }
        return result;
    }

    public BoardPageDto getBoardSearch(String title, String description, Pageable pageable){
        //return new BoardPageDto(boardRepository.findByTitleContainingOrDescriptionContaining(title, description, pageable));

        Slice<Board> slice = boardRepository.findByTitleContainingOrDescriptionContaining(title, description, pageable);
        return getBoardPageDto(slice);
    }

    public BoardPageDto getBoardUser(Long userId, Type type , Pageable pageable){
        Slice<Board> slice = boardRepository.findByUserBoard(userId, type, pageable);
        return getBoardPageDto(slice);
        //return new BoardPageDto(boardRepository.findByUserBoard(userId, type, pageable));
    }

    public BoardDetailDto getBoard(Long boardId){
        Board board = boardRepository.findById(boardId).get();
        List<AttachmentOutputDto> images = attachmentService.getImages(boardId);
        return new BoardDetailDto(board,images);
    }

    @Transactional
    public void deletePost(Long boardId) {
        boardRepository.deleteById(boardId);
    }

    @Transactional
    public void updateBoard(Long boardId, BoardUpdateDto boardUpdateDto, Map<String,MultipartFile> images) throws IOException {
        Board board = boardRepository.findById(boardId).get();

        board.updateBoard(boardUpdateDto.getTitle(),boardUpdateDto.getDescription(),boardUpdateDto.getExchangePoint());

        attachmentService.updateImages(images);

    }

    @Transactional
    public void createBoard(Long userId, BoardCreateDto boardCreateDto, List<MultipartFile> images) throws IOException {
        Member member = memberRepository.findById(userId).get();

        Board board = boardCreateDto.createBoard(member);
        Board save = boardRepository.save(board);

        attachmentService.storeImages(images, save.getId());
    }

    private BoardPageDto getBoardPageDto(Slice<Board> slice) {
        List<BoardIndividualDto> boardIndividualDtos = new ArrayList<>();
        for (Board board : slice.getContent() ) {
            AttachmentOutputDto image = attachmentService.getImage(board.getId());
            boardIndividualDtos.add(new BoardIndividualDto(board, image));
        }

        return new BoardPageDto(boardIndividualDtos, slice.getNumber());
    }

}
