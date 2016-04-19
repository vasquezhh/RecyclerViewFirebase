package vasquezh.rviewfirebase.Data;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Created by Laboratorio on 19/04/2016.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Information {

    private String name;

    public Information() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
