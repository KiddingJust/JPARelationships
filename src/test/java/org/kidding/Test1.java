package org.kidding;

import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.kidding.domain.Member;
import org.kidding.domain.Profile;
import org.kidding.persistence.MemberRepository;
import org.kidding.persistence.ProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import lombok.Setter;
import lombok.extern.java.Log;

@RunWith(SpringRunner.class)
@SpringBootTest
@Log
public class Test1 {
	
	@Setter(onMethod_=@Autowired)
	private MemberRepository memberRepository;
	
	@Setter(onMethod_=@Autowired)
	private ProfileRepository profileRepository;
	
	
	@Test
	public void testFetchJoin1() {
		List<Object[]> result = 
				memberRepository.getMemberWithProfileCount("user1");
		result.forEach(arr ->
		System.out.println(Arrays.toString(arr)));
	}
	
	@Test
	public void testDelete() {
		memberRepository.deleteById("user1");
	}
	
	@Test
	public void testRead() {
		
		//if present 주는 것. optional. 
		profileRepository.findById(1L).ifPresent(profile -> {
			log.info("" + profile);
		});
		
	}
	
	@Test
	public void testInsertMembers() {

		IntStream.range(1, 101).forEach(i -> {
			Member member = new Member();
			member.setUid("user" + i);
			member.setUpw("pw" + i);
			member.setUname("사용자" + i);

			memberRepository.save(member);

		});

	}// end testInsertMember
	
	@Test
	public void testInsertProfile() {

		Member member = new Member();
		member.setUid("user1");

		for (int i = 1; i < 5; i++) {

			Profile profile1 = new Profile();
			profile1.setFname("face" + i + ".jpg");

			if (i == 1) {
				profile1.setCurrent(true);
			}

			profile1.setMember(member);

			profileRepository.save(profile1);

		}
	}// end testInsertProfile
}
