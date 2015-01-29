package com.linda.framework.rpc.filter;

import org.apache.log4j.Logger;

import com.linda.framework.rpc.RemoteCall;
import com.linda.framework.rpc.RpcObject;
import com.linda.framework.rpc.net.RpcSender;

/**
 * 访问日志，方便agent做日志分析统计访问次数，给manager做负载均衡
 * @author linda
 *
 */
public class SimpleLogFilter implements RpcFilter{
	
	private Logger logger = Logger.getLogger("rpcstream");
	
	private String logFormat = "{\"host\":\"%s\",\"port\":%d,\"service\":\"%s\",\"version\":\"%s\",\"method\":\"%s\"}";
	
	private String genLogMessage(String host,int port,String service,String version,String method){
		return String.format(logFormat, host,port,service,version,method);
	}
	
	@Override
	public void doFilter(RpcObject rpc, RemoteCall call, RpcSender sender,
			RpcFilterChain chain) {
		logger.info(this.genLogMessage(rpc.getHost(), rpc.getPort(), call.getService(), call.getVersion(), call.getMethod()));
		chain.nextFilter(rpc, call, sender);
	}
	
}
