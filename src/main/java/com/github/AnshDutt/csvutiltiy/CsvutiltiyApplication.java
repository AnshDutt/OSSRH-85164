package com.github.AnshDutt.csvutiltiy;

import com.github.AnshDutt.csvutiltiy.util.CsvUtil;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CsvutiltiyApplication {

	public static void main(String[] args) throws Exception {
		SpringApplication.run(CsvutiltiyApplication.class, args);
		CsvUtil.jsonCsvWriterAtm("atm");
	}

}
