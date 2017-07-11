// ExtractorTest.java
// (C) COPYRIGHT METASWITCH NETWORKS 2014
package org.openmobilealliance.arc.sax2j.extract;

import org.junit.Test;
import org.openmobilealliance.arc.sax2j.ConsoleProgressWriter;

public class ExtractorTest
{

  @Test
  public void test() throws Exception
  {
    Extractor lExtractor = new Extractor("src/test/resources/sample/OMA-TS-REST_NetAPI_NMS-V1_0-20140715-C.txt",
                                         "target/xmldata_",
                                         ".xml.json");
    lExtractor.setProgressWriter(new ConsoleProgressWriter());
    lExtractor.extract();
  }

}
