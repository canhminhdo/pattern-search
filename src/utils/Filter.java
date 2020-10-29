package utils;

import java.io.File;
import java.util.HashSet;
import java.util.Set;

public class Filter {
	
	private String prefixPath;
	private Set<String> sourceFiles;
	
	public Filter() {
		this.sourceFiles = new HashSet<>();
	}
	
	public void createFilePathFilter() {
		String path = System.getProperty("user.dir");
		String fileSeparator = File.separator;
		File exampleDir = new File(path + fileSeparator + "examples");
		this.prefixPath = exampleDir.getPath() + fileSeparator;
		searchFiles(exampleDir);
	}

	private void searchFiles(File dir) {
		File[] children = dir.listFiles();
		for (File file : children) {
			if (file.isDirectory()) {
				searchFiles(file);
			} else {
				this.sourceFiles.add(this.getSourceFileName(file.getPath()));
			}
		}
	}
	
	private String getSourceFileName(String absPath) {
		return absPath.replaceFirst(this.prefixPath, "");
	}
	
	public int getNumberOfFiles() {
		return this.sourceFiles.size();
	}
	
	public boolean containsFile(String sourceFileName) {
		return this.sourceFiles.contains(sourceFileName);
	}
}
