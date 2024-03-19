package com.groom.Kkri.service;

import com.groom.Kkri.enums.State;
import org.springframework.stereotype.Component;

import com.groom.Kkri.entity.Board;
import com.groom.Kkri.enums.Type;
import com.groom.Kkri.repository.BoardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Component
public class BoardService {

    private final BoardRepository boardRepository;

    @Autowired
    public BoardService(BoardRepository boardRepository) {
        this.boardRepository = boardRepository;
    }

    public List<Board> getAllPosts() {
        return boardRepository.findAll();
    }

    public List<Board> getPostsByType(Type type) {
        return boardRepository.findByType(type);
    }

    public Board createPost(Type type, Board board) {
        return boardRepository.save(board);
    }

    public Optional<Board> getPostById(Long boardId) {
        return boardRepository.findById(boardId);
    }

    public Board updatePost(Type type, Long boardId, Board board) {
        return boardRepository.save(board);
    }

    public void deletePost(Type type, Long boardId) {
        boardRepository.deleteById(boardId);
    }

    public void changePostStatus(Long boardId, State newState) {
        Optional<Board> optionalBoard = boardRepository.findById(boardId);
        if (optionalBoard.isPresent()) {
            Board board = optionalBoard.get();
            board.updateState(newState);
            boardRepository.save(board);
        } else {
            throw new IllegalArgumentException("Board with ID " + boardId + " does not exist.");
        }
    }
}
