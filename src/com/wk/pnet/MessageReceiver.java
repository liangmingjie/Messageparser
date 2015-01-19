package com.wk.pnet;

import java.util.HashMap;
import java.util.Map;

import com.wk.actor.Actor;
import com.wk.actor.Actors;
import com.wk.conv.PacketChannelBuffer;
import com.wk.conv.config.FieldConfig;
import com.wk.conv.config.StructConfig;
import com.wk.conv.converter.DefaultConverter;
import com.wk.conv.mode.DefaultPackageMode;
import com.wk.conv.mode.FieldMode;
import com.wk.conv.mode.Modes;
import com.wk.conv.mode.PackageMode;
import com.wk.conv.mode.VRouterStandardPackageMode;
import com.wk.eai.actor.ServerActor;
import com.wk.eai.config.BufferServerConfig;
import com.wk.eai.config.PackageConfig;
import com.wk.lang.Sync;
import com.wk.net.ChannelBufferMsg;
import com.wk.net.CommManagers;
import com.wk.net.Request;
import com.wk.net.ServerCommManager;
import com.wk.nio.ChannelBuffer;
import com.wk.sdo.FieldType;
import com.wk.sdo.ServiceData;

/**
 * @description
 * @author raoliang
 * @version 2015年1月16日 下午7:48:05
 */
public class MessageReceiver {
	public static void main(String[] args) throws Exception {
		new MessageReceiver();
		Thread.sleep(Integer.MAX_VALUE);
		System.out.println("listening");
	}
	private static final String serverCommName = "MPserver";
	
	private ServerCommManager server;
	
	public MessageReceiver(){
		this.server = getServerCommManager();
	}
	
	private ServerCommManager getServerCommManager(){
		return CommManagers.getServerCommManager(serverCommName, ChannelBufferMsg.class, ReqActor.class);
	}
	
}
class ReqActor<T extends ChannelBufferMsg> extends Actor<Request<T>> {
	
	@Override
	protected void act(Request<T> request) {
		VRouterStandardPackageMode vrouter = new VRouterStandardPackageMode("vrouter", false);
		FieldMode str_ebcd = Modes.getFieldMode("strEBCD");
		StructConfig response = new StructConfig(vrouter, false);
//		response.putChild(new FieldConfig("I1SBNO", FieldType.FIELD_STRING, 10, str_ebcd));
//		response.putChild(new FieldConfig("I1WSNO", FieldType.FIELD_STRING, 0, str_ebcd));
//		response.putChild(new FieldConfig("I1AUUS", FieldType.FIELD_STRING, 0, str_ebcd));
//		response.putChild(new FieldConfig("I1TRCD", FieldType.FIELD_STRING, 4, str_ebcd));
//		response.putChild(new FieldConfig("I1NGAM", FieldType.FIELD_STRING, 0, str_ebcd));
//		response.putChild(new FieldConfig("I1AUPS", FieldType.FIELD_STRING, 0, str_ebcd));
//		response.putChild(new FieldConfig("I1ECRS", FieldType.FIELD_STRING, 0, str_ebcd));
//		response.putChild(new FieldConfig("I1ORTS", FieldType.FIELD_STRING, 10, str_ebcd));
//		response.putChild(new FieldConfig("I1YSQM", FieldType.FIELD_STRING, 0, str_ebcd));
//		response.putChild(new FieldConfig("I1TRAM", FieldType.FIELD_STRING, 0, str_ebcd));
//		response.putChild(new FieldConfig("I1CZRQ", FieldType.FIELD_STRING, 8, str_ebcd));
//		response.putChild(new FieldConfig("I1USID", FieldType.FIELD_STRING, 6, str_ebcd));
		ChannelBuffer buffer = request.getRequestMsg().toChannelBuffer();
		ServiceData data = new ServiceData();
		System.out.println(buffer.toHexString());
//		VRouterStandardPackageMode vmode = new VRouterStandardPackageMode("test");
		data = vrouter.unpack(new PacketChannelBuffer(buffer), response, data, buffer.readableBytes());
//		vmode.unpack(new PacketChannelBuffer(buffer), config, data, buffer.readableBytes());
		System.out.println(data);
		request.doResponse((T)new ChannelBufferMsg(buffer));
	}
}
