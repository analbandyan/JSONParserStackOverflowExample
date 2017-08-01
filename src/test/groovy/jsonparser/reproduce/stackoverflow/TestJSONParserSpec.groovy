package jsonparser.reproduce.stackoverflow

import org.grails.web.json.JSONElement
import org.grails.web.json.parser.JSONParser
import spock.lang.Specification

import java.nio.charset.StandardCharsets

class TestJSONParserSpec extends Specification {


    void "Test JSONParser works as expected when parsing short array json data"() {
        given: "JSONParser with input stream containing short array data"
        JSONParser jsonParser = new JSONParser(getJsonObjectInputStream(25))

        when: "parsing object with short array"
        JSONElement jsonElement = jsonParser.parseJSON()

        then: "json element parsed as expected"
        jsonElement != null
    }

    void "Test JSONParser throws StackOverflowError when parsing long array json data"() {
        given: "JSONParser with input stream containing long array data"
        JSONParser jsonParser = new JSONParser(getJsonObjectInputStream(15000))

        when: "parsing object with long array"
        jsonParser.parseJSON()

        then: "StackOverflowError thrown because of recursion"
        thrown StackOverflowError
    }

    private static InputStream getJsonObjectInputStream(int arrayLength) {
        String arrayObjectStr = "{\"array\": ${generateByteArray(arrayLength)}}"
        return new ByteArrayInputStream(arrayObjectStr.getBytes(StandardCharsets.UTF_8));
    }

    private static byte[] generateByteArray(int length) {
        byte[] byteArray = new byte[length]
        new Random().nextBytes(byteArray)
        byteArray
    }

}
