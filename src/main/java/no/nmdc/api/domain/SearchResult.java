package no.nmdc.api.domain;

import java.util.HashMap;
import java.util.Map;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


@XmlType(name = "doc")
public class SearchResult {
    @XmlElement
    public Map<String, Object> fields = new HashMap<String, Object>(10);
    
    public Map<String, Object> getFields() {
        return fields;
    }
    public void setFields(Map<String, Object> fields) {
        this.fields = fields;
    }
    public void putField(String key, Object value) {
        fields.put(key, value);
    }
}
