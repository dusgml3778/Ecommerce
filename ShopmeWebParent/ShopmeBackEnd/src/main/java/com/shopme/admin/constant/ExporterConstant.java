package com.shopme.admin.constant;

public class ExporterConstant {

	public static final String HEADER = "HEADER";
	public static final String DATA = "DATA";
	public static final int FONT_SIZE_14 = 14;
	public static final int FONT_SIZE_16 = 16;
			
	// Excel
	public static final String HEADER_0 = "User_Id";
	public static final String HEADER_1 = "E-mail";
	public static final String HEADER_2 = "First Name";
	public static final String HEADER_3 = "Last Name";
	public static final String HEADER_4 = "Roles";
	public static final String HEADER_5 = "Enabled";

	public static String[] HeaderArray = { HEADER_0, HEADER_1, HEADER_2, HEADER_3, HEADER_4, HEADER_5 };

	// CSV
	public static String[] FieldMapping = { "id", "email", "firstName", "lastName", "roles", "enabled" };

	
	
}
