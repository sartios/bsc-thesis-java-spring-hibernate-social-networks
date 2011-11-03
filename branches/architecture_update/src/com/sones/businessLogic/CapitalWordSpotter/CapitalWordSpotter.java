package com.sones.businessLogic.CapitalWordSpotter;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;

public class CapitalWordSpotter	implements	ICapitalWordSpotter
{

	private	PropertiesConfiguration	_config;
	private	Pattern	_pattern;
	private	Matcher	_matcher;
	
	public	CapitalWordSpotter()
	{
		try 
		{
			_config	=	new	PropertiesConfiguration("config/CapitalWordSpotter/CapitalWordSpotter.properties");
		} 
		catch (ConfigurationException e) 
		{
			e.printStackTrace();
		}
	}
	
	@Override
	public List<String> GetWordsThatStartWithCapitalLetter( String text ) 
	{
		_pattern	=	Pattern.compile( _config.getString( "regular_expression_word_that_start_with_capital_letter" ) );
		_matcher	=	_pattern.matcher( text );
		
		List< String >	words	=	new	ArrayList< String >();
		int index = 0;
		while(_matcher.find())
		{
			String	word	=	_matcher.group();
			words.add( word );
			index++;
		}
		
		return	words;
	}

}
