package com.shopme.admin.user;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.supercsv.io.CsvBeanWriter;
import org.supercsv.io.ICsvBeanWriter;
import org.supercsv.prefs.CsvPreference;

import com.shopme.admin.constant.ExporterConstant;
import com.shopme.common.entity.User;

public class UserCsvExporter extends AbstractExporter {

	public void export(List<User> listUsers, HttpServletResponse response) throws IOException {

		super.setResponseHeader(response, "text/csv", ".csv");

		ICsvBeanWriter csvWriter = new CsvBeanWriter(response.getWriter(), CsvPreference.STANDARD_PREFERENCE);

		// CSV의 헤더라인설정
		csvWriter.writeHeader(ExporterConstant.HeaderArray);

		listUsers.forEach(user -> {
			try {
				csvWriter.write(user, ExporterConstant.FieldMapping);
			} catch (IOException e) {
				e.printStackTrace();
			}
		});

		csvWriter.close();

	}
}
