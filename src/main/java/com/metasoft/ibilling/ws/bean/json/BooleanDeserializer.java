package com.metasoft.ibilling.ws.bean.json;
import java.io.IOException;
import java.util.Date;

import org.apache.commons.lang.StringUtils;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;

public class BooleanDeserializer extends JsonDeserializer<Boolean> {

    @Override
    public Boolean deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
        ObjectCodec oc = jsonParser.getCodec();
        JsonNode node = oc.readTree(jsonParser);
        String str = node.asText();
        if(StringUtils.isNotBlank(str)){
        	if("1".equals(str) || "Y".equalsIgnoreCase(str) || "true".equalsIgnoreCase(str)){
        		return true;
        	}else if("0".equals(str) || "N".equalsIgnoreCase(str) || "false".equalsIgnoreCase(str)){
        		return false;
        	}else{
        		return null;
        	}
        }else{
        	return null;
        }
        
    }
}