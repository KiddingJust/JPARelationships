package org.kidding.persistence;

import java.util.List;

import org.kidding.domain.FreeBoard;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface FreeBoardRepository extends CrudRepository<FreeBoard, Long> {

	@Query("select b from FreeBoard b where b.bno > 0")
	public List<FreeBoard> fetchList(Pageable pageable);

	@Query("select b, count(r) from FreeBoard b "
			+ "left outer join FreeReply r on b.bno = r.board "
			+ "group by b")
	public Page<Object[]> listPage(Pageable pageable);

	//제목으로 검색하도로고 
	@Query("select b, count(r) from FreeBoard b "
			+ "left outer join FreeReply r on b.bno = r.board "
			+ "where b.title like %?1% "
			+ "group by b")
	public Page<Object[]> listByTitlePage(String keyword, Pageable pageable);
	
	//where 에서는 괄호를 통해 우선순위를 따지는 게 좋음. and가 or보다 우선시되는 것이 default. 
	@Query("select b, count(r) from FreeBoard b "
			+ "left outer join FreeReply r on b.bno = r.board "
			+ "where (b.title like %?1% OR b.content like %?1%) "
			+ "group by b")	
	public Page<Object[]> listByTitleOrContentPage(String keyword, Pageable pageable);

}
