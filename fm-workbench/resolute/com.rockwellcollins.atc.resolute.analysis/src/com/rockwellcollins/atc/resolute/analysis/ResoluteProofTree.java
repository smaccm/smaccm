package com.rockwellcollins.atc.resolute.analysis;

import java.util.List;
import java.util.ListIterator;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.xtext.nodemodel.ICompositeNode;
import org.eclipse.xtext.nodemodel.util.NodeModelUtils;

import com.rockwellcollins.atc.resolute.analysis.values.ResoluteValue;
import com.rockwellcollins.atc.resolute.resolute.Arg;
import com.rockwellcollins.atc.resolute.resolute.ClaimBody;
import com.rockwellcollins.atc.resolute.resolute.DefinitionBody;
import com.rockwellcollins.atc.resolute.resolute.FailExpr;
import com.rockwellcollins.atc.resolute.resolute.FunctionDefinition;
import com.rockwellcollins.atc.resolute.resolute.IdExpr;
import com.rockwellcollins.atc.resolute.resolute.ProveStatement;
import com.rockwellcollins.atc.resolute.resolute.QuantifiedExpr;
import com.rockwellcollins.atc.resolute.resolute.ThisExpr;

public class ResoluteProofTree {
	private ResoluteProofNode root;
	private ResoluteProofNode curNode;
	private ProofType proofType;

	public ResoluteProofTree(ProofType proofType) {
		this.proofType = proofType;
	}

	public ResoluteProofNode getCurNode() {
		return curNode;
	}

	public void setCurNode(ResoluteProofNode node) {
		curNode = node;
	}

	public void setRoot(ResoluteProofNode root) {
		this.root = root;
	}

	public void addNewCurrent(EObject eobj, String initString) {
		if (proofType == ProofType.CLAIMS_ONLY || proofType == ProofType.CONFIDENCE) {
			// throw away nodes that are not claims
			if (!(eobj instanceof FunctionDefinition || eobj instanceof ProveStatement || eobj instanceof FailExpr)) {
				return;
			}

			if (eobj instanceof FunctionDefinition) {
				FunctionDefinition fnDef = (FunctionDefinition) eobj;
				DefinitionBody body = fnDef.getBody();

				if (!(body instanceof ClaimBody)) {
					return;
				}
			}
		}

		ResoluteProofNode newNode = new ResoluteProofNode(curNode, eobj);
		newNode.setExprStr(initString);
		if (curNode != null) {
			curNode.addChild(newNode);
		}
		setCurNode(newNode);
	}

	public void setCurReturnVal(EObject obj, String str, ResoluteValue retVal) {
		if (curNode == null || !curNode.getEObject().equals(obj)) {
			assert (proofType == ProofType.CLAIMS_ONLY
					|| proofType == ProofType.CONFIDENCE);
			return;
		}

		curNode.setRetVal(retVal);
		curNode.setExprStr(str);
		setCurNode(curNode.getParent());
	}

	/********** get methods ***************/

	public ResoluteProofNode getRoot() {
		return root;
	}

	/********** utility methods **********/

	public void pruneQuants() {
		assert proofType == ProofType.VERBOSE;
		pruneQuants(root);
	}

	private void pruneQuants(ResoluteProofNode node) {
		EObject eObj = node.getEObject();
		List<ResoluteProofNode> children = node.getChildren();

		if (!(eObj instanceof QuantifiedExpr)) {
			for (ResoluteProofNode child : children) {
				pruneQuants(child);
			}
			return;
		}

		QuantifiedExpr qExpr = (QuantifiedExpr) eObj;
		ResoluteValue res = node.getRetVal();
		assert (res.isBool());

		if (qExpr.getQuant().equals("exists") && res.getBool()) {
			for (int i = 0; i < children.size(); i++) {
				ResoluteProofNode child;
				child = children.get(i);
				res = child.getRetVal();
				assert (res.isBool());

				if (!res.getBool()) {
					children.remove(i);
					i--;
				}
			}
			assert (children.size() == 1);
			pruneQuants(children.get(0));
		}

		for (ResoluteProofNode child : children) {
			pruneQuants(child);
		}

	}

	public void pruneFalseClaims() {
		assert (proofType == ProofType.CLAIMS_ONLY
				|| proofType == ProofType.CONFIDENCE);

		if (!root.getRetVal().isFail()) {
			pruneFalseClaims(root);
		}
	}

	/** Remove false claims that are beneath true claims */
	private void pruneFalseClaims(ResoluteProofNode node) {
		ResoluteValue claimVal = node.getRetVal();
		assert (claimVal.isBool());

		if (!claimVal.getBool()) {
			return;
		}

		ListIterator<ResoluteProofNode> iterator = node.getChildren().listIterator();
		while (iterator.hasNext()) {
			if (!iterator.next().getRetVal().getBool()) {
				iterator.remove();
			}
		}

		for (ResoluteProofNode child : node.getChildren()) {
			pruneFalseClaims(child);
		}
	}
	
	public static String exprToString(EObject obj, ResoluteValue objVal) {
		if (obj instanceof IdExpr || obj instanceof ThisExpr || obj instanceof Arg) {
			return objVal.toString();
		}

		return getExprString(obj);
	}

	private static String getExprString(EObject obj) {
		ICompositeNode compNode = NodeModelUtils.getNode(obj);
		if (compNode != null) {
			return NodeModelUtils.getTokenText(compNode);
		}
		return null;
	}

	public void sortDescendants(ResoluteProofNode node) {
		for (ResoluteProofNode child : node.getChildren()) {
			sortDescendants(child);
			node.setNumDescendants(node.getNumDescendants() + 1 + child.getNumDescendants());
		}

		node.sortChildrenByNumDescendants();
	}

	public void sortDescendants() {
		sortDescendants(root);
	}
}
