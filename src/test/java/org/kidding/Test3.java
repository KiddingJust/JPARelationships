package org.kidding;

import java.util.List;
import java.util.stream.IntStream;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.kidding.domain.FreeBoard;
import org.kidding.domain.FreeReply;
import org.kidding.persistence.FreeBoardRepository;
import org.kidding.persistence.ReplyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.test.annotation.Commit;
import org.springframework.test.context.junit4.SpringRunner;

import lombok.Setter;
import lombok.extern.java.Log;

@RunWith(SpringRunner.class)
@SpringBootTest
@Log
@Commit
public class Test3 {

	@Setter(onMethod_= @Autowired)
	private FreeBoardRepository boardRepository;
	
	@Setter(onMethod_= @Autowired)
	private ReplyRepository replyRepository;
	
	@Test
	public void testList() {
		
		List<FreeBoard> list = boardRepository.fetchList(
				PageRequest.of(0,  10, Sort.DEFAULT_DIRECTION.DESC, "bno"));
	
		list.stream().forEach(vo -> {
			
			log.info(vo.getBno() + ":" + vo.getTitle());
		});
	}
	
	
	@Test
	public void insertFreeBoard() {
		
		IntStream.range(1, 300).forEach(i -> {
			
			FreeBoard board = new FreeBoard();
			board.setTitle("gaiga...." + i);
			board.setContent("what are you doing" + i);
			board.setWriter("who am i" + (i % 10));
			
			boardRepository.save(board);
			
		});
		
	}
	
	@Test
	public void insertReply() {
		
		FreeReply reply = new FreeReply();
		reply.setReply("ㅎㅇㅎㅇ 방가방가");
		reply.setReplyer("replyer1");		
		
		FreeBoard board = new FreeBoard();
		board.setBno(299L);
		
		reply.setBoard(board);
		
		replyRepository.save(reply);
	}
	
}
