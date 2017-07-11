// XmlDocument.java
// (C) COPYRIGHT METASWITCH NETWORKS 2014
package org.openmobilealliance.arc.sax2j.xml;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.openmobilealliance.arc.sax2j.ProgressWriter;
import org.openmobilealliance.arc.sax2j.json.JsonObject;
import org.openmobilealliance.arc.sax2j.json.JsonValue;
import org.openmobilealliance.arc.sax2j.xml.Translator.TranslationMode;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;

public class XmlDocument
{
  /**
   * The progress writer we use.
   */
  private ProgressWriter mProgress = new ProgressWriter.NullProgressWriter();

  /**
   * The schema to parse with.
   */
  private final XmlSchema mSchema;

  /**
   * The file to parse.
   */
  private final File mDocFile;

  /**
   * The parsed DOM document.
   */
  private Document mDoc;

  /**
   * Construct an XML document. Use {@link #parse()} to parse it.
   *
   * @param xiSchema
   * @param xiFile
   */
  public XmlDocument(XmlSchema xiSchema, File xiFile)
  {
    mSchema = xiSchema;
    mDocFile = xiFile;
  }

  public void setProgressWriter(ProgressWriter xiProgress)
  {
    mProgress = xiProgress;
  }

  /**
   * Parse the given XML document using the given schema.
   *
   * @throws ParserConfigurationException
   * @throws SAXException
   * @throws IOException
   */
  public void parse()
      throws ParserConfigurationException, SAXException, IOException
  {
    if (mDoc != null)
    {
      throw new RuntimeException("Already parsed");
    }

    // Get a parser.
    DocumentBuilderFactory lFactory = mSchema.getDocumentBuilderFactory();
    DocumentBuilder lBuilder = lFactory.newDocumentBuilder();
    ThrowingErrorHandler lErrors = new ThrowingErrorHandler();
    lErrors.setProgressWriter(mProgress);
    lBuilder.setErrorHandler(lErrors);
    mProgress.log("Parsing " + mDocFile);

    // Parse.
    mDoc = lBuilder.parse(mDocFile);

    // Check we're all hunky-dory.
    if (!mDoc.getDocumentElement().isSupported("psvi", "1.0"))
    {
      throw new RuntimeException("PSVI not supported by document");
    }

    mProgress.log("Successfully parsed " + mDocFile);
  }

  /**
   * Convert to JSON following the rules of
   * OMA-TS-REST_NetAPI_Common-V1_0-20140604-D.doc.
   *
   * @param xiMode the translation mode to use
   * @return the JSON object.
   */
  public JsonValue toJson(TranslationMode xiMode)
  {
    mProgress.log("Translating " + mDocFile + " to " + xiMode + " JSON");
    JsonObject lObject = JsonObject.create();
    Translator.toJson(xiMode, lObject, mDoc.getDocumentElement());
    mProgress.log("Successfully translated " + mDocFile);
    return lObject;
  }
}
