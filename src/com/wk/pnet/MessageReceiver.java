package com.wk.pnet;

import com.wk.actor.Actor;
import com.wk.msg.MsgClz;
import com.wk.net.ChannelBufferMsg;
import com.wk.net.CommManagers;
import com.wk.net.Request;
import com.wk.net.ServerCommManager;
import com.wk.nio.ChannelBuffer;

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
		return CommManagers.getServerCommManager(serverCommName, MsgClz.class, ReqActor.class);
	}
	
}
class ReqActor<T extends MsgClz> extends Actor<Request<T>> {
	
	@Override
	protected void act(Request<T> request) {
		T msg = request.getRequestMsg();
		System.out.println(msg);
//		request.doResponse((T)new ChannelBufferMsg(msg));;
	}
}
