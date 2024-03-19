package com.groom.Kkri.repository;

import com.groom.Kkri.entity.Board;
import com.groom.Kkri.enums.Type;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.web.PageableDefault;

import java.util.List;


public interface BoardRepository extends JpaRepository<Board,Long> {

    //페이지와 sort로 변환해야함
    @Query("select b from Board b where b.type = :type and b.state != com.groom.Kkri.enums.State.POST_DEAL")
    Slice<Board> findByType(@Param("type") Type type, Pageable pageable);

    @Query("select b from Board b where b.type = :type and b.state != com.groom.Kkri.enums.State.POST_DEAL")
    Slice<Board> findByBoardLimit5(@Param("type") Type type, Pageable pageable);

    @Query("select b from Board b join fetch b.member m where m.username = :username and b.type = :type")
    Slice<Board> findByUserBoard(@Param("username") String username, @Param("type")Type type, Pageable pageable);

    @Query("select b from Board b where (b.title like %:title% or b.description like %:description%) and b.state != com.groom.Kkri.enums.State.POST_DEAL")
    Slice<Board> findByTitleContainingOrDescriptionContaining(@Param("title") String tile, @Param("description")String description, Pageable pageable);

    void deleteById(Long id);

    List<Board> findByType(Type type);
}
