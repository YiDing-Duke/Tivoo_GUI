package parser;

import java.text.ParseException;

import org.dom4j.Element;

import calendar.XMLCal;

public abstract class Parser {

	private Element root;

	public Parser(Element root) {
		this.root = root;
	}

	public Element getRoot() {
		return root;
	}

	public abstract XMLCal parse() throws ParseException;
	
	public abstract static class Factory {

		public abstract boolean isType(Element root);

		public abstract Parser buildCalendar(Element root);

	}

}
