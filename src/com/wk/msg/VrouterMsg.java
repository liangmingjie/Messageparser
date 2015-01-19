package com.wk.msg;

import com.wk.conv.PacketChannelBuffer;
import com.wk.conv.config.StructConfig;
import com.wk.conv.mode.Modes;
import com.wk.conv.mode.PackageMode;
import com.wk.logging.Log;
import com.wk.logging.LogFactory;
import com.wk.nio.ChannelBuffer;
import com.wk.sdo.ServiceData;

/**
 * @description
 * @author raoliang
 * @version 2015��1��20�� ����12:15:23
 */
public class VrouterMsg {
	
	private Log logger = LogFactory.getLog();
	
	/**
	* @description vrouter��׼���Ĳ��
	* @param buffer
	* @return
	* @version 2015��1��20�� ����12:21:06
	*/
	public static ServiceData unpack(ChannelBuffer buffer) {
		ServiceData data = new ServiceData();
		PackageMode vrouter = Modes.getPackageMode("vrouterserver2");
		StructConfig response = new StructConfig(vrouter, false);
		data = vrouter.unpack(new PacketChannelBuffer(buffer), response, data, buffer.readableBytes());
		return data;
	}
}
