package no.nmdc.solr.domain;

import java.util.Comparator;

import org.apache.solr.client.solrj.response.LukeResponse.FieldInfo;

public class FieldInfoComparator implements Comparator<FieldInfo>{
    @Override
    public int compare(FieldInfo fieldInfo1, FieldInfo fieldInfo2) {
      if (fieldInfo1.getDocs() > fieldInfo2.getDocs()) {
        return -1;
      }
      if (fieldInfo1.getDocs() < fieldInfo2.getDocs()) {
        return 1;
      }
      return fieldInfo1.getName().compareTo(fieldInfo2.getName());
    }
}
