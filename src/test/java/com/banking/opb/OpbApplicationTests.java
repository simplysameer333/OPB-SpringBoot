package com.banking.opb;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import org.apache.commons.io.FileUtils;
import org.junit.jupiter.api.Test;

class OpbApplicationTests {

	@Test
	void contextLoads() throws IOException {
		String filename = "https://drive.google.com/uc?id=1QKsEANKUOsy6xGrVaEuJ34FIXGshuR1Q&export=download";

		File assetsInfo = new File("wallet.zip");
		FileUtils.copyURLToFile(new URL(filename), assetsInfo);

		String zipFilePath = assetsInfo.getAbsolutePath();
		String path = zipFilePath.replace("\\wallet.zip", "");

		String seprator = System.getProperty("file.separator");

		if ("\\".equals(seprator)) {
			path = path.replace("/", seprator);
			zipFilePath = zipFilePath.replace("/", seprator);
		}

		unzip(zipFilePath);
		System.out.println(path + seprator + "wallet");
	}

	public void unzip(String zipName) {
		// String zipName = "data.zip";

		try (FileInputStream fis = new FileInputStream(zipName);
				ZipInputStream zis = new ZipInputStream(new BufferedInputStream(fis))) {

			ZipEntry entry;
			int i = 0;

			// Read each entry from the ZipInputStream until no
			// more entry found indicated by a null return value
			// of the getNextEntry() method.
			while ((entry = zis.getNextEntry()) != null) {
				if (i == 0) {
					File f = new File(entry.getName());
					f.getParentFile().mkdirs();
				}
				System.out.println("Unzipping: " + entry.getName());

				int size;
				byte[] buffer = new byte[2048];

				try (FileOutputStream fos = new FileOutputStream(entry.getName());
						BufferedOutputStream bos = new BufferedOutputStream(fos, buffer.length)) {

					while ((size = zis.read(buffer, 0, buffer.length)) != -1) {
						bos.write(buffer, 0, size);
					}
					bos.flush();
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
