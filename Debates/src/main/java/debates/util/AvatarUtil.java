package debates.util;

import java.io.File;
import java.io.FileInputStream;

import debates.Constants;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class AvatarUtil {
	
	public static String getUserAvatarFileName(Long userId) {
		File avatar = new File(Constants.AVATARS_FOLDER_PATH + userId + ".jpg");
		if (avatar.exists()) {
			return userId + ".jpg";
		}
		else {
			return "default.jpg";
		}
	}
}
