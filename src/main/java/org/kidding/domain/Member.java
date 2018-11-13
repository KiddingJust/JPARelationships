package org.kidding.domain;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Data;

//따로 테이블 주지 않았으므로, Member가 테이블 이름이 되어 생성됨
@Entity
@Data
public class Member {

	@Id
	private String uid;
	
	private String upw;
	
	private String uname;

}
