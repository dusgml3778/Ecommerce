package com.shopme.admin.user;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.shopme.admin.constant.ExporterConstant;
import com.shopme.common.entity.User;

public class UserExcelExporter extends AbstractExporter {

	private XSSFWorkbook workbook;
	private XSSFSheet sheet;

	public UserExcelExporter() {
		workbook = new XSSFWorkbook();
	}

	// 엑셀 헤더 작성
	private void writeHeaderLine() {

		sheet = workbook.createSheet("Users");
		XSSFRow row = sheet.createRow(0);

		XSSFCellStyle cellStyle = setExcel(ExporterConstant.HEADER, ExporterConstant.FONT_SIZE_16);

		for (int columnIndex = 0; columnIndex < ExporterConstant.HeaderArray.length; columnIndex++) {

			this.createCell(row, columnIndex, ExporterConstant.HeaderArray[columnIndex], cellStyle);
		}

	}

	// 엑셀 환경설정 글씨 싸이즈 글씨 굴기 등
	private XSSFCellStyle setExcel(String keyword, int fontSize16) {
		XSSFCellStyle cellStyle = workbook.createCellStyle();
		XSSFFont font = workbook.createFont();

		if (keyword.equals(ExporterConstant.HEADER)) {

			font.setBold(true);
		}

		font.setFontHeight(fontSize16);
		cellStyle.setFont(font);
		return cellStyle;
	}

	// 셀에 데이터 매핑
	private void createCell(XSSFRow row, int columnIndex, Object value, CellStyle style) {

		XSSFCell cell = row.createCell(columnIndex);
		sheet.autoSizeColumn(columnIndex);

		if (value instanceof Integer) {

			cell.setCellValue((Integer) value);

		} else if (value instanceof Boolean) {
			cell.setCellValue((Boolean) value);
		} else {
			cell.setCellValue((String) value);
		}

		cell.setCellStyle(style);

	}

	// 엑셀 추출
	public void export(List<User> listUsers, HttpServletResponse response) throws IOException {
		super.setResponseHeader(response, "application/octet-stream", ".xlsx");

		writeHeaderLine();
		writeDataLine(listUsers);

		ServletOutputStream outputStream = response.getOutputStream();
		workbook.write(outputStream);
		workbook.close();
		outputStream.close();

	}

	// 데이터라인에 값 할당
	private void writeDataLine(List<User> listUsers) {
		int rowIndex = 1;

		XSSFCellStyle cellStyle = setExcel(ExporterConstant.DATA, ExporterConstant.FONT_SIZE_14);

		for (User user : listUsers) {
			XSSFRow row = sheet.createRow(rowIndex++);
			int columnIndex = 0;

			createCell(row, columnIndex++, user.getId(), cellStyle);
			createCell(row, columnIndex++, user.getEmail(), cellStyle);
			createCell(row, columnIndex++, user.getFirstName(), cellStyle);
			createCell(row, columnIndex++, user.getLastName(), cellStyle);
			createCell(row, columnIndex++, user.getRoles().toString(), cellStyle);
			createCell(row, columnIndex++, user.isEnabled(), cellStyle);
		}

	}
}
