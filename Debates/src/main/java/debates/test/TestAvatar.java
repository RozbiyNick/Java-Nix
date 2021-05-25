package debates.test;

import debates.util.AvatarUtil;

public class TestAvatar {
	
	public static void main(String[] args) {
		String src_exist = AvatarUtil.getUserAvatarFileName(22l);
		System.out.println(src_exist);
		String src_not_exist = AvatarUtil.getUserAvatarFileName(300l);
		System.out.println(src_not_exist);
	}
}
