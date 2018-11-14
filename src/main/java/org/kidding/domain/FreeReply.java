package org.kidding.domain;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@ToString(exclude="board")
public class FreeReply {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long rno;
	
	private String reply;
	private String replyer;
	
	@CreationTimestamp
	private LocalDateTime replyDate;
	@UpdateTimestamp
	private LocalDateTime replyUpdate;
	
	//댓글로 게시물 검색하는 상황이 있을 수 있으므로
	@ManyToOne
	private FreeBoard board;
}
