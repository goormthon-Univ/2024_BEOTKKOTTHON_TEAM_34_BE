package com.groom.Kkri.controller;

import com.groom.Kkri.dto.BoardPageDto;
import com.groom.Kkri.entity.Board;
import com.groom.Kkri.enums.State;
import com.groom.Kkri.enums.Type;
import com.groom.Kkri.service.BoardService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/board")
@RequiredArgsConstructor
@Tag(name = "board")
public class BoardController {

    private final BoardService boardService;

    @Operation(summary = "게시판을 불러오는 api", description = "")
    @GetMapping("/{type}")
    public ResponseEntity<BoardPageDto> getBoard(@PathVariable("type") Type type, @PageableDefault(size = 5, sort = "createdDate",
            direction = Sort.Direction.DESC)Pageable pageable)
    {
        return ResponseEntity.ok(boardService.getBoard(type,pageable));
    }
    @Operation(summary = "게시글 작성 api", description = "")
    @PostMapping("/{type}")
    public ResponseEntity<Board> writePost(@PathVariable Type type, @RequestBody Board board) {
        Board newPost = boardService.createPost(type, board);
        return ResponseEntity.status(HttpStatus.CREATED).body(newPost);
    }

    @Operation(summary = "게시글 수정 api", description = "")
    @PutMapping("/{type}/boardno/{boardId}")
    public ResponseEntity<Board> editPost(
            @PathVariable Type type,
            @PathVariable Long boardId,
            @RequestBody Board updatedBoard)
    {
        Board editedPost = boardService.updatePost(type, boardId, updatedBoard);
        return ResponseEntity.ok(editedPost);
    }

    @Operation(summary = "게시글 삭제 api", description = "")
    @DeleteMapping("/{type}/boardno/{boardId}")
    public ResponseEntity<Board> deletePost(
            @PathVariable Type type,
            @PathVariable Long boardId)
    {
        boardService.deletePost(type, boardId);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "거래 상태 변경 api", description = "")
    @GetMapping("/{boardId}/status")
    public ResponseEntity<Board> changeTransactionStatus(
            @PathVariable Long boardId,
            @RequestParam State newState)
    {
        boardService.changePostStatus(boardId, newState);
        return ResponseEntity.ok().build();
    }

}
