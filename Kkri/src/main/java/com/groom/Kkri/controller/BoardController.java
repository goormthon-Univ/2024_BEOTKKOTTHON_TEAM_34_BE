package com.groom.Kkri.controller;

import com.groom.Kkri.dto.BaseResponse;
import com.groom.Kkri.dto.attach.AttachmentUpdateDto;
import com.groom.Kkri.dto.board.*;
import com.groom.Kkri.entity.Board;
import com.groom.Kkri.enums.State;
import com.groom.Kkri.enums.Type;
import com.groom.Kkri.service.AttachmentService;
import com.groom.Kkri.service.BoardService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.Param;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/board")
@RequiredArgsConstructor
@Tag(name = "board")
public class BoardController {

    private final BoardService boardService;
    private final AttachmentService attachmentService;

    @Operation(summary = "게시판을 불러오는 api", description = "")
    @GetMapping("/{type}")
    public BaseResponse<?> getBoard(@PathVariable("type") Type type, @PageableDefault(size = 7, sort = "createdDate",
            direction = Sort.Direction.DESC)Pageable pageable)
    {
        return BaseResponse.response(boardService.getBoard(type,pageable));
    }

    @Operation(summary = "홈 화면에서 게시판을 불러오는 api")
    @GetMapping("/home/{type}")
    public BaseResponse<List<BoardHomeDto>> getHomeBoard(@PathVariable("type") Type type, @PageableDefault(size = 5, sort = "createdDate",
            direction = Sort.Direction.DESC)Pageable pageable){

        return BaseResponse.response(boardService.getHomeBoard(type, pageable));
    }

    @Operation(summary = "검색으로 게시판을 가져오는 api")
    @GetMapping("/search")
    public BaseResponse<?> getSearchBoard(@RequestParam("title")String title, @RequestParam("description") String description,
                   @PageableDefault(size = 7, sort = "createdDate", direction = Sort.Direction.DESC)Pageable pageable){
        return BaseResponse.response(boardService.getBoardSearch(title, description, pageable));
    }

    @Operation(summary = "내가 쓴 게시판을 가져오는 api")
    @GetMapping("/user/{userId}")
    public BaseResponse<?> getUserBoard(@RequestParam("type") Type type, @PageableDefault(size = 7, sort = "createdDate",
            direction = Sort.Direction.DESC)Pageable pageable, @PathVariable("userId") Long userId){

        return BaseResponse.response(boardService.getBoardUser(userId, type, pageable));
    }

    @Operation(summary = "상세게시판을 보여주는 api")
    @GetMapping("detail/{boardId}")
    public BaseResponse<BoardDetailDto> getDetailBoard(@PathVariable("boardId") Long boardId){
        return BaseResponse.response(boardService.getBoard(boardId));
    }

    @Operation(summary = "게시판 수정 api")
    @PutMapping(value = "/{boardId}",consumes = { MediaType.MULTIPART_FORM_DATA_VALUE })
    public BaseResponse<?> updateBoard(@PathVariable("boardId")Long boardId, @RequestPart(value = "boardUpdateDto") BoardUpdateDto boardUpdateDto,@RequestPart(value = "images") List<AttachmentUpdateDto> images) throws IOException {
        //boardService.updateBoard(boardId,boardUpdateDto,images);
        return BaseResponse.response("");
    }


    @Operation(summary = "게시판 생성 api")
    @PostMapping(value = "/{userId}",consumes = { MediaType.MULTIPART_FORM_DATA_VALUE })
    public BaseResponse<?> createBoard(@PathVariable("userId")Long userId,@RequestPart(value = "boardCreateDto")BoardCreateDto boardCreateDto,  @RequestPart(value = "images") List<MultipartFile> images) throws IOException {
        boardService.createBoard(userId,boardCreateDto,images);
        return BaseResponse.response("");
    }

    @Operation(summary = "게시판 삭제 api")
    @DeleteMapping("/{boardId}")
    public BaseResponse<?> deleteBoard(@PathVariable("boardId") Long boardId){
        boardService.deletePost(boardId);
        return BaseResponse.response("");
    }


}
