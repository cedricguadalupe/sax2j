// DefaultErrorHandler.java
// (C) COPYRIGHT METASWITCH NETWORKS 2014
package org.openmobilealliance.arc.sax2j.xml;

import org.openmobilealliance.arc.sax2j.ProgressWriter;
import org.openmobilealliance.arc.sax2j.ProgressWriter.NullProgressWriter;
import org.xml.sax.ErrorHandler;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

/**
 * Default error handler - errors are errors, but warnings are simply written
 * to the progress output.
 */
public class ThrowingErrorHandler implements ErrorHandler
{
  private ProgressWriter mProgress = new ProgressWriter.NullProgressWriter();

  public void setProgressWriter(ProgressWriter xiProgress)
  {
    mProgress = xiProgress;
  }

  @Override
  public void error(SAXParseException xiException) throws SAXException
  {
    mProgress.log("ERROR: " + xiException);
    throw xiException;
  }

  @Override
  public void fatalError(SAXParseException xiException) throws SAXException
  {
    mProgress.log("FATAL: " + xiException);
    throw xiException;
  }

  @Override
  public void warning(SAXParseException xiException) throws SAXException
  {
    mProgress.log("WARNING: " + xiException);
  }
}
