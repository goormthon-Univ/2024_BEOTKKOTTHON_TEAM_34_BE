package com.groom.Kkri.controller;

import com.groom.Kkri.entity.Board;
import com.groom.Kkri.enums.State;
import com.groom.Kkri.enums.Type;
import com.groom.Kkri.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/board")
public class BoardController {

    private final BoardService boardService;

    @Autowired
    public BoardController(BoardService boardService) {
        this.boardService = boardService;
    }

    // GET endpoint to retrieve the list of posts with optional search parameters
    @GetMapping
    public ResponseEntity<List<Board>> getPostList() {
        List<Board> postList = boardService.getAllPosts();
        return ResponseEntity.ok(postList);
    }

    // GET endpoint to view the list of posts by bulletin board type
    @GetMapping("/{type}")
    public ResponseEntity<List<Board>> getPostListByType(@PathVariable Type type) {
        List<Board> postList = boardService.getPostsByType(type);
        return ResponseEntity.ok(postList);
    }

    // POST endpoint to write a new post
    @PostMapping("/{type}")
    public ResponseEntity<Board> writePost(@PathVariable Type type, @RequestBody Board board) {
        Board newPost = boardService.createPost(type, board);
        return ResponseEntity.status(HttpStatus.CREATED).body(newPost);
    }

    // PATCH endpoint to edit a post
    @PutMapping("/{type}/boardno/{boardId}")
    public ResponseEntity<Board> editPost(@PathVariable Type type, @PathVariable Long boardId, @RequestBody Board updatedBoard) {
        Board editedPost = boardService.updatePost(type, boardId, updatedBoard);
        return ResponseEntity.ok(editedPost);
    }

    // DELETE endpoint to delete a post
    @DeleteMapping("/{type}/boardno/{boardId}")
    public ResponseEntity<Void> deletePost(@PathVariable Type type, @PathVariable Long boardId) {
        boardService.deletePost(type, boardId);
        return ResponseEntity.noContent().build();
    }

    // GET endpoint to change transaction status
    @GetMapping("/{boardId}/status")
    public ResponseEntity<String> changeTransactionStatus(@PathVariable Long boardId, @RequestParam State newState) {
        boardService.changePostStatus(boardId, newState);
        return ResponseEntity.ok("Transaction status changed successfully.");
    }
}
