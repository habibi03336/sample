package com.hollysgang.sample.framework.core.manage.api;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class APIInfo implements Comparable<APIInfo> {
    private String key;
    private String uri;
    private String method;
    private String description;
    private boolean delYn;

    @Override
    public int compareTo(APIInfo o) {
        int uriCompare = this.uri.compareTo(o.uri);
        int methodCompare = this.method.compareTo(o.method);
        return uriCompare != 0 ? uriCompare : methodCompare;
    }

    @Override
    public boolean equals(Object o){
        if(o == null) return false;
        if(!(o instanceof APIInfo that)) return false;
        if(this.uri == null || this.method == null) {
            return false;
        }
        return this.uri.equals(that.uri) && this.method.equals(that.method);
    }

    public boolean compareAllFields(Object o){
        if(!this.equals(o)) return false;
        APIInfo that = (APIInfo) o;
        if(description == null && that.description == null) return true;
        if(description == null || that.description == null) return false;
        return description.equals(that.description);
    }
}
