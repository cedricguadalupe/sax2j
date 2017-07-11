// MainTest.java
// (C) COPYRIGHT METASWITCH NETWORKS 2014
package org.openmobilealliance.arc.sax2j;

import org.junit.Test;

public class MainTest
{
  @Test
  public void test() throws Exception
  {
    Main.main(new String[] { "src/test/resources/schemas/simple1.xsd", "src/test/resources/xmldocs/simple1-ok.xml" });
  }
}
