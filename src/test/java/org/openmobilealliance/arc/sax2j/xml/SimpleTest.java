// SimpleTest.java
// (C) COPYRIGHT METASWITCH NETWORKS 2014
package org.openmobilealliance.arc.sax2j.xml;

import java.io.File;

import org.junit.Test;
import org.openmobilealliance.arc.sax2j.ConsoleProgressWriter;
import org.openmobilealliance.arc.sax2j.ProgressWriter;
import org.openmobilealliance.arc.sax2j.xml.XmlDocument;
import org.openmobilealliance.arc.sax2j.xml.XmlSchema;

public class SimpleTest
{

  @Test
  public void test() throws Exception
  {
    ProgressWriter lProgress = new ConsoleProgressWriter();

    XmlSchema lSchema = new XmlSchema(new File("src/test/resources/schemas",
                                               "simple1.xsd"));
    lSchema.setProgressWriter(lProgress);
    lSchema.parse();

    XmlDocument lDoc = new XmlDocument(lSchema, new File("src/test/resources/xmldocs",
                                                         "simple1-ok.xml"));
    lDoc.setProgressWriter(lProgress);
    lDoc.parse();

  }

}
