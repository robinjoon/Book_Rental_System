package dao;

import dto.*;
import java.util.*;

public interface MemberDao {
	
	boolean insertMember(Member member);
	Member selectMember(String userID);
	boolean updateMember(Member member);
	List<Member> selectMembers();
	boolean deleteMember(String userID);
	Member loginMember(String userID, String pw);
}
