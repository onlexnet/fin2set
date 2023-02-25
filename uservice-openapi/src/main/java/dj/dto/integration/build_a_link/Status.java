
package dj.dto.integration.build_a_link;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.experimental.Accessors;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
@Accessors(chain = true)
public class Status {

    @JsonProperty("short")
    public String _short;

    @JsonProperty("long")
    public String _long;

    @JsonProperty("description")
    public String description;

}
