/***************************************************************************
 *                                                                         *
 *   This program is free software; you can redistribute it and/or modify  *
 *   it under the terms of the GNU General Public License as published by  *
 *   the Free Software Foundation; either version 2 of the License, or     *
 *   (at your option) any later version.                                   *
 *                                                                         *
 *   Copyright (C) 2005 - Matteo Merli - matteo.merli@gmail.com            *
 *                                                                         *
 ***************************************************************************/

/*
 * $Id: Main.java 333 2005-12-08 16:49:37Z merlimat $
 * 
 * $URL: http://svn.berlios.de/svnroot/repos/rtspproxy/tags/3.0-ALPHA2/src/main/java/rtspproxy/Main.java $
 * 
 */
package rtspproxy;

import org.apache.log4j.Logger;

import rtspproxy.lib.Exceptions;

/**
 * 
 */
public class Main
{

	static Logger log = Logger.getLogger( "rtspproxy" );

	public static void main( String[] args )
	{
		// TODO: remove this temp stuffs
		/*
		 * for ( Object key : System.getProperties().keySet() ) { String value =
		 * System.getProperty( (String)key ); System.out.println( key + " : " +
		 * value ); }
		 */

		// Register the "rtsp://" protocol scheme
		System.setProperty( "java.protocol.handler.pkgs", "rtspproxy" );

		new Config();

		// Register the signal handler
		Runtime.getRuntime().addShutdownHook( new ShutdownHandler() );

		try {
			log.info( "Starting " + Config.getName() + " " + Config.getVersion() );
			Reactor.setStandalone( true );
			Reactor.start();

		} catch ( Exception e ) {
			log.fatal( "Exception in the reactor: " + e );
			Exceptions.logStackTrace( e );
			System.exit( -1 );
		}
	}

}
