package com.wk.msg;

import com.wk.conv.PacketChannelBuffer;
import com.wk.conv.config.StructConfig;
import com.wk.conv.mode.JSONPackageMode;
import com.wk.conv.mode.Modes;
import com.wk.conv.mode.PackageMode;
import com.wk.nio.ChannelBuffer;
import com.wk.sdo.ServiceData;

/**
 * @description
 * @author raoliang
 * @version 2015��1��20�� ����12:24:28
 */
public class JSONMsg {
	
	/**
	* @description json���Ĳ��
	* @param buffer
	* @return
	* @author raoliang
	* @version 2015��1��20�� ����12:28:48
	*/
	public static ServiceData unpack(ChannelBuffer buffer) {
		ServiceData data = new ServiceData();
		PackageMode json = Modes.getPackageMode("json");
		StructConfig response = new StructConfig(json, false);
		data = json.unpack(new PacketChannelBuffer(buffer), response, data, buffer.readableBytes());
		return data;
	}
}
