package org.kidding;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.kidding.domain.PDS;
import org.kidding.domain.PDSFile;
import org.kidding.persistence.PDSRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.test.context.junit4.SpringRunner;

import lombok.Setter;
import lombok.extern.java.Log;

@RunWith(SpringRunner.class)
@SpringBootTest
@Log
@Commit
public class Test2 {

	@Setter(onMethod_=@Autowired)
	private PDSRepository pdsRepository;
	
	@Test
	public void testInsert() {
		
		PDS vo = new PDS();
		vo.setTitle("sample PDS");
		vo.setWriter("user00");
		
		List<PDSFile> list = IntStream.range(0, 3).mapToObj(i -> {
			
			PDSFile fileObj = new PDSFile();
			fileObj.setFname("파일이름" + i);
			
			return fileObj;
		}).collect(Collectors.toList());
		
		vo.setFiles(list);
		log.info("" + vo);
		
		pdsRepository.save(vo);
	}
	
	@Test
	public void testRead() {
		
		//Optional이므로 람다식으로 처리
		pdsRepository.findById(1L).ifPresent(vo -> log.info("" + vo));;
		
	}
	
	//3번 글 수정해볼 것
	@Transactional
	@Test
	public void testUpdateFile() {
		
		pdsRepository.updatePDSFile(3L, "새로운 파일이름");
		
	}
	
	@Transactional
	@Test
	public void testDeleteFile() {
		
		pdsRepository.deletePDSFile(3L);
		
	}
	
	@Test
	public void testOldway() {
		PDS pds = pdsRepository.findById(1L).get();
		//첨부파일 2번 수정할겨
		List<PDSFile> files = pds.getFiles();
		
		log.info("" + files);
		
		PDSFile newFile = new PDSFile();
		newFile.setFno(2L);
		newFile.setFname("갱신된 파일");
		
		int idx = files.indexOf(newFile);
		log.info("idx: " + idx);
	
		if (idx >= 0) {
			files.remove(idx);
			files.add(newFile);
		}
		
		pds.setFiles(files);
		pdsRepository.save(pds);
	}
	
	
}
