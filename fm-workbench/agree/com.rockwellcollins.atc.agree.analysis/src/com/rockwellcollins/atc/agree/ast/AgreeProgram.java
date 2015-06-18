package com.rockwellcollins.atc.agree.ast;

import java.util.List;

import jkind.lustre.Node;
import jkind.lustre.Type;

public class AgreeProgram {
	public final List<AgreeNode> agreeNodes;
	public final List<Node> globalLustreNodes;
	public final List<Type> globalTypes;
	public final AgreeNode topNode;
	
	public AgreeProgram(List<AgreeNode> agreeNodes, List<Node> globalLustreNodes,
			List<Type> globalTypes, AgreeNode topNode){
		this.agreeNodes = agreeNodes;
		this.globalLustreNodes = globalLustreNodes;
		this.globalTypes = globalTypes;
		this.topNode = topNode;
	}

}
