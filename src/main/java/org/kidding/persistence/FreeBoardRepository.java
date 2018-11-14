package org.kidding.persistence;

import java.util.List;

import org.kidding.domain.FreeBoard;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface FreeBoardRepository extends CrudRepository<FreeBoard, Long> {

	@Query("select b from FreeBoard b where b.bno > 0")
	public List<FreeBoard> fetchList(Pageable pageable);
}
