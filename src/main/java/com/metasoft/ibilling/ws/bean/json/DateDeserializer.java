package com.metasoft.ibilling.ws.bean.json;
import java.io.IOException;
import java.util.Date;
import java.util.Locale;

import org.apache.commons.lang.StringUtils;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.metasoft.ibilling.util.DateToolsUtil;

public class DateDeserializer extends JsonDeserializer<Date> {

    @Override
    public Date deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
        ObjectCodec oc = jsonParser.getCodec();
        JsonNode node = oc.readTree(jsonParser);
        String str = node.asText();
        if(StringUtils.isNotBlank(str)){
        	return DateToolsUtil.convertStringToDate(str, "yyyy-MM-dd HH:mm", Locale.US);
        }else{
        	return null;
        }
        
    }
}