package com.minws.tps.ctrl;

import cn.dreampie.shiro.hasher.Hasher;
import cn.dreampie.shiro.hasher.HasherInfo;
import cn.dreampie.shiro.hasher.HasherKit;

public class CreatePassword {

	public static void main(String[] args) {
		HasherInfo passwordInfo = HasherKit.hash("123456", Hasher.DEFAULT);
		System.out.println(passwordInfo.getHashResult());
		System.out.println(passwordInfo.getHasher().value());
		System.out.println(passwordInfo.getSalt());
	}

}
