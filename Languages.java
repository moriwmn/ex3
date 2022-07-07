package ex3;

public class Languages {
	private boolean python;
	private boolean java;
	private boolean c;
	private boolean cpp;
	private boolean javascript;

	// default Constructor:
	public Languages() {
		this.python = false;
		this.java = false;
		this.c = false;
		this.cpp = false;
		this.javascript = false;
	}

	public Languages(boolean python, boolean java, boolean c, boolean cpp, boolean javascript) {
		this.python = python;
		this.java = java;
		this.c = c;
		this.cpp = cpp;
		this.javascript = javascript;
	}

	// getters and setters:
	public boolean isPython() {
		return python;
	}

	public void setPython(boolean python) {
		this.python = python;
	}

	public boolean isJava() {
		return java;
	}

	public void setJava(boolean java) {
		this.java = java;
	}

	public boolean isC() {
		return c;
	}

	public void setC(boolean c) {
		this.c = c;
	}

	public boolean isCpp() {
		return cpp;
	}

	public void setCpp(boolean cpp) {
		this.cpp = cpp;
	}

	public boolean isJavascript() {
		return javascript;
	}

	public void setJavascript(boolean javascript) {
		this.javascript = javascript;
	}

	public int How_many_lang(){
		int counter = 0;
		if (this.isC())
			counter++;
		if (this.isCpp())
			counter++;
		if (this.isPython())
			counter++;
		if (this.isJava())
			counter++;
		if (this.isJavascript())
			counter++;
		return counter;
		
	}

	@Override
	public String toString() {
		String lang = "";
		if (this.python == true) {
			lang += "**pyhton**  ";
		}
		if (this.java == true) {
			lang += "**java**  ";
		}
		if (this.c == true) {
			lang += "**C**  ";
		}
		if (this.cpp == true) {
			lang += "**Cpp**  ";
		}
		if (this.javascript == true) {
			lang += "**javaScript**  ";
		}
		return " " + lang;
	}

} // end class
