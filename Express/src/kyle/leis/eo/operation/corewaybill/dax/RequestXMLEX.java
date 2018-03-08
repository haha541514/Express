package kyle.leis.eo.operation.corewaybill.dax;

import kyle.common.util.jlang.StringUtility;
import kyle.leis.eo.operation.housewaybill.da.ForinputallColumns;

public abstract class RequestXMLEX extends RequestXML {

	/**
	 * 分割发件人地址
	 * 
	 * @param objFIAColumns
	 * @param addressLen
	 *            单个地址允许的长度
	 * @param expectAddressCount
	 *            期望的地址个数
	 * @return
	 */
	protected String[] splitSAddress(ForinputallColumns objFIAColumns,
			int addressLen, int expectAddressCount) {
		String strAddress = buildAddress(objFIAColumns.getHwshipperaddress1(),
				objFIAColumns.getHwshipperaddress2(), objFIAColumns
						.getHwshipperaddress3());
		return splitAddress(strAddress, addressLen, expectAddressCount);
	}

	/**
	 * 分割收件人地址
	 * 
	 * @param objFIAColumns
	 * @param addressLen
	 *            单个地址允许的长度
	 * @param expectAddressCount
	 *            期望的地址个数
	 * @return
	 */
	protected String[] splitRAddress(ForinputallColumns objFIAColumns,
			int addressLen, int expectAddressCount) {
		String strAddress = buildAddress(
				objFIAColumns.getHwconsigneeaddress1(), objFIAColumns
						.getHwconsigneeaddress2(), objFIAColumns
						.getHwconsigneeaddress3());
		return splitAddress(strAddress, addressLen, expectAddressCount);
	}

	private String buildAddress(String address1, String address2,
			String address3) {
		StringBuilder builder = new StringBuilder();
		if (validateAddress(address1)) {
			builder.append(address1);
		}
		if (validateAddress(address2)) {
			builder.append(" " + address2);
		}
		if (validateAddress(address3)) {
			builder.append(" " + address3);
		}
		return builder.toString().trim();
	}

	private boolean validateAddress(String address) {
		return !StringUtility.isNull(address) && !".".equals(address);
	}

	private String[] splitAddress(String strAddress, int addressLen,
			int expectAddressCount) {
		String[] addresses = splitAddress(strAddress, addressLen);
		if (addresses.length < expectAddressCount) {
			return addresses;
		}
		String[] addResult = new String[expectAddressCount];
		System.arraycopy(addresses, 0, addResult, 0, expectAddressCount);
		return addResult;
	}

	private String[] splitAddress(String strAddress, int addressLength) {
		int size = strAddress.length() / addressLength;
		if (strAddress.length() % addressLength != 0) {
			size++;
		}
		String[] astrAddress = new String[size];
		for (int i = 0; i < size; i++) {
			if (i < size - 1) {
				astrAddress[i] = strAddress.substring(i * addressLength,
						(i + 1) * addressLength);
			} else {
				astrAddress[i] = strAddress.substring(i * addressLength);
			}
		}
		return astrAddress;
	}

}
