package org.anarres.ipmi.protocol;

public enum IanaEnterpriseNumber {
	/** IBM. Kristine Adamson <adamson&us.ibm.com> */
	IBM(2, "IBM"),
	/** Hewlett-Packard. Harry Lynch <harry.lynch&hp.com> */
	Hewlett_Packard(11, "Hewlett-Packard"),
	/** Intel Corporation. Adam Kaminski <adam.kaminski&intel.com> */
	Intel_Corporation(343, "Intel Corporation"),
	/** Dell Inc.. David L. Douglas <david_l_douglas&dell.com> */
	Dell_Inc(674, "Dell Inc."),
	/** Alerting Specifications Forum. Steven Williams <steven.d.williams&intel.com> */
	Alerting_Specifications_Forum(4542, "Alerting Specifications Forum"),
	/** IBM Corporation. Victor Sample <vsample&us.ibm.com> */
	IBM_Corporation(4769, "IBM Corporation"),
	/** IBM eServer X. Lynn Fore <sls&us.ibm.com> */
	IBM_eServer_X(20301, "IBM eServer X"),
	EOD(-1, null);

	private final int number;
	private final String name;

	private IanaEnterpriseNumber(int number, String name) {
		this.number = number;
		this.name = name;
	}

	public int getNumber() {
		return number;
	}

	public String getNanem() {
		return name;
	}
}
