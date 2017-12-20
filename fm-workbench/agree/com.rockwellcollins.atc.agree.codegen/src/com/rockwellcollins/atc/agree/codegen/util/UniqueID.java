package com.rockwellcollins.atc.agree.codegen.util;

import jkind.Assert;

/**
 *
 * UniqueID class contains the variable/function ID and its record type if applicable
 * This is used in ensuring unique names are created during the translation process.
 *
 */
public class UniqueID {

	public final String id;
	public final String recordId;

	public UniqueID(String id, String recordId) {
		Assert.isNotNull(id);
		Assert.isNotNull(recordId);
		this.id = id;
		this.recordId = recordId;
	}

	@Override
	public String toString() {
		return id + " : " + recordId + "\n";
	}

	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof UniqueID)) {
			return false;
		} else {
			UniqueID otherID = (UniqueID) obj;
			if (id.equals(otherID.id) && recordId.equals(otherID.recordId)) {
				return true;
			} else {
				return false;
			}
		}
	}

	@Override
	public int hashCode() {
		int hash = id.hashCode();
		hash = hash * 31 + recordId.hashCode();
		return hash;
	}

}
