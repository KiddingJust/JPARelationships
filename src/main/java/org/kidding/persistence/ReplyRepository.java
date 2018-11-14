package org.kidding.persistence;

import org.kidding.domain.FreeReply;
import org.springframework.data.repository.CrudRepository;

public interface ReplyRepository extends CrudRepository<FreeReply, Long> {

}
