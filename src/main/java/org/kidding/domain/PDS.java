package org.kidding.domain;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@ToString(exclude = "files") // 조인되는 건 이렇게 exclude해주는 게 좋음. JPA는 기본적으로 lazyloading이므로
@EqualsAndHashCode(of = "pno")	//equalsandhashcode는 PK 값으로만 잡아주는 것이 정석. 
public class PDS {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long pno;
	private String title;
	private String writer;
	
	//file 여러 개를 잡아야하므로 collection타입이 무난. 편안하게 list로
//	@OneToMany(cascade=CascadeType.ALL, fetch = FetchType.EAGER)
	@OneToMany(cascade=CascadeType.ALL)
	@JoinColumn(name="pdsno")
	private List<PDSFile> files;

}
